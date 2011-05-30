package edu.trier.cs.cb.project.parser;

public class Identifier implements Term {

	private String name;

	public Identifier(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void dump() {
		dump(0);
	}
	
	public void dump(int level) {
		for (int i=0; i < level; i++) System.out.print("-");
		System.out.println("identifier:"+name);
	}
}
