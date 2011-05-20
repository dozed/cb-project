package edu.trier.cs.cb.project.parser;

public class Constant implements Expression {

	private Integer value;

	public Constant(Integer value) {
		super();
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
}
