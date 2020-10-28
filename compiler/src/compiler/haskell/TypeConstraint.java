package compiler.haskell;

public class TypeConstraint extends Type {

	private TypeApplication application;
	private Type type;

	public TypeConstraint(TypeApplication application, Type type) {
		this.application = application;
		this.type = type;
	}

	public TypeApplication getApplication() {
		return application;
	}

	public Type getType() {
		return type;
	}
}