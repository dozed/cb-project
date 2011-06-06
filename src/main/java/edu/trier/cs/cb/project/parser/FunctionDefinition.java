package edu.trier.cs.cb.project.parser;

import java.util.List;

import edu.trier.cs.cb.project.parser.visitor.Visitor;

public class FunctionDefinition implements ASTNode {

	private String name;

	private List<Identifier> variables;
	
	private List<Expression> expressions;
  
	public FunctionDefinition(String name, List<Identifier> variables, List<Expression> expressions) {
		this.name = name;
		this.variables = variables;
		this.expressions = expressions;
	}

	public String getName() {
		return name;
	}

	public List<Identifier> getVariables() {
		return variables;
	}

	public List<Expression> getExpressions() {
		return expressions;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}
