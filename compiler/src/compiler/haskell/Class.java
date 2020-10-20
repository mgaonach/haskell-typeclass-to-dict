package compiler.haskell;

import java.util.*;

public class Class {

	private String name;
	private List<GenericType> genericTypes = new ArrayList<>();
	private List<Class> supertypes = new ArrayList<>();
	private Map<String, FunctionType> functions = new HashMap<>();

	public String getName() {
		return this.name;
	}

	public List<GenericType> getGenericTypes() {
		return this.genericTypes;
	}

	public List<Class> getSupertypes() {
		return this.supertypes;
	}

	public Map<String, FunctionType> getFunctions() {
		return this.functions;
	}

	/**
	 * 
	 * @param name
	 */
	public Class(String name) {
		// TODO - implement Class.Class
		throw new UnsupportedOperationException();
	}

}