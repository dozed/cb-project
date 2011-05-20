package edu.trier.cs.cb.project.machine;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.trier.cs.cb.project.machine.AbstractMachine;
import edu.trier.cs.cb.project.machine.Instruction;

public class AddTest {

	@Test
	public void simpleAdd() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 1),
			new Instruction(Instruction.CONST, 2),
			new Instruction(Instruction.ADD),
			new Instruction(Instruction.HALT),
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
		
		m.printStack();
	}
	
}
