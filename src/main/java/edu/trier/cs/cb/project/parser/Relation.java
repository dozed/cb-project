package edu.trier.cs.cb.project.parser;

import edu.trier.cs.cb.project.parser.visitor.Visitor;

public class Relation extends BinaryNode<Expression> implements Expression {

	private String type;

	public Relation(String type, Expression left, Expression right) {
		this.type = type;
		this.setLeft(left);
		this.setRight(right);
	}

	public String getType() {
		return type;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
