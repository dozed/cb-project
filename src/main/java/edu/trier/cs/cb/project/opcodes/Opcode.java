package edu.trier.cs.cb.project.opcodes;

import edu.trier.cs.cb.project.AbstractMachine;
import edu.trier.cs.cb.project.Instruction;

public interface Opcode {

	public static class Factory {
		public static <T extends Opcode> T get(Class<T> clazz, AbstractMachine m) {
			try {
				T op = clazz.newInstance();
				op.setAbstractMachine(m);
				return op;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public void touch(Instruction i);
	
	public void setAbstractMachine(AbstractMachine m);

}
