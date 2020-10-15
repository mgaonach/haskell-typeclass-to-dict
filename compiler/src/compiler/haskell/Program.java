package compiler.haskell;

import java.util.*;

public class Program {

	List<Function> functions;
	List<Class> classes;
	List<Data> dataTypes;

	public Program(List<Function> functions, List<Class> classes, List<Data> dataTypes) {
		this.functions = functions;
		this.classes = classes;
		this.dataTypes = dataTypes;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public List<Class> getClasses() {
		return classes;
	}

	public List<Data> getDataTypes() {
		return dataTypes;
	}

}