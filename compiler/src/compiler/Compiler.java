package compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import compiler.haskell.Attribution;
import compiler.haskell.ClassDefinition;
import compiler.haskell.ConstructorDefinition;
import compiler.haskell.DataDefinition;
import compiler.haskell.FunctionDefinition;
import compiler.haskell.Instance;
import compiler.haskell.Instruction;
import compiler.haskell.Program;
import compiler.haskell.Term;
import compiler.haskell.TermApplication;
import compiler.haskell.TermConstructor;
import compiler.haskell.TermVar;
import compiler.haskell.Type;
import compiler.haskell.TypeApplication;
import compiler.haskell.TypeConstraint;
import compiler.haskell.TypeConstructor;
import compiler.haskell.TypeFunction;
import utils.Pair;

public class Compiler {

	public static void main(String[] args) {
		new Compiler().compile(ExampleProgram.getAST());
	}

	private List<String> replacedClassConsNames = new ArrayList<>();
	private List<String> replacedFunctionsNames = new ArrayList<>();

	public void compile(Program program) {
		replacedClassConsNames = new ArrayList<>();
		replacedFunctionsNames = new ArrayList<>();

		List<Instruction> is = program.getInstructions().stream()//
				.flatMap(this::replaceClassDefinition)//
				.flatMap(this::replaceInstance)//
				.flatMap(this::replaceFunctionDef)//
				.collect(Collectors.toList());

		Program res = new Program(program.getName() + "_compiled");
		res.getInstructions().addAll(is);
	}

	private Stream<Instruction> replaceClassDefinition(Instruction is) {
		if (is instanceof ClassDefinition) {
			ClassDefinition cd = (ClassDefinition) is;
			List<Instruction> res = new ArrayList<>();
			Pair<String, Type> consAndTranslation = getDataTypeTranslation(cd.getType());
			String constructorName = consAndTranslation.first();
			Type dataTranslation = consAndTranslation.second();
			replacedClassConsNames.add(constructorName);
			ConstructorDefinition constructor = new ConstructorDefinition(//
					constructorName, //
					cd.getFunctionDefinitions().stream()//
							.map(f -> f.getType())//
							.collect(Collectors.toList())//
			);
			DataDefinition dict = new DataDefinition(dataTranslation, Collections.singletonList(constructor),
					Collections.emptyList());
			res.add(dict);

			Term accesorDictPatern = buildAccessorDictPatern(constructorName, cd.getFunctionDefinitions().size());

			for (int i = 0; i < cd.getFunctionDefinitions().size(); i++) {

				TermApplication gauche = new TermApplication(//
						new TermConstructor(cd.getFunctionDefinitions().get(i).getId() + "f"), //
						accesorDictPatern//
				);
				char var = (char) (cd.getFunctionDefinitions().size() - i - 1 + 'a');
				Attribution accessor = new Attribution(//
						gauche, //
						new TermVar(String.valueOf(var))//
				);
				res.add(accessor);
			}

			return res.stream();
		}
		return Collections.singletonList(is).stream();
	}

	private Stream<Instruction> replaceInstance(Instruction is) {
		if (is instanceof Instance) {
			Instance instance = (Instance) is;
			Pair<String, Type> consAndTranslation = getDataTypeTranslation(instance.getInstanceType());
			String constructorName = consAndTranslation.first();
			Type dataTranslation = consAndTranslation.second();
			if (replacedClassConsNames.contains(constructorName)) {
				List<Instruction> res = new ArrayList<>();
				String functionName = dataTranslation.toSimpleStringName();
				FunctionDefinition def = new FunctionDefinition(functionName, dataTranslation);
				res.add(def);
				for (Attribution attr : instance.getAttributions()) {
					res.add(//
							new Attribution(//
									new TermConstructor(functionName), //
									new TermApplication(//
											new TermConstructor(constructorName), //
											attr.getRight()//
									)//
							)//
					);
				}
				return res.stream();
			}
		}
		return Collections.singletonList(is).stream();
	}

	private Stream<Instruction> replaceFunctionDef(Instruction is) {
		if (is instanceof FunctionDefinition) {
			FunctionDefinition fDef = (FunctionDefinition) is;
			if (fDef.getType() instanceof TypeConstraint) {
				TypeConstraint typeConstraint = (TypeConstraint) fDef.getType();
				Pair<String, Type> consAndTranslation = getDataTypeTranslation(typeConstraint.getGauche());
				String constructorName = consAndTranslation.first();
				Type dataTranslation = consAndTranslation.second();

				if (replacedClassConsNames.contains(constructorName)) {
					List<Instruction> res = new ArrayList<>();
					res.add(//
							new FunctionDefinition(//
									fDef.getId() + "'", //
									new TypeFunction(dataTranslation, typeConstraint.getDroite()) //
							)//
					);
					replacedFunctionsNames.add(fDef.getId());
					return res.stream();
				}
			}
		}
		return Collections.singletonList(is).stream();
	}

	private TermApplication buildAccessorDictPatern(String cons, int vars) {
		if (vars == 1)
			return new TermApplication(new TermConstructor(cons), new TermVar("a"));
		char var = (char) ('a' + vars - 1);
		return new TermApplication(buildAccessorDictPatern(cons, vars - 1), new TermVar(String.valueOf(var)));
	}

	private Pair<String, Type> getDataTypeTranslation(Type type) {
		if (type instanceof TypeApplication) {
			TypeApplication typeApp = (TypeApplication) type;
			if (typeApp.getGauche() instanceof TypeConstructor) {
				TypeConstructor gauche = (TypeConstructor) typeApp.getGauche();
				String name = gauche.getId();
				Type resType = new TypeApplication(new TypeConstructor(name + "D"), typeApp.getDroite());
				return new Pair<>(name + "Dict", resType);
			}
		}
		return new Pair<>("", type);
	}

}
