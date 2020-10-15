package compiler.haskell;

import java.util.ArrayList;
import java.util.List;

public class Class {

	private String name;
	private List<GenericType> genericTypes = new ArrayList<>();

	private List<Class> supertypes = new ArrayList<>();

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

}