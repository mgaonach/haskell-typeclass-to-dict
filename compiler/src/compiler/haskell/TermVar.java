package compiler.haskell;

public class TermVar extends Term {

	private String id;

	public TermVar(String id) {
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