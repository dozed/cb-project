package edu.trier.cs.cb.project.parser;

import java.util.List;

public class FunctionCall implements Term {

	private String name;
	private List<Expression> arguments;

	public FunctionCall(String name, List<Expression> arguments) {
		this.name = name;
		this.arguments = arguments;
	}
 
	public void dump() {
		dump(0);
	}
	
	public void dump(int level) {
		for (int i=0; i < level; i++) System.out.print("-");
		System.out.println("call:"+name);
		for (Expression e : arguments) {
			e.dump(level+1);
		}
	}
}
