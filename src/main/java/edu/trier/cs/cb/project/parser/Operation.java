package edu.trier.cs.cb.project.parser;

public class Operation implements Expression {

	private String type;
	private Expression left;
	private Expression right;

	public Operation(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public void setLeft(Expression left) {
		this.left = left;
	}

	public void setRight(Expression right) {
		this.right = right;
	}

	public Expression getLeftExpression() {
		return left;
	}

	public Expression getRightExpression() {
		return right;
	}

}
