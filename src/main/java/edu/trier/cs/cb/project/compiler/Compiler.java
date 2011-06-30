package edu.trier.cs.cb.project.compiler;

import java.util.ArrayList;
import java.util.List;

import edu.trier.cs.cb.project.machine.Instruction;
import edu.trier.cs.cb.project.parser.Let;

public class Compiler {

	public List<Instruction> compile(Let l) {
		List<Instruction> instructions = new ArrayList<Instruction>();
		AddressEnvironment rho = new AddressEnvironment();
		l.code(instructions, rho, 0);
		instructions.add(new Instruction(Instruction.HALT));
		
		for (int i = 0; i < instructions.size(); i++) {
			Instruction ins = instructions.get(i);
			if (ins.hasLabel()) {
				ins.getLabel().setAddress(i);
			}
		}
		
		return instructions;
	}
	
}
