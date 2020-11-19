package compiler.haskell;

import java.util.List;
import java.util.stream.Collectors;

public class ClassDefinition implements Instruction {

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
		StringBuilder sb = new StringBuilder("class " + this.getType().toHaskell());
		// super types
		if (!this.getSuperTypes().isEmpty())
			sb.append(this.getSuperTypes().stream().map(st -> st.toHaskell())
					.collect(Collectors.joining(", ", " => ", "")));
		sb.append(" where");
		// function definitions
		if (!this.getFunctionDefinitions().isEmpty())
			sb.append(this.getFunctionDefinitions().stream().map(fd -> fd.toHaskell())
					.collect(Collectors.joining("\n   ", "\n   ", "\n")));
		return sb.toString();
	}

	@Override
	public String toString() {
		return toHaskell();
	}
}