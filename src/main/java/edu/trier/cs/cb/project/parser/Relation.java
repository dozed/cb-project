package edu.trier.cs.cb.project.parser;

//public class Relation extends BinaryNode<Operation> implements Expression {
public class Relation extends BinaryNode<Expression> implements Expression {

	// TODO write type enum
	private String type;

	public Relation(String type, Expression left, Expression right) {
		super();
		this.type = type;
		this.setLeft(left);
		this.setRight(right);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void dump() {
		dump(0);
	}
	
	public void dump(int level) {
		for (int i=0; i < level; i++) System.out.print("-");
		System.out.println("operation");
		getLeft().dump(level+1);
		getRight().dump(level+1);
	}
}
