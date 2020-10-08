-- Primitive types definitions
data MyNat = MyZero
         | MySucc MyNat deriving Show

data MyBool = MyTrue
             | MyFalse deriving Show

myAnd :: MyBool -> MyBool -> MyBool
myAnd MyTrue MyTrue = MyTrue
myAnd _ _ = MyFalse

myOr :: MyBool -> MyBool -> MyBool
myOr MyTrue _ = MyTrue
myOr MyFalse b = b

data MyList a = MyCons a (MyList a) 
                | MyNil deriving Show

-- First example : type class with one method
class Compare a where 
    isSup :: a -> a -> MyBool

isSupBool :: MyBool -> MyBool -> MyBool
isSupBool MyTrue MyFalse = MyTrue
isSupBool _ _ = MyFalse

isSupNat ::  MyNat ->  MyNat -> MyBool
isSupNat (MySucc x) (MySucc y) = isSupNat x y
isSupNat (MySucc x) MyZero = MyTrue
isSupNat MyZero _ = MyFalse

instance Compare MyBool where
    isSup = isSupBool

instance Compare MyNat where
    isSup = isSupNat

areAllSup :: Compare a => MyList a -> a -> MyBool
areAllSup MyNil n = MyTrue
areAllSup (MyCons x l) n = myAnd (areAllSup l n) (isSup x n)

-- Tests
test1 = (isSupBool MyTrue MyTrue)
test2 = isSupBool MyTrue MyFalse
test3 = isSupNat (MySucc (MySucc MyZero)) (MySucc (MySucc (MySucc MyZero)))
test4 = isSupNat (MySucc (MySucc MyZero)) (MySucc (MyZero))

test5 = areAllSup (MyCons MyTrue (MyCons MyTrue (MyCons MyTrue MyNil))) (MyFalse)
test6 = areAllSup (MyCons MyTrue (MyCons MyFalse (MyCons MyTrue MyNil))) (MyFalse)

my0 = MyZero
my1 = (MySucc MyZero)
my2 = (MySucc (MySucc MyZero))
my3 = (MySucc (MySucc (MySucc MyZero)))
my4 = (MySucc (MySucc (MySucc (MySucc MyZero))))
my5 = (MySucc (MySucc (MySucc (MySucc (MySucc MyZero)))))

test7 = areAllSup (MyCons my2 (MyCons my3 (MyCons my4 MyNil))) my1
test8 = areAllSup (MyCons my3 (MyCons my1 (MyCons my4 MyNil))) my2

-- First example : dictionaries translation
data CompareD a = CompareDict (a -> a -> MyBool)
isSupf (CompareDict f) = f

compareDBool :: CompareD MyBool
compareDBool = CompareDict isSupBool

compareDNat :: CompareD  MyNat
compareDNat = CompareDict isSupNat

areAllSup' :: CompareD a -> MyList a -> a -> MyBool
areAllSup' d MyNil n = MyTrue
areAllSup' d (MyCons x l) n = myAnd (areAllSup' d l n) (isSupf d x n)

-- Tests

test9 = areAllSup' compareDBool (MyCons MyTrue (MyCons MyTrue (MyCons MyTrue MyNil))) (MyFalse)
test10 = areAllSup' compareDBool (MyCons MyTrue (MyCons MyFalse (MyCons MyTrue MyNil))) (MyFalse)
test11 = areAllSup' compareDNat (MyCons my2 (MyCons my3 (MyCons my4 MyNil))) my1
test12 = areAllSup' compareDNat (MyCons my3 (MyCons my1 (MyCons my4 MyNil))) my2

-- Second example : second class with two methods

class Parity a where 
    isOdd :: a -> MyBool
    isEven :: a -> MyBool

isOddBool :: MyBool -> MyBool
isOddBool b = b

isEvenBool :: MyBool -> MyBool
isEvenBool MyTrue = MyFalse
isEvenBool MyFalse = MyTrue

isOddNat :: MyNat -> MyBool
isOddNat MyZero = MyFalse
isOddNat (MySucc MyZero) = MyTrue
isOddNat (MySucc (MySucc n)) = isOddNat n

isEvenNat :: MyNat -> MyBool
isEvenNat MyZero = MyTrue
isEvenNat (MySucc MyZero) = MyFalse
isEvenNat (MySucc (MySucc n)) = isEvenNat n

instance Parity MyBool where
    isOdd = isOddBool
    isEven = isEvenBool

instance Parity MyNat where
    isOdd = isOddNat
    isEven = isEvenNat

