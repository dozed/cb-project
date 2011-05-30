package edu.trier.cs.cb.project.parser;

import edu.trier.cs.cb.project.parser.visitor.Visitor;

public class Identifier implements Term {

	private String name;

	public Identifier(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}
