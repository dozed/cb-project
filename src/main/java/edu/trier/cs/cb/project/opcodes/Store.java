package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.Instruction;


public class Store extends AbstractOpcode implements Opcode {

	Store() {}
	
	public void touch(Instruction i) {
		Integer k = i.getArg1();
		Integer d = i.getArg2();
		if (d == null) {
			localStore(k);
		} else {
			remoteStore(k, d);
		}
	}
	
	private void localStore(int k) {
		Integer v = machine.pop();
		log.debug("storing " + k + "th argument with: " + v);
		machine.set(machine.getPP()+k, v);
		machine.incPC();
	}
	
	private void remoteStore(int k, int d) {
		int v = machine.pop();
		log.debug("storing " + k + "th argument with: " + v);
		machine.set(machine.spp(d)+k, v);
		machine.incPC();
	}

}
