package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class Load extends AbstractOpcode implements Opcode {

	Load() {}
	
	public void touch(Instruction i) {
		Integer k = i.getArg1();
		Integer d = i.getArg2();
		if (d == null) {
			localLoad(k);
		} else {
			remoteLoad(k, d);
		}
	}
	
	private void localLoad(int k) {
		int v = machine.get(machine.getPP()+k);
		log.debug("loading " + k + "th argument on stack: " + v);
		machine.push(v);
		machine.incPC();
	}

	private void remoteLoad(int k, int d) {
		int v = machine.get(machine.spp(d)+k);
		log.debug("loading " + k + "th argument from remote stack with distance " + d + ": " + v);
		machine.push(v);
		machine.incPC();
	}

}
