-- Notre example de programme

-- Primitive types definitions

data MyNat = MyZero 
   | MySucc (MyNat) deriving Show

data MyBool = MyTrue 
   | MyFalse  deriving Show
myAnd :: MyBool -> MyBool -> MyBool
myAnd (MyTrue)  (MyTrue)  = MyTrue
myAnd (_)  (_)  = MyFalse
myOr :: MyBool -> MyBool -> MyBool
myOr (MyTrue)  (_)  = MyTrue
myOr (MyFalse)  (b)  = b

data (MyList a) = MyCons (a) ((MyList a))
   | MyNil  deriving Show

-- First example : type class with one method
class (Compare a) where
   isSup :: a -> a -> MyBool

isSupBool :: MyBool -> MyBool -> MyBool
isSupBool (MyTrue)  (MyFalse)  = MyTrue
isSupBool (_)  (_)  = MyFalse
isSupNat :: MyNat -> MyNat -> MyBool
isSupNat (MySucc (x) )  (MySucc (y) )  = isSupNat (x)  (y) 
isSupNat (MySucc (x) )  (MyZero)  = MyTrue
isSupNat (MyZero)  (_)  = MyFalse
instance (Compare MyBool) where
   isSup = isSupBool

instance (Compare MyNat) where
   isSup = isSupNat

areAllSup :: (Compare a) => (MyList a) -> a -> MyBool
areAllSup (MyNil)  (n)  = MyTrue
areAllSup (MyCons (x)  (l) )  (n)  = myAnd (areAllSup (l)  (n) )  (isSup (x)  (n) ) 