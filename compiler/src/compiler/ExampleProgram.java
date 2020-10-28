package compiler;

import compiler.haskell.*;

import java.util.ArrayList;
import java.util.List;

public class ExampleProgram {

	public static void getAST() {
		/// 1st example

		// type Application
		Type typeVar = new TypeVar("Compare");
		Type typeParam = new TypeVar("a");
		TypeApplication typeApp =  new TypeApplication(typeVar, typeParam);

		// super types
		List<Type> sts = new ArrayList<>();

		// function Definition
		List<FunctionDefinition> fds = new ArrayList<>();
		fds.add(new FunctionDefinition("isSup")); // TODO preciser la definition de fonction

		// class definition
		// "class Compare a where
		//    isSup :: a -> a -> MyBool"
		ClassDefinition cd = new ClassDefinition(typeApp, fds, sts);

		// function Definition
		FunctionDefinition isSupBool = new FunctionDefinition("isSupBool");
		FunctionDefinition isSupNat = new FunctionDefinition("isSupNat");

	}

}
