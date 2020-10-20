package compiler.haskell;

import java.util.*;

public class DataConstructor {

	private String name;
	private List<Type> types = new ArrayList<>();

	public String getName() {
		return this.name;
	}

	public List<Type> getTypes() {
		return this.types;
	}

	/**
	 * 
	 * @param name
	 */
	public DataConstructor(String name) {
		// TODO - implement DataConstructor.DataConstructor
		throw new UnsupportedOperationException();
	}

}