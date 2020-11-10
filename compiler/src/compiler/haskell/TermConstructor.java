package compiler.haskell;

public class TermConstructor extends Term {

	private String id;

	public TermConstructor(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toHaskell() {
		return this.getId();
	}
}