package compiler.haskell;

public abstract class Term implements Instruction {

	public Term() {
	}

	@Override
	public String toString() {
		return toHaskell();
	}

}