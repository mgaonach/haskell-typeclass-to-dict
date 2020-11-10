package compiler.haskell;

import java.util.List;
import java.util.StringJoiner;

public class ClassDefinition implements Instruction{

	private Type type;
	private List<FunctionDefinition> functionDefinitions;
	private List<Type> superTypes;

	public ClassDefinition(Type type, List<FunctionDefinition> functionDefinitions, List<Type> superTypes) {
		this.type = type;
		this.functionDefinitions = functionDefinitions;
		this.superTypes = superTypes;
	}

	public Type getType() {
		return type;
	}

	public List<FunctionDefinition> getFunctionDefinitions() {
		return functionDefinitions;
	}

	public List<Type> getSuperTypes() {
		return superTypes;
	}

	@Override
	public String toHaskell() {
		StringBuilder sb = new StringBuilder("class " + this.getType());
		// super types
		StringJoiner sjSuperTypes = new StringJoiner(", ", " => ", "");
		for (Type t : this.getSuperTypes()){
			sjSuperTypes.add(t.toHaskell());
		}
		sb.append(sjSuperTypes);
		sb.append(" where\n");
		// function definitions
		StringJoiner sjFuncDef = new StringJoiner("\n\t", "", "\n");
		for (FunctionDefinition fd : this.functionDefinitions){
			sjFuncDef.add(fd.toHaskell());
		}
		sb.append(sjFuncDef);
		return sb.toString();
	}
}