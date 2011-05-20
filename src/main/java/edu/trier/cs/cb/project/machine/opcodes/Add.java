package edu.trier.cs.cb.project.machine.opcodes;

import edu.trier.cs.cb.project.machine.Instruction;


public class Add extends AbstractOpcode implements Opcode {

	Add() {}
	
	public void touch(Instruction i) {
		int b = machine.pop();
		int a = machine.pop();
		int c = a + b;
		log.debug("adding: " + a + " + " + b + " = " + c);
		machine.push(c);
		machine.incPC();
	}

}
