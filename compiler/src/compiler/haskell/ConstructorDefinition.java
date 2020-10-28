package compiler.haskell;

import java.util.Arrays;
import java.util.List;

public class ConstructorDefinition {

	private List<Type> params;
	private String id;

	public ConstructorDefinition(String id, List<Type> params) {
		this.params = params;
		this.id = id;
	}

	public ConstructorDefinition(String id, Type... params) {
		this(id, Arrays.asList(params));
	}

	public List<Type> getParams() {
		return params;
	}

	public String getId() {
		return id;
	}

}