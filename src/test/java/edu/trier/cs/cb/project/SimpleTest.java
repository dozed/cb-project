package edu.trier.cs.cb.project;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SimpleTest {

	@Test
	public void simpleTest() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.CONST, 20),
			new Instruction(Instruction.CONST, 30),
			new Instruction(Instruction.CONST, 40),
			new Instruction(Instruction.STORE, 10),
			new Instruction(Instruction.CONST, 35),
			new Instruction(Instruction.LOAD, 10),
			new Instruction(Instruction.ADD),
			new Instruction(Instruction.SUB),
			new Instruction(Instruction.MULT),
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.CONST, 20),
			new Instruction(Instruction.DIV),
			new Instruction(Instruction.CONST, 0),
			new Instruction(Instruction.CONST, 0),
			new Instruction(Instruction.CONST, 0),
			new Instruction(Instruction.HALT),
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
		
		m.printStack();
	}
	
	@Test
	public void ppTest() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 10),	// x
			new Instruction(Instruction.CONST, 20),	// y
			new Instruction(Instruction.LOAD, 0),
			new Instruction(Instruction.CONST, 3),
			new Instruction(Instruction.MULT),
			new Instruction(Instruction.CONST, 5),
			new Instruction(Instruction.CONST, 2),
			new Instruction(Instruction.MULT),
			new Instruction(Instruction.ADD),
			new Instruction(Instruction.STORE, 1),
			new Instruction(Instruction.HALT),
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
		
		m.printStack();
	}

	@Test
	public void ltTest() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.CONST, 20),
			new Instruction(Instruction.IFLT),
			new Instruction(Instruction.HALT),
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
		m.printStack();

		int r = m.peek();
		Assert.assertEquals(0, r);
	}

	@Test
	public void ltTest2() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 20),
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.IFLT),
			new Instruction(Instruction.HALT),
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
		m.printStack();

		int r = m.peek();
		Assert.assertEquals(1, r);
	}

	@Test
	public void gtTest() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.CONST, 20),
			new Instruction(Instruction.IFGT),
			new Instruction(Instruction.HALT),
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
		m.printStack();

		int r = m.peek();
		Assert.assertEquals(1, r);
	}

	@Test
	public void eqTest() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.IFEQ),
			new Instruction(Instruction.HALT),
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
		m.printStack();

		int r = m.peek();
		Assert.assertEquals(1, r);
	}

	@Test
	public void neqTest() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.IFNEQ),
			new Instruction(Instruction.HALT),
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
		m.printStack();

		int r = m.peek();
		Assert.assertEquals(0, r);
	}
}
