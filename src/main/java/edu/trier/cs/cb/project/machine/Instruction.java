package edu.trier.cs.cb.project.machine;

import edu.trier.cs.cb.project.compiler.Label;
import edu.trier.cs.cb.project.compiler.Location;

/* Instructions for TRIPLA 2011 */

public class Instruction {
	
	private int opcode;
	private Object arg1;
	private Object arg2;
	private Object arg3;
	private Label label;

	public final static int INVOKE = 1;
	public final static int RETURN = 2;
	public final static int CONST = 3;
	public final static int STORE = 4;
	public final static int LOAD = 5;
	public final static int ADD = 6;
	public final static int SUB = 7;
	public final static int MULT = 8;
	public final static int DIV = 9;
	public final static int IFZERO = 10;
	public final static int IFNZERO = 11;
	public final static int GOTO = 12;
	public final static int NOP = 13;
	public final static int IFLT = 14;
	public final static int IFGT = 15;
	public final static int IFEQ = 16;
	public final static int IFNEQ = 17;
	public final static int HALT = 100;

	public Instruction(int op) {
		opcode = op;
		arg1 = null;
		arg2 = null;
		arg3 = null;
	}
	
	public Instruction(int op, Integer a1) {
		this(op, a1, null, null);
	}

	public Instruction(int op, Integer a1, Integer a2) {
		this(op, a1, a2, null);
	}

	public Instruction(int op, Integer a1, Integer a2, Integer a3) {
		opcode = op;
		arg1 = a1;
		arg2 = a2;
		arg3 = a3;
	}

	public Instruction(int op, Location l1) {
		opcode = op;
		arg1 = l1;
	}

	public Instruction(int op, int l1, Location l2, int l3) {
		opcode = op;
		arg1 = l1;
		arg2 = l2;
		arg3 = l3;
	}
	
	public Instruction(int op, Location l1, int l2) {
		opcode = op;
		arg1 = l1;
		arg2 = l2;
	}

	private Integer getIntValue(Object o) {
		if (o instanceof Integer) {
			return (Integer)o;
		} else if (o instanceof Location) {
			return ((Location)o).address;
		} else {
			return null;
		}
	}
	
	public Integer getArg1() {
		return getIntValue(arg1);
	}

	public Integer getArg2() {
		return getIntValue(arg2);
	}

	public Integer getArg3() {
		return getIntValue(arg3);
	}

	public int getOpcode() {
		return opcode;
	}
	
	public void setLabel(Label label) {
		this.label = label;
	}
	
	public Label getLabel() {
		return this.label;
	}

	public boolean hasLabel() {
		return (this.label != null);
	}

	@Override
	public String toString() {
		String retStr = "";

		switch (opcode) {
		case Instruction.INVOKE:
			retStr += "INVOKE";
			break;
		case Instruction.RETURN:
			retStr += "RETURN";
			break;
		case Instruction.CONST:
			retStr += "CONST";
			break;
		case Instruction.STORE:
			retStr += "STORE";
			break;
		case Instruction.LOAD:
			retStr += "LOAD";
			break;
		case Instruction.ADD:
			retStr += "ADD";
			break;
		case Instruction.SUB:
			retStr += "SUB";
			break;
		case Instruction.MULT:
			retStr += "MULT";
			break;
		case Instruction.DIV:
			retStr += "DIV";
			break;
		case Instruction.IFZERO:
			retStr += "IFZERO";
			break;
		case Instruction.IFNZERO:
			retStr += "IFNZERO";
			break;
		case Instruction.IFGT:
			retStr += "IFGT";
			break;
		case Instruction.IFLT:
			retStr += "IFLT";
			break;
		case Instruction.GOTO:
			retStr += "GOTO";
			break;
		case Instruction.HALT:
			retStr += "HALT";
			break;
		case Instruction.NOP:
			retStr += "NOP";
			break;
		default:
			retStr += "ERROR";
		}

		if (arg1 != null) {
			retStr += " " + arg1;

			if (arg2 != null) {
				retStr += " " + arg2;

				if (arg3 != null) {
					retStr += " " + arg3;
				}
			}
		}
		
		return retStr;
	}

	/***********************************************
	 * Test Programs Abstract Machine
	 ***********************************************/

