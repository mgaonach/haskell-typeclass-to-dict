package compiler.haskell;

public class FunctionDefinition  implements Instruction{

	private String id;
	private Type type;

	public FunctionDefinition(String id, Type type) {
		this.id = id;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public Type getType() {
		return type;
	}
}