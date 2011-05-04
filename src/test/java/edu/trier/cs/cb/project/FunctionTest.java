package edu.trier.cs.cb.project;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FunctionTest {

	@Test
	public void gotoTest() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.INVOKE, 1, 3, 0),
			new Instruction(Instruction.HALT),
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.MULT),
			new Instruction(Instruction.RETURN),
			new Instruction(Instruction.HALT),
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
		m.printStack();
	}

	@Test
	public void program1() {
		AbstractMachine m = new AbstractMachine();
		m.execute(Instruction.program1);
		m.printStack();
	}
	
	@Test
	public void program2() {
		AbstractMachine m = new AbstractMachine();
		m.execute(Instruction.program2);
		m.printStack();
	}

	@Test
	public void program3() {
		AbstractMachine m = new AbstractMachine();
		m.reserveSpace(1);
		m.execute(Instruction.program3);
		m.printStack();
	}

	@Test
	public void program4() {
		AbstractMachine m = new AbstractMachine();
		m.reserveSpace(1);
		m.execute(Instruction.program4);
		m.printStack();
	}

	@Test
	public void factorial() {
		AbstractMachine m = new AbstractMachine();
		m.execute(Instruction.factorial);
		m.printStack();
	}
}
