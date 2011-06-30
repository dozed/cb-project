package edu.trier.cs.cb.project.machine.opcodes;

import edu.trier.cs.cb.project.compiler.Label;
import edu.trier.cs.cb.project.machine.Instruction;


public class Goto extends AbstractOpcode implements Opcode {

	private Label label;

	public Goto(Label label) {
		this.label = label;
	}
	
	Goto() {}
	
	public void touch(Instruction i) {
		int p = i.getArg1();
		log.debug("goto: " + p);
		machine.setPC(p);
	}

}
