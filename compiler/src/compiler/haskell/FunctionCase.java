package compiler.haskell;

import java.util.Arrays;
import java.util.List;

public class FunctionCase {

	private List<DataInstance> params;
	private DataInstance result;

	public FunctionCase(DataInstance result, DataInstance... params) {
		super();
		this.result = result;
		this.params = Arrays.asList(params);
	}

	public List<DataInstance> getParams() {
		return params;
	}

	public DataInstance getResult() {
		return result;
	}

}