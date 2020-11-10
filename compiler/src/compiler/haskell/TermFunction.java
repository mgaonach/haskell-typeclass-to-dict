package compiler.haskell;

public class TermFunction extends Term {

	private String id;

	public TermFunction(String id) {
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