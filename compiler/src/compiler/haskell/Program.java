package compiler.haskell;

import java.util.ArrayList;
import java.util.List;

public class Program {

	private List<Function> functions = new ArrayList<>();
	private List<Data> dataTypes = new ArrayList<>();

	public List<Function> getFunctions() {
		return functions;
	}

	public List<Data> getDataTypes() {
		return dataTypes;
	}

}
