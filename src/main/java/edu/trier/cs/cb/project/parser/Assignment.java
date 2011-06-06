package edu.trier.cs.cb.project.parser;

import edu.trier.cs.cb.project.parser.visitor.Visitor;

public class Assignment implements Expression {

	private Identifier identifier;
	
	private Expression expression;

	public Assignment(Identifier i, Expression e) {
		identifier = i;
		expression = e;
	}
	
	public Identifier getIdentifier() {
		return identifier;
	}

	public Expression getExpression() {
		return expression;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

}
