package compiler.haskell;

public abstract class Type implements Instruction {

	public Type() {
	}

	public abstract String toSimpleStringName();

	@Override
	public String toString() {
		return toHaskell();
	}

}