	/*
	 * Quellcode: let mult(a,b) { a*b } add(a,b) { let inc(a) { if (b!=0)
	 * b=b-1;inc(a+1) else mult(a,1) } in inc(a) } in add(mult(2,3),add(4,5))
	 * 
	 * Das Programm sollte den Wert 15 berechnen
	 */
	static Instruction program1[] = new Instruction[] {
			// MAIN:
			new Instruction(CONST, 2),
			new Instruction(CONST, 3),
			new Instruction(INVOKE, 2, 8, 0), // -->MULT
			new Instruction(CONST, 4),
			new Instruction(CONST, 5),
			new Instruction(INVOKE, 2, 12, 0), // --> ADD
			new Instruction(INVOKE, 2, 12, 0), // --> ADD
			new Instruction(HALT),
			// MULT:
			new Instruction(LOAD, 0, 0),
			new Instruction(LOAD, 1, 0),
			new Instruction(MULT),
			new Instruction(RETURN),
			// ADD:
			new Instruction(LOAD, 0, 0),
			new Instruction(INVOKE, 1, 15, 0), // --> INC
			new Instruction(RETURN),
			// INC:
			new Instruction(LOAD, 1, 1),
			new Instruction(IFNZERO, 21), // --> IFTRUE
			new Instruction(LOAD, 0, 0),
			new Instruction(CONST, 1),
			new Instruction(INVOKE, 2, 8, 2), // --> MULT
			new Instruction(GOTO, 29), // --> IFEND
			/* IFTRUE: */new Instruction(LOAD, 1, 1),
			new Instruction(CONST, 1), new Instruction(SUB),
			new Instruction(STORE, 1, 1), new Instruction(LOAD, 0, 0),
			new Instruction(CONST, 1), new Instruction(ADD),
			new Instruction(INVOKE, 1, 15, 1), // --> INC
			/* IFEND: */new Instruction(RETURN) };

	/*
	 * Quellkode: y=x*3+5*2 Annahme: Variable x durch Kellerzelle 0 und Variable
	 * y durch Kellerzelle 1 implementiert, sowie der PP=0 und TOP>1.
	 */
	static Instruction[] program2 = new Instruction[] {
			new Instruction(Instruction.CONST, 6), // value for x
			new Instruction(Instruction.STORE, 0, 0), // store x
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.CONST, 3),
			new Instruction(Instruction.MULT),
			new Instruction(Instruction.CONST, 5),
			new Instruction(Instruction.CONST, 2),
			new Instruction(Instruction.MULT),
			new Instruction(Instruction.ADD),
			new Instruction(Instruction.STORE, 1, 0),
			new Instruction(Instruction.HALT) };

	/*
	 * Quellkode: x=10; if(x==0) 100 else 200; 3 Annahme: Variable x durch
	 * Kellerzelle 0 implementiert, sowie PP=0 und TOP>0.
	 */
	static Instruction[] program3 = new Instruction[] {
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.STORE, 0, 0),
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.IFZERO, 6),
			new Instruction(Instruction.CONST, 200),
			new Instruction(Instruction.GOTO, 8),
			new Instruction(Instruction.CONST, 100),
			new Instruction(Instruction.NOP),
			new Instruction(Instruction.CONST, 3),
			new Instruction(Instruction.HALT) };

	/*
	 * Quellkode: let square(b) { b*b; } in square(10) Annahme: Variable b durch
	 * Kellerzelle 0 implementiert, sowie der Kellerzeiger TOP>0.
	 */
	static Instruction[] program4 = new Instruction[] {
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.STORE, 0, 0),
			new Instruction(Instruction.INVOKE, 1, 4, 0),
			new Instruction(Instruction.HALT),
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.MULT),
			new Instruction(Instruction.RETURN),
			new Instruction(Instruction.HALT) };

	/*
	 * (Beispiel durchspielen) Quellkode: let fac(x) { if (x == 1) 1 else
	 * f(x-1)*x } in fac(3) Annahme: PP=0, FP=0, TOP=0.
	 */
	static Instruction[] factorial = new Instruction[] {
			new Instruction(Instruction.CONST, 3),
			new Instruction(Instruction.INVOKE, 1, 3, 0), // --> fac
			new Instruction(Instruction.HALT),
			// fac
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.CONST, 1),
			new Instruction(Instruction.SUB),
			new Instruction(Instruction.IFZERO, 14), // --> iftrue
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.CONST, 1),
			new Instruction(Instruction.SUB),
			new Instruction(Instruction.INVOKE, 1, 3, 0), // --> fac
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.MULT),
			new Instruction(Instruction.RETURN),
			// iftrue
			new Instruction(Instruction.CONST, 1),
			new Instruction(Instruction.RETURN) };
}