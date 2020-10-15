package compiler.haskell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Class {

	private String name;
	private List<GenericType> genericTypes = new ArrayList<>();
	private List<Class> supertypes = new ArrayList<>();
	private Map<String, FunctionType> functions = new HashMap<>();

	public Class(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<GenericType> getGenericTypes() {
		return genericTypes;
	}

	public List<Class> getSupertypes() {
		return supertypes;
	}

	public Map<String, FunctionType> getFunctions() {
		return functions;
	}

}