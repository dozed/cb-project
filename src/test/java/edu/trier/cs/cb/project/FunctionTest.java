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
	public void squareTest() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 10),
			//new Instruction(Instruction.STORE, 0, 0),
			new Instruction(Instruction.INVOKE, 1, 3, 0),
			new Instruction(Instruction.HALT),
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.MULT),
			new Instruction(Instruction.RETURN),
			new Instruction(Instruction.HALT)
		});
		
		AbstractMachine m = new AbstractMachine();
		m.execute(instr);
		
		m.printStack();
	}
	
	/*
	 * (Beispiel durchspielen) Quellkode: let fac(x) { if (x == 1) 1 else
	 * f(x-1)*x } in fac(3) Annahme: PP=0, FP=0, TOP=0.
	 */
	@Test
	public void factorialTest() {
		List<Instruction> instr = Arrays.asList(new Instruction[] {
			new Instruction(Instruction.CONST, 3),
			new Instruction(Instruction.INVOKE, 1, 3, 0), // --> fac
			new Instruction(Instruction.HALT),
			// fac
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.CONST, 1),
			new Instruction(Instruction.SUB),
			new Instruction(Instruction.IFZERO, 14), // --> iftrue
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.CONST, 1),
			new Instruction(Instruction.SUB),
			new Instruction(Instruction.INVOKE, 1, 3, 0), // --> fac
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.MULT),
			new Instruction(Instruction.RETURN),
			// iftrue
			new Instruction(Instruction.CONST, 1),
			new Instruction(Instruction.RETURN)
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
		m.execute(Instruction.program3);
		m.printStack();
	}

	@Test
	public void program4() {
		AbstractMachine m = new AbstractMachine();
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
