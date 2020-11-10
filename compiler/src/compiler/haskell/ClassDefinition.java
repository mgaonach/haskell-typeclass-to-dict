package compiler.haskell;

import java.util.List;

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

}