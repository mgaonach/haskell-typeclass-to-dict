package compiler.haskell;

public class TypeVar extends Type {

	private String id;

	public TypeVar(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	@Override
	public String toSimpleStringName() {
		return id;
	}

	@Override
	public String toHaskell() {
		return this.getId();
	}
}