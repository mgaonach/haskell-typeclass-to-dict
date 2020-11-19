package compiler.haskell;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

//Instance := Type(only App) Attribution*
public class Instance implements Instruction {

	private TypeApplication instanceType;
	private List<Attribution> attributions;

	public Instance(TypeApplication instanceType, Attribution... attributions) {
		this.instanceType = instanceType;
		this.attributions = Arrays.asList(attributions);
	}

	public TypeApplication getInstanceType() {
		return instanceType;
	}

	public List<Attribution> getAttributions() {
		return attributions;
	}

	@Override
	public String toHaskell() {
		StringBuilder sb = new StringBuilder("instance " + this.getInstanceType().toHaskell());
		StringJoiner sj = new StringJoiner("\n   ", " where\n   ", "\n");
		for (Attribution a : this.getAttributions()) {
			sj.add(a.toHaskell());
		}
		sb.append(sj);
		return sb.toString();
	}

	@Override
	public String toString() {
		return toHaskell();
	}
}
