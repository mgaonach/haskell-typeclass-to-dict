package compiler.haskell;

import java.util.*;

public class Data extends Type {

	private String name;
	private List<Class> deriving = new ArrayList<>();
	private List<GenericType> genericTypes = new ArrayList<>();
	private List<DataConstructor> dataConstructors = new ArrayList<>();

	public String getName() {
		return this.name;
	}

	public List<Class> getDeriving() {
		return this.deriving;
	}

	public List<GenericType> getGenericTypes() {
		return this.genericTypes;
	}

	public List<DataConstructor> getDataConstructors() {
		return this.dataConstructors;
	}

	/**
	 * 
	 * @param name
	 */
	public Data(String name) {
		// TODO - implement Data.Data
		throw new UnsupportedOperationException();
	}

}