package edu.trier.cs.cb.project.parser;

import java.util.List;

import edu.trier.cs.cb.project.parser.visitor.Visitor;

public class FunctionCall implements Term {

	private String name;
	
	private List<Expression> arguments;

	public FunctionCall(String name, List<Expression> arguments) {
		this.name = name;
		this.arguments = arguments;
	}

	public String getName() {
		return name;
	}

	public List<Expression> getArguments() {
		return arguments;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}
