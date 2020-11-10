package compiler.haskell;

import java.util.List;
import java.util.StringJoiner;

public class DataDefinition implements Instruction {

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

	@Override
	public String toHaskell() {
		StringBuilder sb = new StringBuilder("\ndata " + this.getType().toHaskell() + " = ");
		// constructor definitions
		StringJoiner sjConsDef = new StringJoiner("\n\t\t| ", "", "");
		for (ConstructorDefinition cd : this.getConstructorDefinitions()) {
			sjConsDef.add(cd.toHaskell());
		}
		sb.append(sjConsDef);
		// super types
		StringJoiner sjSuperTypes = new StringJoiner(", ", " deriving ", "");
		for (Type t : this.getSuperTypes()) {
			sjSuperTypes.add(t.toHaskell());
		}
		sb.append(sjSuperTypes);
		return sb.toString();
	}
}