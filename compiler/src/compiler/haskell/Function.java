package compiler.haskell;

import java.util.*;

public class Function {

	private String name;
	FunctionType functions;
	List<FunctionCase> functionCases;

	public Function(String name, FunctionType functions, List<FunctionCase> functionCases) {
		this.name = name;
		this.functions = functions;
		this.functionCases = functionCases;
	}

	public String getName() {
		return name;
	}

	public FunctionType getFunctions() {
		return functions;
	}

	public List<FunctionCase> getFunctionCases() {
		return functionCases;
	}

}