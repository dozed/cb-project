package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class Store extends AbstractOpcode implements Opcode {

	Store() {}
	
	public void touch(Instruction i) {
		int k = i.getArg1();
		int v = machine.pop();
		log.debug("storing " + k + "th argument with: " + v);
		machine.pushArg(k, v);
		machine.incPC();
	}

}
