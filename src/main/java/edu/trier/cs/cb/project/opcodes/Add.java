package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class Add extends AbstractOpcode implements Opcode {

	Add() {}
	
	public void touch(Instruction i) {
		int a = machine.pop();
		int b = machine.pop();
		int c = a + b;
		log.debug("adding: " + a + " + " + b + " = " + c);
		machine.push(c);
		machine.incPC();
	}

}
