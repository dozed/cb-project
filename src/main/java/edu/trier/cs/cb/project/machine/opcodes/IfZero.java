package edu.trier.cs.cb.project.machine.opcodes;

import edu.trier.cs.cb.project.compiler.Label;
import edu.trier.cs.cb.project.machine.Instruction;


public class IfZero extends AbstractOpcode implements Opcode {

	private Label label;

	public IfZero(Label label) {
		this.label = label;
	}
	
	IfZero() {}
	
	public void touch(Instruction i) {
		int p = i.getArg1();
		int r = machine.pop();
		log.debug("ifzero: " + (r == 0));
		if (r == 0) {
			machine.setPC(p);
		} else {
			machine.incPC();
		}
	}

}