areAllEven :: Parity a => MyList a -> MyBool
areAllEven MyNil = MyTrue
areAllEven (MyCons x l) = myAnd (areAllEven l) (isEven x)

-- tests

test13 = isOdd MyTrue
test14 = isOdd MyFalse
test15 = isEven MyTrue
test16 = isEven MyFalse

test17 = areAllEven (MyCons MyFalse (MyCons MyFalse (MyCons MyFalse MyNil)))
test18 = areAllEven (MyCons MyFalse (MyCons MyTrue (MyCons MyFalse MyNil)))

test19 = isOdd my4
test20 = isOdd my5
test21 = isEven my4
test22 = isEven my5

test23 = areAllEven (MyCons my2 (MyCons my3 (MyCons my4 MyNil)))
test24 = areAllEven (MyCons my2 (MyCons my0 (MyCons my4 MyNil)))

-- Second example : dictionaries translation

data ParityD a = ParityDict (a -> MyBool) (a -> MyBool)
isOddf (ParityDict f g) = f
isEvenf (ParityDict f g) = g

parityDBool :: ParityD MyBool
parityDBool = ParityDict isOddBool isEvenBool

parityDNat :: ParityD MyNat
parityDNat = ParityDict isOddNat isEvenNat

areAllEven' :: ParityD a -> MyList a -> MyBool
areAllEven' d MyNil = MyTrue
areAllEven' d (MyCons x l) = myAnd (areAllEven' d l) (isEvenf d x)

-- Tests

test25 = areAllEven' parityDBool (MyCons MyFalse (MyCons MyFalse (MyCons MyFalse MyNil)))
test26 = areAllEven' parityDBool (MyCons MyFalse (MyCons MyTrue (MyCons MyFalse MyNil)))

test27 = areAllEven' parityDNat (MyCons my2 (MyCons my3 (MyCons my4 MyNil)))
test28 = areAllEven' parityDNat (MyCons my2 (MyCons my0 (MyCons my4 MyNil)))

-- Third example : subclasses

class Compare a => Parity2 a where 
    isOdd2 :: a -> MyBool
    isEven2 :: a -> MyBool

instance Parity2 MyBool where
    isOdd2 = isOddBool
    isEven2 = isEvenBool

instance Parity2 MyNat where
    isOdd2 = isOddNat
    isEven2 = isEvenNat

allEvenAreSup :: Parity2 a => MyList a -> a -> MyBool
allEvenAreSup MyNil n = MyTrue
allEvenAreSup (MyCons x l) n = myAnd (allEvenAreSup l n) (myOr (isOdd2 x) (isSup x n))

-- Tests

test29 = allEvenAreSup (MyCons MyTrue (MyCons MyTrue (MyCons MyTrue MyNil))) MyFalse
test30 = allEvenAreSup (MyCons MyTrue (MyCons MyFalse (MyCons MyTrue MyNil))) MyFalse

test31 = allEvenAreSup (MyCons my4 (MyCons my1 (MyCons my4 MyNil))) my2
test32 = allEvenAreSup (MyCons my4 (MyCons my1 (MyCons my0 MyNil))) my2

-- Third example : dictionaries translation

data Parity2D a = Parity2Dict (a -> MyBool) (a -> MyBool) (a-> a-> MyBool)
isOdd2f (Parity2Dict f g h) = f
isEven2f (Parity2Dict f g h) = g
isSup2f (Parity2Dict f g h) = h

parity2DBool :: Parity2D MyBool
parity2DBool = Parity2Dict isOddBool isEvenBool isSupBool

parity2DNat :: Parity2D MyNat
parity2DNat = Parity2Dict isOddNat isEvenNat isSupNat

allEvenAreSup' :: Parity2D a -> MyList a -> a -> MyBool
allEvenAreSup' d MyNil n = MyTrue
allEvenAreSup' d (MyCons x l) n = myAnd (allEvenAreSup' d l n) (myOr (isOdd2f d x) (isSup2f d x n))

-- Tests

test33 = allEvenAreSup' parity2DBool (MyCons MyTrue (MyCons MyTrue (MyCons MyTrue MyNil))) MyFalse
test34 = allEvenAreSup' parity2DBool (MyCons MyTrue (MyCons MyFalse (MyCons MyTrue MyNil))) MyFalse

test35 = allEvenAreSup' parity2DNat (MyCons my4 (MyCons my1 (MyCons my4 MyNil))) my2
test36 = allEvenAreSup' parity2DNat (MyCons my4 (MyCons my1 (MyCons my0 MyNil))) my2