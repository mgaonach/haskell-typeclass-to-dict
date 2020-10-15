package compiler;

import compiler.haskell.*;
import compiler.haskell.Class;

public class ExampleProgram {

	public static void getAST() {

		// Primitive types definitions

		Data myNat = new Data("MyNat");
		DataConstructor myZero = new DataConstructor("MyZero");
		myNat.getDataConstructors().add(myZero);
		DataConstructor mySucc = new DataConstructor("MySucc").addParam(myNat);
		myNat.getDataConstructors().add(mySucc);
		myNat.getDeriving().add(HaskellDefault.SHOW);

		Data myBool = new Data("MyBool");
		DataConstructor myTrue = new DataConstructor("MyTrue");
		myBool.getDataConstructors().add(myTrue);
		DataConstructor myFalse = new DataConstructor("MyFalse");
		myBool.getDataConstructors().add(myFalse);
		myBool.getDeriving().add(HaskellDefault.SHOW);

		Function myAnd = new Function("myAnd", new FunctionType(myBool, myBool, myBool));
		myAnd.getFunctionCases().add(new FunctionCase(//
				new DataInstanceCons(myBool, myTrue), // Res
				new DataInstanceCons(myBool, myTrue), // Param
				new DataInstanceCons(myBool, myTrue)// Param
		));
		myAnd.getFunctionCases().add(new FunctionCase(//
				new DataInstanceCons(myBool, myFalse), // Res
				new DataInstanceAny(myBool), // Param
				new DataInstanceAny(myBool)// Param
		));

		Function myOr = new Function("myOr", new FunctionType(myBool, myBool, myBool));
		myOr.getFunctionCases().add(new FunctionCase(//
				new DataInstanceCons(myBool, myTrue), // Res
				new DataInstanceCons(myBool, myTrue), // Param
				new DataInstanceAny(myBool)// Param
		));
		DataInstanceVar myOrVarB = new DataInstanceVar(myBool, "b");
		myOr.getFunctionCases().add(new FunctionCase(//
				myOrVarB, // Res
				new DataInstanceCons(myBool, myFalse), // Param
				myOrVarB// Param
		));

		// First example : type class with one method

		Class compare = new Class("Compare");
		GenericType compareA = new GenericType("a");
		compare.getGenericTypes().add(compareA);
		FunctionType CompareIsSup = new FunctionType(compareA, compareA, myBool);
		compare.getFunctions().put("isSup", CompareIsSup);

		Function isSupBool = new Function("isSupBool", new FunctionType(myBool, myBool, myBool));
		isSupBool.getFunctionCases().add(new FunctionCase(
				new DataInstanceCons(myBool, myTrue), // Res
				new DataInstanceCons(myBool, myTrue), // Param
				new DataInstanceCons(myBool, myFalse) // Param
		));
		isSupBool.getFunctionCases().add(new FunctionCase(
				new DataInstanceCons(myBool, myFalse), // Res
				new DataInstanceAny(myBool), // Param
				new DataInstanceAny(myBool) // Param
		));

//		isSupNat ::  MyNat ->  MyNat -> MyBool
//		isSupNat (MySucc x) (MySucc y) = isSupNat x y
//		isSupNat (MySucc x) MyZero = MyTrue
//		isSupNat MyZero _ = MyFalse


		Function isSupNat = new Function ("isSupNat", new FunctionType(myNat, myNat, myBool));
		DataInstanceVar isSupNatX = new DataInstanceVar(myNat, "x");
		DataInstanceVar isSupNatY = new DataInstanceVar(myNat, "y");
		isSupNat.getFunctionCases().add(new FunctionCase(
				new DataInstanceFunction(myBool, isSupNat),  // Res
				new DataInstanceCons(myNat, mySucc), // TODO
				new DataInstanceCons(myNat, mySucc) // TODO
		));
	}

}
