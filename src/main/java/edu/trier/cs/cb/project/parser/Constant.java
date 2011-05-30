package edu.trier.cs.cb.project.parser;

import edu.trier.cs.cb.project.parser.visitor.Visitor;

public class Constant implements Term {

	private Integer value;

	public Constant(Integer value) {
		super();
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
}
