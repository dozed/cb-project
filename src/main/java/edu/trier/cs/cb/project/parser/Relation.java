package edu.trier.cs.cb.project.parser;

import java.util.List;

import edu.trier.cs.cb.project.compiler.AddressEnvironment;
import edu.trier.cs.cb.project.compiler.Label;
import edu.trier.cs.cb.project.machine.Instruction;
import edu.trier.cs.cb.project.parser.visitor.Visitor;

public class Relation extends BinaryNode<Expression> implements Expression {

	public enum Type {
		EQ, NEQ, GT, LT;
		
		public static Type get(String s) {
			if (s.equals("==")) return EQ;
			if (s.equals("!=")) return NEQ;
			if (s.equals(">")) return GT;
			if (s.equals("<")) return LT;
			return null;
		}
	}
	
	private Type type;

	public Relation(String type, Expression left, Expression right) {
		this(Type.get(type), left, right);
	}
	
	public Relation(Type type, Expression left, Expression right) {
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
		Label l1 = new Label();
		Label l2 = new Label();
		
		new Operation(Operation.Type.SUB, getLeft(), getRight()).code(i, rho, nl);
		
		if (getType() == Type.EQ) i.add(new Instruction(Instruction.IFZERO, l1));
		else if (getType() == Type.NEQ) i.add(new Instruction(Instruction.IFNZERO, l1));
		else if (getType() == Type.GT) i.add(new Instruction(Instruction.IFGT, l1));
		else if (getType() == Type.LT) i.add(new Instruction(Instruction.IFLT, l1));
		
		i.add(new Instruction(Instruction.CONST, 0));
		i.add(new Instruction(Instruction.GOTO, l2));

		Instruction c = new Instruction(Instruction.CONST, 1);
		c.setLabel(l1);
		i.add(c);
		
		Instruction nop = new Instruction(Instruction.NOP);
		nop.setLabel(l2);
		i.add(nop);
	}
}
