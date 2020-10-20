package compiler.haskell;

import java.util.*;

public class Function {

	private String name;
	private FunctionType type;
	private List<FunctionCase> functionCases = new ArrayList<>();

	public String getName() {
		return this.name;
	}

	public FunctionType getType() {
		return this.type;
	}

	public List<FunctionCase> getFunctionCases() {
		return this.functionCases;
	}

	/**
	 * 
	 * @param name
	 * @param type
	 */
	public Function(String name, FunctionType type) {
		// TODO - implement Function.Function
		throw new UnsupportedOperationException();
	}

}