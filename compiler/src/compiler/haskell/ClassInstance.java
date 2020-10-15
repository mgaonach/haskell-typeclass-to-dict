package compiler.haskell;

import java.util.HashMap;
import java.util.Map;

public class ClassInstance {

	private Map<String, Function> functions = new HashMap<>();

	public Map<String, Function> getFunctions() {
		return functions;
	}

}