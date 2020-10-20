package compiler.haskell;

import java.util.*;

public class ClassInstance {

	private Map<String, Function> functions = new HashMap<>();

	public Map<String, Function> getFunctions() {
		return this.functions;
	}

}