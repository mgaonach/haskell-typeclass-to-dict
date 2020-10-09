package compiler.haskell;

import java.util.ArrayList;
import java.util.List;

public class Data implements Type {

	private String name;

	private Class deriving;

	private List<DataConstructor> constructors = new ArrayList<>();

	public Data(String name, Class deriving) {
		super();
		this.name = name;
		this.deriving = deriving;
	}

	public Data(String name) {
		this(name, null);
	}

	public String getName() {
		return name;
	}

	public Class getDeriving() {
		return deriving;
	}

	public List<DataConstructor> getConstructors() {
		return constructors;
	}

}
