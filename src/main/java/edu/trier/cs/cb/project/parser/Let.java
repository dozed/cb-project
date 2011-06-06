package edu.trier.cs.cb.project.parser;

import java.util.List;

import edu.trier.cs.cb.project.parser.visitor.Visitor;

public class Let implements ASTNode {

	private List<FunctionDefinition> definitions;
	private List<Expression> expressions;

	public Let(List<FunctionDefinition> definitions, List<Expression> expressions) {
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
	public void accept(Visitor v) {
		v.visit(this);
	}
	
}
