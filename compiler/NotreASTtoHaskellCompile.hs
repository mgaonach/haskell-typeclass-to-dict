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

data (CompareD a) = CompareDict (a -> a -> MyBool)
isSupf (CompareDict (a) )  = a
isSupBool :: MyBool -> MyBool -> MyBool
isSupBool (MyTrue)  (MyFalse)  = MyTrue
isSupBool (_)  (_)  = MyFalse
isSupNat :: MyNat -> MyNat -> MyBool
isSupNat (MySucc (x) )  (MySucc (y) )  = isSupNat (x)  (y) 
isSupNat (MySucc (x) )  (MyZero)  = MyTrue
isSupNat (MyZero)  (_)  = MyFalse
compareDMyBool :: (CompareD MyBool)
compareDMyBool = CompareDict (isSupBool) 
compareDMyNat :: (CompareD MyNat)
compareDMyNat = CompareDict (isSupNat) 
areAllSup' :: (CompareD a) -> (MyList a) -> a -> MyBool
areAllSup' (d)  (MyNil)  (n)  = MyTrue
areAllSup' (d)  (MyCons (x)  (l) )  (n)  = myAnd (areAllSup' (d)  (l)  (n) )  (isSupf (d)  (x)  (n) ) 