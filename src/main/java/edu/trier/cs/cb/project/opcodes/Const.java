package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class Const extends AbstractOpcode implements Opcode {

	Const() {}
	
	public void touch(Instruction i) {
		int k = i.getArg1();
		log.debug("pushing " + k);
		machine.push(k);
		machine.incPC();
	}

}
