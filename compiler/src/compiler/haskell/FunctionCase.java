package compiler.haskell;

import java.util.*;

public class FunctionCase {

	public FunctionCase(List<DataInstance> dataInstances, DataInstance dataInstance) {
		this.dataInstances = dataInstances;
		this.dataInstance = dataInstance;
	}

	public List<DataInstance> getDataInstances() {
		return dataInstances;
	}

	public DataInstance getDataInstance() {
		return dataInstance;
	}

	List<DataInstance> dataInstances;
	DataInstance dataInstance;
}