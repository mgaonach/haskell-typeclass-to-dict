package compiler.haskell;

import java.util.List;

public class DataInstanceCons extends DataInstance {

	private DataConstructor dataConstructor;
	private List<DataInstance> parameters;

	public DataInstanceCons(Data dataType, DataConstructor dataConstructor) {
		super(dataType);
		this.dataConstructor = dataConstructor;
	}

	public DataConstructor getDataConstructor() {
		return dataConstructor;
	}

	public List<DataInstance> getParameters() {
		return parameters;
	}

}