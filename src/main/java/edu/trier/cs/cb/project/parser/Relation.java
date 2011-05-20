package edu.trier.cs.cb.project.parser;

public class Relation implements Expression {

	private String type;

	public Relation(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
}
