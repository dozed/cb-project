package edu.trier.cs.cb.project.parser;

public abstract class BinaryNode<T> implements Expression {

	private T left;
	private T right;
	
	protected void setLeft(T left) {
		this.left = left;
	}

	protected void setRight(T right) {
		this.right = right;
	}

	public T getLeft() {
		return left;
	}

	public T getRight() {
		return right;
	}
	
}
