package edu.trier.cs.cb.project;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AddTest {

	@Test
	public void simpleAdd() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.ADD, 1, 2)
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
	}
	
}
