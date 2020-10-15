package compiler.haskell;

public class DataInstanceVar extends DataInstance {

	private String name;

	public DataInstanceVar(Data dataType, String name) {
		super(dataType);
		this.name = name;
	}

	public String getName() {
		return name;
	}

}