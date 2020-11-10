-- Notre example de programme

-- Primitive types definitions

data MyNat = MyZero 
		| MySucc MyNat deriving Show

data MyBool = MyTrue 
		| MyFalse  deriving Show
myAnd :: myBool -> myBool -> myBool
myAnd (myTrue) (myFalse) = MyTrue
myAnd (_) (_) = MyFalse
myOr :: myBool -> myBool -> myBool
myOr (myTrue) (_) = MyTrue
myOr (myFalse) (b) = b

data (MyList a) = MyCons a (MyList a)
		| MyNil  deriving Show

-- First example : type class with one method
class (Compare a) where
	isSup :: a -> a -> MyBool

isSupBool :: MyBool -> MyBool -> MyBool
isSupBool (MyTrue) (MyFalse) = MyTrue
isSupBool (_) (_) = MyFalse
isSupNat :: MyNat -> MyNat -> MyBool
isSupNat (MySucc (x)) (MySucc (y)) = isSupNat (x) (y)
isSupNat (MySucc (x)) (MyZero) = MyTrue
isSupNat (MyZero) (_) = MyFalse
instance (Compare MyBool) where
	isSup = isSupBool

instance (Compare MyNat) where
	isSup = isSupNat

areAllSup :: (Compare a) -> (MyList a) -> a -> MyBool
areAllSup (MyNil (n)) = MyTrue
areAllSup (MyCons (x) (l) (n)) = myAnd (areAllSup (l) (n) (isSup (x) (n)))

-- Second example : second class with two methods
class (Parity a) where
	isOdd :: a -> MyBool
	isEven :: a -> MyBool

isOddBool :: MyBool -> MyBool
isOddBool (b) = b
isEvenBool :: MyBool -> MyBool
isEvenBool (MyTrue) = MyFalse
isEvenBool (MyFalse) = MyTrue
isOddNat :: MyNat -> MyFalse
isOddNat (MyZero) = MyFalse
isOddNat (MySucc (MyZero)) = MyTrue
isOddNat (MySucc (MySucc (n))) = isOddNat (n)
isEvenNat :: MyNat -> MyBool
isEvenNat (MyZero) = MyTrue
isEvenNat (MySucc (MyZero)) = MyFalse
isEvenNat (MySucc (MySucc (n))) = isEvenNat (n)
instance (Parity MyBool) where
	isOdd = isOddBool
	isEven = isEvenBool

instance (Parity MyNat) where
	isOdd = isOddNat
	isEven = isEvenNat

areAllEven :: (Parity a) => (MyList a) -> MyBool
areAllEven (MyNil) = MyTrue
areAllEven (MyCons (x) (l)) = myAnd (areAllEven (l) (isEven (x)))

-- Third example : subclasses
class (Compare a) => (Parity2 a) where
	isOdd2 :: a -> MyBool
	isEven2 :: a -> MyBool

instance (Parity2 myBool) where
	isOdd2 = isOddBool
	isEven2 = isEvenBool

instance (Parity2 MyNat) where
	isOdd2 = isOddNat
	isEven2 = isEvenNat

allEvenAreSup :: (Parity2 a) => (MyList a) -> a -> MyBool
allEvenAreSup (MyNil) (n) = MyTrue
allEvenAreSup (MyCons (x) (l)) (n) = myAnd (allEvenAreSup (l) (n)) (myOr (idOdd2 (x)) (isSup (x) (n)))) ((isSup x) n)))n)))