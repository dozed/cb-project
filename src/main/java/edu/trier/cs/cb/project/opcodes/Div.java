package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class Div extends AbstractOpcode implements Opcode {

	Div() {}
	
	public void touch(Instruction i) {
		int b = machine.pop();
		int a = machine.pop();
		int c = a / b;
		log.debug("dividing: " + a + " / " + b + " = " + c);
		machine.push(c);
		machine.incPC();
	}

}
