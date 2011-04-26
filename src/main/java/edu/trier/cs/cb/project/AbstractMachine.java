package edu.trier.cs.cb.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import edu.trier.cs.cb.project.opcodes.Add;
import edu.trier.cs.cb.project.opcodes.Opcode;

public class AbstractMachine {

	private Integer TOP;
	
	private Integer PP;
	
	private Integer FP;
	
	private Integer PC;
	
	private Stack<Integer> STACK;
	
	private Map<Integer, Opcode> operators = new HashMap<Integer, Opcode>();
	
	public AbstractMachine() {
		operators.put(Instruction.ADD, Opcode.Factory.get(Add.class, this));
		
	}
	
	public void execute(List<Instruction> instructions) {
		for (Instruction i : instructions) {
			Opcode op = operators.get(i.getOpcode());
			op.touch(i);
		}
	}
	
	
	
}
