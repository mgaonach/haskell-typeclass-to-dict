package compiler.haskell;

import java.util.ArrayList;
import java.util.List;

public class Function {

	private String name;
	private FunctionType type;
	private List<FunctionCase> functionCases = new ArrayList<>();

	public Function(String name, FunctionType type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public List<FunctionCase> getFunctionCases() {
		return functionCases;
	}

	public FunctionType getType() {
		return type;
	}

}