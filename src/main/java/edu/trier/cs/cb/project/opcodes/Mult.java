package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class Mult extends AbstractOpcode implements Opcode {

	Mult() {}
	
	public void touch(Instruction i) {
		int a = machine.pop();
		int b = machine.pop();
		int c = a * b;
		log.debug("multiplying: " + a + " * " + b + " = " + c);
		machine.push(c);
		machine.incPC();
	}

}
