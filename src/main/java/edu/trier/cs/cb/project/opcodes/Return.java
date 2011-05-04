package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;

public class Return extends AbstractOpcode implements Opcode {

	Return() {}

	public void touch(Instruction i) {
		log.debug("return");

		int r = machine.peek();
		
		int top = machine.getPP();
		int pp = machine.get(machine.getFP());
		int fp = machine.get(machine.getFP()+1);
		int pc = machine.get(machine.getFP()+4);
		
		machine.setTOP(top-1);
		machine.setPP(pp);
		machine.setFP(fp);
		machine.setPC(pc);

		machine.push(r);
	}

}
