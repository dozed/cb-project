package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class Sub extends AbstractOpcode implements Opcode {

	Sub() {}
	
	public void touch(Instruction i) {
		int b = machine.pop();
		int a = machine.pop();
		int c = a - b;
		log.debug("subtracting: " + a + " - " + b + " = " + c);
		machine.push(c);
		machine.incPC();
	}

}
