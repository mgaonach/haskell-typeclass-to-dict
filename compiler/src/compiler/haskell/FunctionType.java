package compiler.haskell;

import java.util.Arrays;
import java.util.List;

public class FunctionType {

	private List<Type> types;

	FunctionType(Type... types) {
		this.types = Arrays.asList(types);
	}

}