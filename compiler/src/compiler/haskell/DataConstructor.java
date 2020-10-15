package compiler.haskell;

import java.util.ArrayList;
import java.util.List;

public class DataConstructor {

	private String name;
	private List<Type> types = new ArrayList<>();

	public DataConstructor(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Type> getTypes() {
		return types;
	}

}