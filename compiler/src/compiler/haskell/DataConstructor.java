package compiler.haskell;

import java.util.ArrayList;
import java.util.List;

public class DataConstructor {

	private String name;
	private List<Type> params = new ArrayList<>();

	public DataConstructor(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Type> getParams() {
		return params;
	}

	public DataConstructor addParam(Type param) {
		this.params.add(param);
		return this;
	}

}