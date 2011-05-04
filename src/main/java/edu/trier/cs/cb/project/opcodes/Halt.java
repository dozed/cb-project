package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class Halt extends AbstractOpcode implements Opcode {

	Halt() {}
	
	public void touch(Instruction i) {
		machine.setPC(-1);
	}

}