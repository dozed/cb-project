package edu.trier.cs.cb.project.parser;

public class Assignment implements Expression {

	private Identifier identifier;
	
	private Expression expression;

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	public void dump() {
		dump(0);
	}
	
	public void dump(int level) {
		for (int i=0; i < level; i++) System.out.print("-");
		System.out.println("assignment");
		identifier.dump(level+1);
		expression.dump(level+1);
	}

}
