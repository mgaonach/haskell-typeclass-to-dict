package compiler.haskell;

import java.util.*;

public class DataInstanceFunction extends DataInstance {

	private Function function;
	private List<DataInstance> parameters = new ArrayList<>();

	public Function getFunction() {
		return this.function;
	}

	public List<DataInstance> getParameters() {
		return this.parameters;
	}

	/**
	 * 
	 * @param dataType
	 * @param function
	 */
	public DataInstanceFunction(Data dataType, Function function) {
		// TODO - implement DataInstanceFunction.DataInstanceFunction
		throw new UnsupportedOperationException();
	}

}