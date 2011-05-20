package edu.trier.cs.cb.project.machine;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import edu.trier.cs.cb.project.machine.AbstractMachine;
import edu.trier.cs.cb.project.machine.Instruction;

public class ControlFlowTest {

	@Test
	public void gotoTest() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.GOTO, 3),
			new Instruction(Instruction.CONST, 20),
			new Instruction(Instruction.CONST, 30),
			new Instruction(Instruction.HALT),
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
		
		m.printStack();
	}

	@Test
	public void ifZeroTest() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 10),	// 0
			new Instruction(Instruction.CONST, 15),	// 1
			new Instruction(Instruction.IFEQ),		// 2
			new Instruction(Instruction.IFNZERO, 5),// 3
			new Instruction(Instruction.CONST, 20),	// 4
			new Instruction(Instruction.GOTO, 7),	// 5
			new Instruction(Instruction.CONST, 30),	// 6
			new Instruction(Instruction.HALT),
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
		m.printStack();
		
		int r = m.peek();
		Assert.assertEquals(20, r);
	}
	
	@Test
	public void ifZeroTest2() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 10),	// 0
			new Instruction(Instruction.CONST, 10),	// 1
			new Instruction(Instruction.IFEQ),		// 2
			new Instruction(Instruction.IFNZERO, 6),// 3
			new Instruction(Instruction.CONST, 20),	// 4
			new Instruction(Instruction.GOTO, 7),	// 5
			new Instruction(Instruction.CONST, 30),	// 6
			new Instruction(Instruction.HALT),
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
		m.printStack();
		
		int r = m.peek();
		Assert.assertEquals(30, r);
	}
	
	/**
	 * (1) const 10
	 * (2) sto 0
	 * (3) load 0
	 * (4) ifzero 7
	 * (5) const 200
	 * (6) goto 8
	 * (7) const 100
	 * (8) nop
	 * (9) const 3
	 * (10) halt
	 */
	@Test
	public void sample1() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 10),		// 0
			new Instruction(Instruction.STORE, 0),		// 1
			new Instruction(Instruction.LOAD, 0),		// 2
			new Instruction(Instruction.IFZERO, 7),		// 3
			new Instruction(Instruction.CONST, 200),	// 4
			new Instruction(Instruction.GOTO, 8),		// 5
			new Instruction(Instruction.CONST, 100),	// 6
			new Instruction(Instruction.NOP),			// 7
			new Instruction(Instruction.CONST, 3),		// 8
			new Instruction(Instruction.HALT),
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
		m.printStack();
		
		int r = m.peek(-1);
		Assert.assertEquals(200, r);
	}
	
}
