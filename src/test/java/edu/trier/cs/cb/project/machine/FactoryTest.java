package edu.trier.cs.cb.project.machine;

import org.junit.Test;

import edu.trier.cs.cb.project.machine.AbstractMachine;
import edu.trier.cs.cb.project.machine.opcodes.Add;
import edu.trier.cs.cb.project.machine.opcodes.Opcode;

public class FactoryTest {

	@SuppressWarnings("unused")
	@Test
	public void test() {
		AbstractMachine m = new AbstractMachine();
		Add a = Opcode.Factory.get(Add.class, m);
	}
	
}
