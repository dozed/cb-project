package edu.trier.cs.cb.project.machine.opcodes;

import edu.trier.cs.cb.project.machine.Instruction;

public class Invoke extends AbstractOpcode implements Opcode {

	Invoke() {}

	public void touch(Instruction i) {
		int n = i.getArg1();
		int p = i.getArg2();
		int d = i.getArg3();

		log.debug("invoke: n:" + n + " p:" + p + " d:" + d);
		
		int pp = machine.getTOP() - n + 1;	// pointer to the current parameter list
		int fp = machine.getTOP() + 1;		// pointer to the current stack frame

		machine.push(machine.getPP());
		machine.push(machine.getFP());
		machine.push(machine.spp(d));
		machine.push(machine.sfp(d));
		machine.push(machine.getPC()+1);
		
		machine.setPP(pp);
		machine.setFP(fp);
		machine.setPC(p);
	}

}
