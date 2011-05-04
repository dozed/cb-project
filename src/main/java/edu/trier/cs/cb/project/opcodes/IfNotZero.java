package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class IfNotZero extends AbstractOpcode implements Opcode {

	IfNotZero() {}
	
	public void touch(Instruction i) {
		int p = i.getArg1();
		int r = machine.pop();
		log.debug("ifnzero: " + (r != 0));
		if (r != 0) {
			machine.setPC(p);
		} else {
			machine.incPC();
		}
	}

}
