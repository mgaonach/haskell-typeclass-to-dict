package compiler.haskell;

import java.util.List;

public class ConstructorDefinition {

	private List<Type> params;
	private String id;

	public ConstructorDefinition(List<Type> params, String id) {
		this.params = params;
		this.id = id;
	}

	public List<Type> getParams() {
		return params;
	}

	public String getId() {
		return id;
	}

}