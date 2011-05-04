package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class Nop extends AbstractOpcode implements Opcode {

	Nop() {}
	
	public void touch(Instruction i) {
		log.debug("nop");
		machine.incPC();
	}

}
