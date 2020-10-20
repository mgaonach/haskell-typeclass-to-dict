package compiler.haskell;

import java.util.*;

public class DataInstanceCons extends DataInstance {

	private DataConstructor dataConstructor;
	private List<DataInstance> parameters;

	public DataConstructor getDataConstructor() {
		return this.dataConstructor;
	}

	public List<DataInstance> getParameters() {
		return this.parameters;
	}

	/**
	 * 
	 * @param dataType
	 * @param dataConstructor
	 */
	public DataInstanceCons(Data dataType, DataConstructor dataConstructor) {
		// TODO - implement DataInstanceCons.DataInstanceCons
		throw new UnsupportedOperationException();
	}

}