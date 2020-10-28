package compiler.haskell;

import java.util.List;

public class DataDefinition {

	private Type type;
	private List<ConstructorDefinition> constructorDefinitions;
	private List<Type> superTypes;

	public DataDefinition(Type type, List<ConstructorDefinition> constructorDefinitions, List<Type> superTypes) {
		this.type = type;
		this.constructorDefinitions = constructorDefinitions;
		this.superTypes = superTypes;
	}

	public Type getType() {
		return type;
	}

	public List<ConstructorDefinition> getConstructorDefinitions() {
		return constructorDefinitions;
	}

	public List<Type> getSuperTypes() {
		return superTypes;
	}

}