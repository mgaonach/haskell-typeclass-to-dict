-- Primitive types definitions
data MyNat = MyZero
         | MySucc MyNat

data MyBool = MyTrue
             | MyFalse

myAnd :: MyBool -> MyBool -> MyBool
myAnd MyTrue MyTrue = MyTrue
myAnd _ _ = MyFalse

data MyList a = MyCons a (MyList a) 
                | MyNil

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

instance Compare  MyNat where
    isSup = isSupNat

areAllSup :: Compare a => MyList a -> a -> MyBool
areAllSup MyNil n = MyTrue
areAllSup (MyCons x l) n = myAnd (areAllSup l n) (isSup x n)

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