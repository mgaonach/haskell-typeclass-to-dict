package compiler.haskell;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class ConstructorDefinition implements Instruction{

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

	@Override
	public String toHaskell() {
		StringBuilder sb = new StringBuilder(this.getId());
		StringJoiner sj = new StringJoiner(" ");
		for(Type t : this.getParams()){
			sj.add(t.toHaskell());
		}
		sb.append(sj);
		return sb.toString();
	}
}