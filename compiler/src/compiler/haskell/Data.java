package compiler.haskell;

import java.util.ArrayList;
import java.util.List;

public class Data extends Type {

	private String name;
	private List<Class> deriving = new ArrayList<>();
	private List<GenericType> genericTypes = new ArrayList<>();
	private List<DataConstructor> dataConstructors = new ArrayList<>();

	public Data(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Class> getDeriving() {
		return deriving;
	}

	public List<GenericType> getGenericTypes() {
		return genericTypes;
	}

	public List<DataConstructor> getDataConstructors() {
		return dataConstructors;
	}

}