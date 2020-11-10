package compiler.haskell;

public class TermAny extends Term {

	public TermAny() {
	}

	@Override
	public String toHaskell() {
		return "_";
	}
}