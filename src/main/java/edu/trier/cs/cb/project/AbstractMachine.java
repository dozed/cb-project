package edu.trier.cs.cb.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import edu.trier.cs.cb.project.opcodes.Add;
import edu.trier.cs.cb.project.opcodes.Const;
import edu.trier.cs.cb.project.opcodes.Div;
import edu.trier.cs.cb.project.opcodes.Halt;
import edu.trier.cs.cb.project.opcodes.Ifeq;
import edu.trier.cs.cb.project.opcodes.Ifgt;
import edu.trier.cs.cb.project.opcodes.Iflt;
import edu.trier.cs.cb.project.opcodes.Ifneq;
import edu.trier.cs.cb.project.opcodes.Load;
import edu.trier.cs.cb.project.opcodes.Mult;
import edu.trier.cs.cb.project.opcodes.Opcode;
import edu.trier.cs.cb.project.opcodes.Store;
import edu.trier.cs.cb.project.opcodes.Sub;

public class AbstractMachine {

	/**
	 * The stack.
	 */
	private Stack<Integer> STACK = new Stack<Integer>();
	
	/**
	 * Points to the head of the stack.
	 * TODO This variable is managed by the machine.
	 */
	private Integer TOP = new Integer(-1);
	
	/**
	 * Parameter pointer: points to the first argument.
	 */
	private Integer PP = new Integer(0);
	
	/**
	 * Frame pointer: points to the start of the current frame.
	 */
	private Integer FP = new Integer(0);
	
	/**
	 * Program Counter: points to the instruction which is next in queue for execution. 
	 */
	private Integer PC = new Integer(0);

	/**
	 * Holds the handlers for the operators.
	 */
	private Map<Integer, Opcode> operators = new HashMap<Integer, Opcode>();
	
	public AbstractMachine() {
		operators.put(Instruction.CONST, Opcode.Factory.get(Const.class, this));
		operators.put(Instruction.STORE, Opcode.Factory.get(Store.class, this));
		operators.put(Instruction.LOAD, Opcode.Factory.get(Load.class, this));
		operators.put(Instruction.ADD, Opcode.Factory.get(Add.class, this));
		operators.put(Instruction.SUB, Opcode.Factory.get(Sub.class, this));
		operators.put(Instruction.MULT, Opcode.Factory.get(Mult.class, this));
		operators.put(Instruction.DIV, Opcode.Factory.get(Div.class, this));
		operators.put(Instruction.IFLT, Opcode.Factory.get(Iflt.class, this));
		operators.put(Instruction.IFGT, Opcode.Factory.get(Ifgt.class, this));
		operators.put(Instruction.IFEQ, Opcode.Factory.get(Ifeq.class, this));
		operators.put(Instruction.IFNEQ, Opcode.Factory.get(Ifneq.class, this));
		operators.put(Instruction.HALT, Opcode.Factory.get(Halt.class, this));
	}
	
	/**
	 * Executes a bunch of instructions.
	 * @param instructions The memory containing the instructions to be executed.
	 */
	public synchronized void execute(List<Instruction> instructions) {
		PC = 0;
		while (PC >= 0) {
			Instruction i = instructions.get(PC);
			Opcode op = operators.get(i.getOpcode());
			op.touch(i);
		}
	}
	
	public void printStack() {
		for (int i=0; i < STACK.size(); i++) {
			System.out.println(i+": "+STACK.get(i));
		}
	}
	
	public void incPC() {
		PC++;
	}

	public Integer getPC() {
		return PC;
	}

	public void setPC(Integer pC) {
		PC = pC;
	}

	public Integer getPP() {
		return PP;
	}

	public void setPP(Integer pP) {
		PP = pP;
	}

	public Integer getFP() {
		return FP;
	}

	public void setFP(Integer fP) {
		FP = fP;
	}

	public void push(Integer e) {
		TOP++;
		if (STACK.size() <= TOP+1) {
			STACK.setSize((TOP+1)*2);
		}
		STACK.set(TOP, e);
	}
	
	public Integer pop() {
		int e = STACK.get(TOP);
		TOP--;
		return e;
	}

	public Integer peek() {
		return STACK.get(TOP);
	}

	public Integer peek(int p) {
		return STACK.get(p);
	}

	public void pushArg(int index, Integer e) {
		int realIndex = PP + index;
		if (STACK.size() <= realIndex) {
			STACK.setSize(realIndex+1);
		}
		STACK.set(realIndex, e);
	}

}
