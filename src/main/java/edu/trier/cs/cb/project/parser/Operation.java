package edu.trier.cs.cb.project.parser;

import java.util.List;

import edu.trier.cs.cb.project.compiler.AddressEnvironment;
import edu.trier.cs.cb.project.machine.Instruction;
import edu.trier.cs.cb.project.parser.visitor.Visitor;

public class Operation extends BinaryNode<Expression> implements Expression {

	public enum Type {
		ADD, SUB, MUL, DIV;
		
		public static Type get(String s) {
			if (s.equals("+")) return ADD;
			else if (s.equals("-")) return SUB;
			else if (s.equals("*")) return MUL;
			else if (s.equals("/")) return DIV;
			else return null;
		}
	}
	
	private Type type;
	
	public Operation(String type, Expression left, Expression right) {
		this(Type.get(type), left, right);
	}

	public Operation(Type type, Expression left, Expression right) {
		this.type = type;
		this.setLeft(left);
		this.setRight(right);
	}

	public Type getType() {
		return type;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public void code(List<Instruction> i, AddressEnvironment rho, int nl) {
		getLeft().code(i, rho, nl);
		getRight().code(i, rho, nl);
		
		if (getType() == Type.ADD) i.add(new Instruction(Instruction.ADD));
		else if (getType() == Type.SUB) i.add(new Instruction(Instruction.SUB));
		else if (getType() == Type.MUL) i.add(new Instruction(Instruction.MULT));
		else if (getType() == Type.DIV) i.add(new Instruction(Instruction.DIV));
	}
}
