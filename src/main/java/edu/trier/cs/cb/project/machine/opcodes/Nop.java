package edu.trier.cs.cb.project.machine.opcodes;

import edu.trier.cs.cb.project.machine.Instruction;


public class Nop extends AbstractOpcode implements Opcode {

	Nop() {}
	
	public void touch(Instruction i) {
		log.debug("nop");
		machine.incPC();
	}

}
