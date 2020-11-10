package compiler.haskell;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Program {

	private String name;
	private List<Instruction> instructions = new ArrayList<>();

	public Program(String name) {
		this.name = name;
	}

	public void add(Instruction instruction) {
		this.instructions.add(instruction);

	}

	public String getName() {
		return name;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public Program convertClassToDict() {
		return this;
	}

	public String toHaskell(){
		StringBuilder sb = new StringBuilder("-- Notre example de programme\n");
		StringJoiner sj = new StringJoiner("\n");
		for (Instruction i : this.getInstructions()){
			sj.add(i.toHaskell());
		}
		sb.append(sj);
		return sb.toString();
	}
}
