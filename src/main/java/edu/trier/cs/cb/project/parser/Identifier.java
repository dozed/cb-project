package edu.trier.cs.cb.project.parser;

public class Identifier implements Expression {

	private String name;

	public Identifier(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
