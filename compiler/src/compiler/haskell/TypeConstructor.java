package compiler.haskell;

public class TypeConstructor extends Type {

	private String id;

	public TypeConstructor(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}