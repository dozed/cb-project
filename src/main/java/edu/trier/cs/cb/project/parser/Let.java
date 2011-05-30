package edu.trier.cs.cb.project.parser;

import java.util.List;

public class Let extends ASTNode implements IASTNode {

	private List<FunctionDefinition> definitions;
	private List<Expression> expressions;

	public Let(List<FunctionDefinition> definitions, List<Expression> expressions) {
		super();
		this.definitions = definitions;
		this.expressions = expressions;
	}
	
	public List<FunctionDefinition> getDefinitions() {
		return definitions;
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
		System.out.println("let");
		System.out.println("function definitions:");
		for (FunctionDefinition d : definitions) {
			d.dump(level+1);
		}
		for (Expression e : expressions) {
			e.dump(level+1);
		}
	}

}
