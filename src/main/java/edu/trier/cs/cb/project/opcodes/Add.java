package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class Add extends AbstractOpcode implements Opcode {

	Add() {}
	
	public void touch(Instruction i) {
		System.out.println(i.getArg1() + i.getArg2());
	}

}
