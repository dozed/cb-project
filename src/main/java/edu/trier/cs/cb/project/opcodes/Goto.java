package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class Goto extends AbstractOpcode implements Opcode {

	Goto() {}
	
	public void touch(Instruction i) {
		int p = i.getArg1();
		machine.setPC(p);
	}

}
