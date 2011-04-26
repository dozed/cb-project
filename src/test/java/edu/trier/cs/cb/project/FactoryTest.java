package edu.trier.cs.cb.project;

import org.junit.Test;

import edu.trier.cs.cb.project.opcodes.Add;
import edu.trier.cs.cb.project.opcodes.Opcode;

public class FactoryTest {

	@Test
	public void test() {
		AbstractMachine m = new AbstractMachine();
		Add a = Opcode.Factory.get(Add.class, m);
		a.touch(null);
	}
	
}
