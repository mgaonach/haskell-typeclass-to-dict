package compiler.haskell;

public class GenericType extends Type {

	private String name;

	public GenericType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}