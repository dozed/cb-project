package edu.trier.cs.cb.project.parser;

import edu.trier.cs.cb.project.parser.visitor.Visitor;

//public class Relation extends BinaryNode<Operation> implements Expression {
public class Relation extends BinaryNode<Expression> implements Expression {

	// TODO write type enum
	private String type;

	public Relation(String type, Expression left, Expression right) {
		this.type = type;
		this.setLeft(left);
		this.setRight(right);
	}

	public String getType() {
		return type;
	}

//	public void dump() {
//		dump(0);
//	}
//	
//	public void dump(int level) {
//		for (int i=0; i < level; i++) System.out.print("-");
//		System.out.println("operation");
//		getLeft().dump(level+1);
//		getRight().dump(level+1);
//	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
