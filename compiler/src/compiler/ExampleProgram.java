package compiler;

import java.util.Arrays;

import compiler.haskell.*;

import java.util.ArrayList;
import java.util.List;

public class ExampleProgram {

	public static void getAST() {
		ConstructorDefinition myZeroDef = new ConstructorDefinition("MyZero");
		ConstructorDefinition mySuccDef = new ConstructorDefinition("MySucc", new TypeConstructor("myNat"));
		DataDefinition myNatDef = new DataDefinition(new TypeConstructor("MyNat"), Arrays.asList(myZeroDef, mySuccDef),
				Arrays.asList(new TypeConstructor("Show")));

		ConstructorDefinition myTrueDef = new ConstructorDefinition("MyTrue");
		ConstructorDefinition myFalseDef = new ConstructorDefinition("MyFalse");
		DataDefinition myBoolDef = new DataDefinition(new TypeConstructor("MyBool"),
				Arrays.asList(myTrueDef, myFalseDef), Arrays.asList(new TypeConstructor("Show")));

		FunctionDefinition myAndDef = new FunctionDefinition("myAnd",
				new TypeFunction(new TypeFunction(new TypeConstructor("myBool"), new TypeConstructor("myBool")),
						new TypeConstructor("myBool")));
		new Attribution(//
				new TermApplication(//
						new TermApplication(//
								new TermFunction("myAnd"), //
								new TermConstructor("myTrue")//
						), //
						new TermConstructor("myFalse")//
				), //
				new TermConstructor("MyTrue")//
		);
		new Attribution(//
				new TermApplication(//
						new TermApplication(//
								new TermFunction("myAnd"), //
								new TermAny()//
						), //
						new TermAny()//
				), //
				new TermConstructor("MyFalse")//
		);

		FunctionDefinition myOrDef = new FunctionDefinition("myOr",
				new TypeFunction(new TypeFunction(new TypeConstructor("myBool"), new TypeConstructor("myBool")),
						new TypeConstructor("myBool")));
		new Attribution(//
				new TermApplication(//
						new TermApplication(//
								new TermFunction("myOr"), //
								new TermConstructor("myTrue")//
						), //
						new TermAny()//
				), //
				new TermConstructor("MyTrue")//
		);
		new Attribution(//
				new TermApplication(//
						new TermApplication(//
								new TermFunction("myOr"), //
								new TermConstructor("myFalse")//
						), //
						new TermVar("b")//
				), //
				new TermVar("b")//
		);

		ConstructorDefinition myConsDef = new ConstructorDefinition("MyCons", new TypeVar("a"),
				new TypeApplication(new TypeConstructor("MyList"), new TypeVar("a")));
		ConstructorDefinition myNilDef = new ConstructorDefinition("MyNil");
		DataDefinition myListDef = new DataDefinition(
				new TypeApplication(new TypeConstructor("MyList"), new TypeVar("a")),
				Arrays.asList(myConsDef, myNilDef), Arrays.asList(new TypeConstructor("Show")));

	}

}
