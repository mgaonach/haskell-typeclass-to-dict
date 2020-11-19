package compiler;

import java.util.Arrays;
import java.util.Collections;

import compiler.haskell.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ExampleProgram {

	public static Program getAST() {

		Program program = new Program("examples");

		Comment comPrimTypeDef = new Comment("Primitive types definitions");
		program.add(comPrimTypeDef);

		ConstructorDefinition myZeroDef = new ConstructorDefinition("MyZero");
		ConstructorDefinition mySuccDef = new ConstructorDefinition("MySucc", new TypeConstructor("MyNat"));
		DataDefinition myNatDef = new DataDefinition(new TypeConstructor("MyNat"), Arrays.asList(myZeroDef, mySuccDef),
				Arrays.asList(new TypeConstructor("Show")));

		program.add(myNatDef);

		ConstructorDefinition myTrueDef = new ConstructorDefinition("MyTrue");
		ConstructorDefinition myFalseDef = new ConstructorDefinition("MyFalse");
		DataDefinition myBoolDef = new DataDefinition(new TypeConstructor("MyBool"),
				Arrays.asList(myTrueDef, myFalseDef), Arrays.asList(new TypeConstructor("Show")));
		program.add(myBoolDef);

		FunctionDefinition myAndDef = new FunctionDefinition("myAnd",
				new TypeFunction(new TypeFunction(new TypeConstructor("MyBool"), new TypeConstructor("MyBool")),
						new TypeConstructor("MyBool")));
		program.add(myAndDef);

		program.add(new Attribution(//
				new TermApplication(//
						new TermApplication(//
								new TermFunction("myAnd"), //
								new TermConstructor("MyTrue")//
						), //
						new TermConstructor("MyFalse")//
				), //
				new TermConstructor("MyTrue")//
		));
		program.add(new Attribution(//
				new TermApplication(//
						new TermApplication(//
								new TermFunction("myAnd"), //
								new TermAny()//
						), //
						new TermAny()//
				), //
				new TermConstructor("MyFalse")//
		));

		FunctionDefinition myOrDef = new FunctionDefinition("myOr",
				new TypeFunction(new TypeFunction(new TypeConstructor("MyBool"), new TypeConstructor("MyBool")),
						new TypeConstructor("MyBool")));
		program.add(myOrDef);

		program.add(new Attribution(//
				new TermApplication(//
						new TermApplication(//
								new TermFunction("myOr"), //
								new TermConstructor("MyTrue")//
						), //
						new TermAny()//
				), //
				new TermConstructor("MyTrue")//
		));
		program.add(new Attribution(//
				new TermApplication(//
						new TermApplication(//
								new TermFunction("myOr"), //A
								new TermConstructor("MyFalse")//
						), //
						new TermVar("b")//
				), //
				new TermVar("b")//
		));

		ConstructorDefinition myConsDef = new ConstructorDefinition("MyCons", new TypeVar("a"),
				new TypeApplication(new TypeConstructor("MyList"), new TypeVar("a")));
		ConstructorDefinition myNilDef = new ConstructorDefinition("MyNil");
		DataDefinition myListDef = new DataDefinition(
				new TypeApplication(new TypeConstructor("MyList"), new TypeVar("a")),
				Arrays.asList(myConsDef, myNilDef), Arrays.asList(new TypeConstructor("Show")));
		program.add(myListDef);


		/// 1st example
		Comment com1stEx = new Comment("First example : type class with one method");
		program.add(com1stEx);

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
		program.add(cd);

		// isSupBool
		FunctionDefinition isSupBoolDef = new FunctionDefinition("isSupBool",
				new TypeFunction(new TypeFunction(new TypeConstructor("MyBool"), new TypeConstructor("MyBool")),
						new TypeConstructor(("MyBool"))));
		program.add(isSupBoolDef);

		program.add(new Attribution(
				new TermApplication(new TermApplication(new TermFunction("isSupBool"), new TermConstructor("MyTrue")),
						new TermConstructor("MyFalse")),
				new TermConstructor("MyTrue")));

		program.add(new Attribution(
				new TermApplication(new TermApplication(new TermFunction("isSupBool"), new TermAny()), new TermAny()),
				new TermConstructor("MyFalse")));

		// isSupNat
		FunctionDefinition isSupNatDef = new FunctionDefinition("isSupNat",
				new TypeFunction(new TypeFunction(new TypeConstructor("MyNat"), new TypeConstructor("MyNat")),
						new TypeConstructor("MyBool")));
		program.add(isSupNatDef);

		program.add(new Attribution(
				new TermApplication(
						new TermApplication(
								new TermFunction("isSupNat"),
								new TermApplication(
										new TermConstructor("MySucc"),
										new TermVar("x")
								)
						),
						new TermApplication(
								new TermConstructor("MySucc"),
								new TermVar("y")
						)

				),
				new TermApplication(
						new TermApplication(
								new TermFunction("isSupNat"),
								new TermVar("x")
						),
						new TermVar("y")
				)
		));

		program.add(new Attribution(
				new TermApplication(
						new TermApplication(
								new TermFunction("isSupNat"),
								new TermApplication(
										new TermConstructor("MySucc"),
										new TermVar("x")
								)
						),
						new TermConstructor("MyZero")

				),
				new TermConstructor("MyTrue")


		));

		program.add(new Attribution(
				new TermApplication(
						new TermApplication(
								new TermFunction("isSupNat"),
								new TermConstructor("MyZero")
						),
						new TermAny()
				),
				new TermConstructor("MyFalse")
		));

		// instance Compare MyBool and Compare MyNat
		Instance compareMyBool = new Instance(
				new TypeApplication(
						new TypeVar("Compare"),
						new TypeConstructor("MyBool")
				),
				new Attribution(
						new TermFunction("isSup"),
						new TermFunction("isSupBool")
				)
		);
		program.add(compareMyBool);

		Instance compareMyNat = new Instance(
				new TypeApplication(
						new TypeVar("Compare"),
						new TypeConstructor("MyNat")
				),
				new Attribution(
						new TermFunction("isSup"),
						new TermFunction("isSupNat")
				)
		);
		program.add(compareMyNat);

		// areAllSup
		FunctionDefinition areAllSupDef = new FunctionDefinition("areAllSup",
				new TypeFunction(
						new TypeFunction(
								new TypeFunction(
										new TypeApplication(
												new TypeVar("Compare"),
												new TypeVar("a")
										),
										new TypeApplication(
												new TypeConstructor("MyList"),
												new TypeVar("a")
										)
								),
								new TypeVar("a")
						),
						new TypeConstructor("MyBool")
				)
		);
		program.add(areAllSupDef);

		program.add(new Attribution(
				new TermApplication(
						new TermApplication(
								new TermFunction("areAllSup"),
								new TermConstructor("MyNil")
						),
						new TermVar("n")
				),
				new TermConstructor("MyTrue")
		));

		program.add(new Attribution(
						new TermApplication(
								new TermApplication(
										new TermFunction("areAllSup"),
										new TermApplication(
												new TermApplication(
														new TermConstructor("MyCons"),
														new TermVar("x")
												),
												new TermVar("l"))),
								new TermVar("n")),
						new TermApplication(
								new TermApplication(
										new TermFunction("myAnd"),
										new TermApplication(
												new TermApplication(
														new TermFunction("areAllSup"),
														new TermVar("l")
												),
												new TermVar("n")
										)),
								new TermApplication(
										new TermApplication(
												new TermFunction("isSup"),
												new TermVar("x")
										),
										new TermVar("n")
								)
						)
				)
		);

		/// 2nd example
		Comment com2ndEx = new Comment("Second example : second class with two methods");
		program.add(com2ndEx);

		Type typeVar2 = new TypeConstructor("Parity");
		Type typeParam2 = new TypeVar("a");
		TypeApplication typeApp2 =  new TypeApplication(typeVar2, typeParam2);

		// super types
		List<Type> sts2 = new ArrayList<>();

		FunctionDefinition isOddDef = new FunctionDefinition("isOdd",
				new TypeFunction(new TypeVar("a"),
						new TypeConstructor("MyBool")
				)
		);
		FunctionDefinition isEvenDef = new FunctionDefinition("isEven",
				new TypeFunction(new TypeVar("a"),
						new TypeConstructor("MyBool")
				)
		);

		List<FunctionDefinition> fds2 = new ArrayList<>(Arrays.asList(isOddDef, isEvenDef));

		ClassDefinition cd2 = new ClassDefinition(typeApp2, fds2, sts2);
		program.add(cd2);

		// isOddBool
		FunctionDefinition isOddBoolDef = new FunctionDefinition("isOddBool",
				new TypeFunction(
						new TypeConstructor("MyBool"),
						new TypeConstructor("MyBool")
				)
		);
		program.add(isOddBoolDef);

		program.add(new Attribution(
				new TermApplication(
						new TermFunction("isOddBool"),
						new TermVar("b")
				),
				new TermVar("b")
		));

		// isEvenBool
		FunctionDefinition isEvenBoolDef = new FunctionDefinition("isEvenBool",
				new TypeFunction(
						new TypeConstructor("MyBool"),
						new TypeConstructor("MyBool")
				)
		);
		program.add(isEvenBoolDef);
		program.add(new Attribution(
				new TermApplication(
						new TermFunction("isEvenBool"),
						new TermConstructor("MyTrue")
				),
				new TermConstructor("MyFalse")
		));
		program.add(new Attribution(
				new TermApplication(
						new TermFunction("isEvenBool"),
						new TermConstructor("MyFalse")
				),
				new TermConstructor("MyTrue")
		));

		// isOddNat
		FunctionDefinition isOddNatDef = new FunctionDefinition("isOddNat",
				new TypeFunction(
						new TypeConstructor("MyNat"),
						new TypeConstructor("MyBool")
				)
		);
		program.add(isOddNatDef);
		program.add(new  Attribution(
				new TermApplication(
						new TermFunction("isOddNat"),
						new TermConstructor("MyZero")
				),
				new TermConstructor("MyFalse")
		));
		program.add(new Attribution(
				new TermApplication(
						new TermFunction("isOddNat"),
						new TermApplication(
								new TermConstructor("MySucc"),
								new TermConstructor("MyZero")
						)
				),
				new TermConstructor("MyTrue")
		));
		program.add(new Attribution(
				new TermApplication(
						new TermFunction("isOddNat"),
						new TermApplication(
								new TermConstructor("MySucc"),
								new TermApplication(
										new TermConstructor("MySucc"),
										new TermVar("n")
								)
						)
				),
				new TermApplication(
						new TermConstructor("isOddNat"),
						new TermVar("n")
				)
		));

		// isEvenNat
		FunctionDefinition isEvenNatDef = new FunctionDefinition("isEvenNat",
				new TypeFunction(
						new TypeConstructor("MyNat"),
						new TypeConstructor("MyBool")
				)
		);
		program.add(isEvenNatDef);

		program.add(new Attribution(
				new TermApplication(
						new TermFunction("isEvenNat"),
						new TermConstructor("MyZero")
				),
				new TermConstructor("MyTrue")
		));
		program.add(new Attribution(
				new TermApplication(
						new TermFunction("isEvenNat"),
						new TermApplication(
								new TermConstructor("MySucc"),
								new TermConstructor("MyZero")
						)
				),
				new TermConstructor("MyFalse")
		));
		program.add(new Attribution(
				new TermApplication(
						new TermFunction("isEvenNat"),
						new TermApplication(
								new TermConstructor("MySucc"),
								new TermApplication(
										new TermConstructor("MySucc"),
										new TermVar("n")
								)
						)
				),
				new TermApplication(
						new TermFunction("isEvenNat"),
						new TermVar("n")
				)
		));

		// instance Parity MyBool and Parity MyNat
		Instance parityMyBool = new Instance(
				new TypeApplication(
						new TypeVar("Parity"),
						new TypeConstructor("MyBool")
				),

				new Attribution(
						new TermFunction("isOdd"),
						new TermFunction("isOddBool")
				),
				new Attribution(
						new TermFunction("isEven"),
						new TermFunction("isEvenBool")
				)

		);
		program.add(parityMyBool);
		Instance parityMyNat = new Instance(
				new TypeApplication(
						new TypeVar("Parity"),
						new TypeConstructor("MyNat")
				),

				new Attribution(
						new TermFunction("isOdd"),
						new TermFunction("isOddNat")
				),
				new Attribution(
						new TermFunction("isEven"),
						new TermFunction("isEvenNat")
				)

		);
		program.add(parityMyNat);

		// areAllEven
		FunctionDefinition areAllEvenDef = new FunctionDefinition("areAllEven",
				new TypeFunction(
						new TypeConstraint(
								new TypeApplication(
										new TypeVar("Parity"),
										new TypeVar("a")
								),
								new TypeApplication(
										new TypeConstructor("MyList"),
										new TypeVar("a")
								)
						),
						new TypeConstructor("MyBool")
				)
		);
		program.add(areAllEvenDef);
		program.add(new Attribution(
				new TermApplication(
						new TermFunction("areAllEven"),
						new TermConstructor("MyNil")
				),
				new TermConstructor("MyTrue")
		));
		program.add(new Attribution(
				new TermApplication(
						new TermFunction("areAllEven"),
						new TermApplication(
								new TermApplication(
										new TermConstructor("MyCons"),
										new TermVar("x")
								),
								new TermVar("l")
						)
				),
				new TermApplication(
						new TermFunction("myAnd"),
						new TermApplication(
								new TermApplication(
										new TermFunction("areAllEven"),
										new TermVar("l")
								),
								new TermApplication(
										new TermFunction("isEven"),
										new TermVar("x")
								)
						)
				)
		));

		//Exemple 3

		Comment com3rdEx = new Comment("Third example : subclasses");
		program.add(com3rdEx);

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
		program.add(parity2Def);

		Instance iParity2MyBool = new Instance(//
				new TypeApplication(new TypeConstructor("Parity2"), new TypeConstructor("MyBool")), //
				new Attribution(new TermConstructor("isOdd2"), new TermConstructor("isOddBool")), //
				new Attribution(new TermConstructor("isEven2"), new TermConstructor("isEvenBool"))//
		);
		program.add(iParity2MyBool);

		Instance iParity2MyNat = new Instance(//
				new TypeApplication(new TypeConstructor("Parity2"), new TypeConstructor("MyNat")), //
				new Attribution(new TermConstructor("isOdd2"), new TermConstructor("isOddNat")), //
				new Attribution(new TermConstructor("isEven2"), new TermConstructor("isEvenNat"))//
		);
		program.add(iParity2MyNat);

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
		program.add(allEvenAreSupDef);

		program.add(new Attribution(new TermApplication(
				new TermApplication(new TermConstructor("allEvenAreSup"), new TermConstructor("MyNil")),
				new TermVar("n")), new TermConstructor("MyTrue")));

		program.add(new Attribution(//
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
		));

		return program;
	}

}
