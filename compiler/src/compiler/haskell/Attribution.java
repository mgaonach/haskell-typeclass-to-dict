package compiler.haskell;

import java.util.*;

public class Attribution {

	private Term left;
	private Term right;

	public Attribution(Term left, Term right) {
		this.left = left;
		this.right = right;
	}

	public Term getLeft() {
		return left;
	}

	public Term getRight() {
		return right;
	}

}