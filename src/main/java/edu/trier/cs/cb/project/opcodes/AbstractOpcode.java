package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.AbstractMachine;

public abstract class AbstractOpcode implements Opcode {

	private AbstractMachine abstractMachine;
	
	public void setAbstractMachine(AbstractMachine m) {
		abstractMachine = m;
	}

	protected AbstractMachine getAbstractMachine() {
		return abstractMachine;
	}
	
}
