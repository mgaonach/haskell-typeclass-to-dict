package compiler.haskell;

import java.util.ArrayList;
import java.util.List;

public class DataInstanceFunction extends DataInstance {

	private Function function;
	private List<DataInstance> parameters = new ArrayList<>();

	public DataInstanceFunction(Data dataType, Function function) {
		super(dataType);
		this.function = function;
	}

	public Function getFunction() {
		return function;
	}

	public List<DataInstance> getParameters() {
		return parameters;
	}

}