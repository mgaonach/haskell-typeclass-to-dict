package compiler.haskell;

import java.util.ArrayList;
import java.util.List;

public class Program {

	private String name;
	private List<Instruction> instructions = new ArrayList<>();

	public Program(String name) {
		this.name = name;
	}

	public void add(Instruction instruction) {
		this.instructions.add(instruction);

	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public String getName() {
		return name;
	}

}
