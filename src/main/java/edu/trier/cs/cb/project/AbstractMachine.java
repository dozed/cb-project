package edu.trier.cs.cb.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import edu.trier.cs.cb.project.opcodes.Add;
import edu.trier.cs.cb.project.opcodes.Const;
import edu.trier.cs.cb.project.opcodes.Div;
import edu.trier.cs.cb.project.opcodes.Goto;
import edu.trier.cs.cb.project.opcodes.Halt;
import edu.trier.cs.cb.project.opcodes.IfEq;
import edu.trier.cs.cb.project.opcodes.IfGt;
import edu.trier.cs.cb.project.opcodes.IfLt;
import edu.trier.cs.cb.project.opcodes.IfNeq;
import edu.trier.cs.cb.project.opcodes.IfNotZero;
import edu.trier.cs.cb.project.opcodes.IfZero;
import edu.trier.cs.cb.project.opcodes.Invoke;
import edu.trier.cs.cb.project.opcodes.Load;
import edu.trier.cs.cb.project.opcodes.Mult;
import edu.trier.cs.cb.project.opcodes.Nop;
import edu.trier.cs.cb.project.opcodes.Opcode;
import edu.trier.cs.cb.project.opcodes.Return;
import edu.trier.cs.cb.project.opcodes.Store;
import edu.trier.cs.cb.project.opcodes.Sub;

/**
 * The abstract machine.
 * 
 * @author Stefan Ollinger
 */
public class AbstractMachine {

	public interface Listener {
		public void onBeforeExecuting(Instruction i);
		public void onAfterExecuting();
	}
	
	private List<Listener> listeners = new ArrayList<AbstractMachine.Listener>();
	
	/**
	 * The stack.
	 */
	private Stack<Integer> STACK = new Stack<Integer>();

	/**
	 * Points to the head of the stack. TODO This variable is managed by the
	 * machine.
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
	 * Program Counter: points to the instruction which is next in queue for
	 * execution.
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
		operators.put(Instruction.IFLT, Opcode.Factory.get(IfLt.class, this));
		operators.put(Instruction.IFGT, Opcode.Factory.get(IfGt.class, this));
		operators.put(Instruction.IFEQ, Opcode.Factory.get(IfEq.class, this));
		operators.put(Instruction.IFNEQ, Opcode.Factory.get(IfNeq.class, this));
		operators.put(Instruction.GOTO, Opcode.Factory.get(Goto.class, this));
		operators.put(Instruction.IFZERO, Opcode.Factory.get(IfZero.class, this));
		operators.put(Instruction.IFNZERO, Opcode.Factory.get(IfNotZero.class, this));
		operators.put(Instruction.INVOKE, Opcode.Factory.get(Invoke.class, this));
		operators.put(Instruction.RETURN, Opcode.Factory.get(Return.class, this));
		operators.put(Instruction.NOP, Opcode.Factory.get(Nop.class, this));
		operators.put(Instruction.HALT, Opcode.Factory.get(Halt.class, this));
	}

	/**
	 * Executes a bunch of instructions.
	 * 
	 * @param instructions
	 *            The memory containing the instructions to be executed.
	 */
	public synchronized void execute(List<Instruction> instructions) {
		PC = 0;
		while (PC >= 0) {
			Instruction i = instructions.get(PC);
			Opcode op = operators.get(i.getOpcode());
			// System.out.println("TOP: " + TOP + " PP: " + PP + " FP: " + FP);
			for (Listener l : listeners) l.onBeforeExecuting(i);
			
			op.touch(i);
			
			for (Listener l : listeners) l.onAfterExecuting();
		}
	}

	public void execute(Instruction[] instructions) {
		List<Instruction> ins = Arrays.asList(instructions);
		execute(ins);
	}

	public void addListener(Listener l) {
		this.listeners.add(l);
	}
	
	/**
	 * This method can be used to create space for local variables before
	 * executing a instruction set.
	 * 
	 * @param n
	 */
	public void reserveSpace(int n) {
		TOP += n;
	}

	public void printStack() {
		for (int i = 0; i < STACK.size(); i++) {
			System.out.println(i + ": " + STACK.get(i));
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

	public Integer getTOP() {
		return TOP;
	}

	public void setTOP(Integer top) {
		this.TOP = top;
	}

	public Stack<Integer> getStack() {
		return STACK;
	}
	
	public void push(Integer e) {
		TOP++;
		if (STACK.size() <= TOP + 1) {
			STACK.setSize((TOP + 2));
		}
		STACK.set(TOP, e);
	}

	public Integer pop() {
		int e = STACK.get(TOP);
		TOP--;
		return e;
	}

	public Integer peek() {
		return peek(0);
	}

	public Integer peek(int k) {
		return STACK.get(TOP + k);
	}

	public Integer get(int p) {
		return STACK.get(p);
	}

	public void set(int index, Integer e) {
		if (STACK.size() <= index) {
			STACK.setSize(index + 1);
		}
		STACK.set(index, e);
	}

	public Integer spp(int d) {
		return spp(d, PP, FP);
	}

	private Integer spp(int d, int pp, int fp) {
		if (d == 0) {
			return pp;
		} else {
			return spp(d - 1, get(fp + 2), get(fp + 3));
		}
	}

	public Integer sfp(int d) {
		return sfp(d, FP);
	}

	private Integer sfp(int d, int fp) {
		if (d == 0) {
			return fp;
		} else {
			return sfp(d - 1, get(fp + 3));
		}
	}

}
