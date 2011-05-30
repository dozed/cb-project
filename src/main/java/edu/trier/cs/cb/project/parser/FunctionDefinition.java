package edu.trier.cs.cb.project.parser;

import java.util.List;

public class FunctionDefinition implements IASTNode {

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
	public void dump() {
		dump(0);
	}

	@Override
	public void dump(int level) {
		for (int i=0; i < level; i++) System.out.print("-");
		System.out.println("FUNCTION:"+name);
		for (Identifier i : variables) {
			i.dump(level+1);
		}
		for (Expression e : expressions) {
			e.dump(level+1);
		}
	}
	
}
