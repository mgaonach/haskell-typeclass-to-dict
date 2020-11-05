package compiler;

import java.util.Arrays;
import java.util.Collections;

import compiler.haskell.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

		/// 1st example

		Type typeVar = new TypeVar("Compare");
		Type typeParam = new TypeVar("a");
		TypeApplication typeApp = new TypeApplication(typeVar, typeParam);

		// super types
		List<Type> sts = new ArrayList<>();

		// function Definition
		// "isSup :: a -> a -> MyBool"
		FunctionDefinition isSupDef = new FunctionDefinition("isSup",
				new TypeFunction(new TypeFunction(new TypeVar("a"), new TypeVar("a")), new TypeConstructor("MyBool")));
		List<FunctionDefinition> fds = new ArrayList<>(Arrays.asList(isSupDef));

		ClassDefinition cd = new ClassDefinition(typeApp, fds, sts);

		// isSupBool
		FunctionDefinition isSupBoolDef = new FunctionDefinition("isSupBool",
				new TypeFunction(new TypeFunction(new TypeConstructor("MyBool"), new TypeConstructor("MyBool")),
						new TypeConstructor(("MyBool"))));

		new Attribution(
				new TermApplication(new TermApplication(new TermFunction("isSupBool"), new TermConstructor("MyTrue")),
						new TermConstructor("MyFalse")),
				new TermConstructor("MyTrue"));

		new Attribution(
				new TermApplication(new TermApplication(new TermFunction("isSupBool"), new TermAny()), new TermAny()),
				new TermConstructor("MyFalse"));

		// TODO isSupNat
	}

	public static void getAST3() {

		TypeConstraint parity2Type = new TypeConstraint(//
				new TypeApplication(new TypeVar("Compare"), new TypeVar("a")), //
				new TypeApplication(new TypeVar("Parity2"), new TypeVar("a"))//
		);

		FunctionDefinition isOdd2Def = new FunctionDefinition("isOdd2",
				new TypeFunction(new TypeVar("a"), new TypeConstructor("MyBool")));
		FunctionDefinition isEven2Def = new FunctionDefinition("isEven2",
				new TypeFunction(new TypeVar("a"), new TypeConstructor("MyBool")));

		ClassDefinition parity2Def = new ClassDefinition(parity2Type, Arrays.asList(isOdd2Def, isEven2Def),
				Collections.emptyList());

		Instance iParity2MyBool = new Instance(//
				new TypeApplication(new TypeConstructor("Parity2"), new TypeConstructor("myBool")), //
				new Attribution(new TermConstructor("isOdd2"), new TermConstructor("isOddBool")), //
				new Attribution(new TermConstructor("isEven2"), new TermConstructor("isEvenBool"))//
		);

		Instance iParity2MyNat = new Instance(//
				new TypeApplication(new TypeConstructor("Parity2"), new TypeConstructor("MyNat")), //
				new Attribution(new TermConstructor("isOdd2"), new TermConstructor("isOddNat")), //
				new Attribution(new TermConstructor("isEven2"), new TermConstructor("isEvenNat"))//
		);

		FunctionDefinition allEvenAreSupDef = new FunctionDefinition("allEvenAreSup", //
				new TypeConstraint(//
						new TypeApplication(//
								new TypeConstructor("Parity2"), //
								new TypeVar("a")//
						), //
						new TypeFunction(//
								new TypeFunction(//
										new TypeApplication(//
												new TypeConstructor("MyList"), //
												new TypeVar("a")//
										)//
										, new TypeVar("a")//
								), //
								new TypeConstructor("MyBool")//
						)//
				)//
		);

		new Attribution(new TermApplication(
				new TermApplication(new TermConstructor("allEvenAreSup"), new TermConstructor("MyNil")),
				new TermVar("n")), new TermConstructor("MyTrue"));

		new Attribution(//
				new TermApplication(//
						new TermApplication(//
								new TermConstructor("allEvenAreSup"), //
								new TermApplication(//
										new TermApplication(//
												new TermConstructor("MyCons"), //
												new TermVar("x")//
										), //
										new TermVar("l")//
								)//
						), //
						new TermVar("n")//
				), //
				new TermApplication(//
						new TermApplication(//
								new TermConstructor("myAnd"), //
								new TermApplication(//
										new TermApplication(//
												new TermConstructor("allEvenAreSup"), //
												new TermVar("l")//
										), //
										new TermVar("n")//
								)), //
						new TermApplication(//
								new TermApplication(//
										new TermConstructor("myOr"), //
										new TermApplication(//
												new TermConstructor("idOdd2"), //
												new TermVar("x")//
										)//
								), //
								new TermApplication(//
										new TermApplication(//
												new TermConstructor("isSup"), //
												new TermVar("x")//
										), //
										new TermVar("n")//
								)//
						)//
				)//
		);
	}

}
