package compiler.haskell;

import java.util.ArrayList;
import java.util.List;

public class FunctionCase {

	private List<DataInstance> params = new ArrayList<>();
	private DataInstance result;

	public FunctionCase(DataInstance result) {
		super();
		this.result = result;
	}

	public List<DataInstance> getParams() {
		return params;
	}

	public DataInstance getResult() {
		return result;
	}

}