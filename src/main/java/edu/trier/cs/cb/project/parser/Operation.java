package edu.trier.cs.cb.project.parser;

public class Operation extends BinaryNode<Expression> implements Expression {

	// TODO write type enum
	private String type;
	
	public Operation(String type, Expression left, Expression right) {
		super();
		this.type = type;
		this.setLeft(left);
		this.setRight(right);
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void dump() {
		dump(0);
	}
	
	public void dump(int level) {
		for (int i=0; i < level; i++) System.out.print("-");
		System.out.println("operation:"+type);
		getLeft().dump(level+1);
		getRight().dump(level+1);
	}
}
