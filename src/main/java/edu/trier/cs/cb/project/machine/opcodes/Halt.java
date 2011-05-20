package edu.trier.cs.cb.project.machine.opcodes;

import edu.trier.cs.cb.project.machine.Instruction;


public class Halt extends AbstractOpcode implements Opcode {

	Halt() {}
	
	public void touch(Instruction i) {
		machine.setPC(-1);
	}

}
