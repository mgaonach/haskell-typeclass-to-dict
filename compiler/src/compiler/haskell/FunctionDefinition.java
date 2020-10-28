package compiler.haskell;

public class FunctionDefinition {

	private String Id;

	/**
	 * 
	 * @param id
	 */
	public FunctionDefinition(String id) {
		Id = id;
	}

	public String getId() {
		return Id;
	}
}