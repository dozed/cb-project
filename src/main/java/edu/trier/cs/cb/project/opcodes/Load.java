package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class Load extends AbstractOpcode implements Opcode {

	Load() {}
	
	public void touch(Instruction i) {
		int k = i.getArg1();
		int v = machine.peek(machine.getPP()+k);
		log.debug("loading " + k + "th argument on stack: " + v);
		machine.push(v);
		machine.incPC();
	}

}
