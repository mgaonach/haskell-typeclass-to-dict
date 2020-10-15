package compiler;

import compiler.haskell.Class;
import compiler.haskell.Data;
import compiler.haskell.DataConstructor;
import compiler.haskell.DataInstanceAny;
import compiler.haskell.DataInstanceCons;
import compiler.haskell.DataInstanceVar;
import compiler.haskell.Function;
import compiler.haskell.FunctionCase;
import compiler.haskell.FunctionType;
import compiler.haskell.GenericType;
import compiler.haskell.HaskellDefault;

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
	}

}
