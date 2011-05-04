package edu.trier.cs.cb.project.opcodes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.trier.cs.cb.project.AbstractMachine;

public abstract class AbstractOpcode implements Opcode {

	protected AbstractMachine machine;
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void setAbstractMachine(AbstractMachine m) {
		machine = m;
	}

	protected AbstractMachine getAbstractMachine() {
		return machine;
	}

	protected Logger getLogger() {
		return log;
	}
	
}
