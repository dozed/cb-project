package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class IfNeq extends AbstractOpcode implements Opcode {

	IfNeq() {}
	
	public void touch(Instruction i) {
		int a = machine.pop();
		int b = machine.pop();
		int c;
		if (a != b) {
			c = 1;
		} else {
			c = 0;
		}
		log.debug("lt compare: " + a + " != " + b + " = " + c);
		machine.push(c);
		machine.incPC();
	}

}