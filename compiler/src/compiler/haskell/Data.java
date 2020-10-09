package compiler.haskell;

import java.util.*;

public class Data extends Type {

	private String name;
	Collection<Class> classes;
	Collection<GenericType> genericTypes;
	Collection<DataConstructor> dataConstructors;

}