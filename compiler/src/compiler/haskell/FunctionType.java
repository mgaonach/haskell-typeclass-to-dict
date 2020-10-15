package compiler.haskell;

import java.util.*;

public class FunctionType {

	List<Function> functions;

	public FunctionType(List<Function> functions) {
		this.functions = functions;
	}

	public List<Function> getFunctions() {
		return functions;
	}


}