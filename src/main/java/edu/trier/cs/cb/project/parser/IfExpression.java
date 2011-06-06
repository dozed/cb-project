package edu.trier.cs.cb.project.parser;

import java.util.List;

import edu.trier.cs.cb.project.parser.visitor.Visitor;

public class IfExpression implements Expression {

	private Expression condition;
	
	private List<Expression> ifBranch;
	
	private List<Expression> elseBranch;
	
	public IfExpression(Expression condition, List<Expression> ifBranch, List<Expression> elseBranch) {
		this.condition = condition;
		this.ifBranch = ifBranch;
		this.elseBranch = elseBranch;
	}

	public Expression getCondition() {
		return condition;
	}

	public List<Expression> getIfBranch() {
		return ifBranch;
	}

	public List<Expression> getElseBranch() {
		return elseBranch;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
