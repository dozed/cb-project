package edu.trier.cs.cb.project.parser;

import java.util.List;

public class IfExpression implements Expression {

	private Expression condition;
	private List<Expression> ifBranch;
	private List<Expression> elseBranch;
	
	public IfExpression(Expression condition, List<Expression> ifBranch, List<Expression> elseBranch) {
		super();
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

	public void dump() {
		dump(0);
	}
	
	public void dump(int level) {
		for (int i=0; i < level; i++) System.out.print("-");
		System.out.println("if");
		
		// condition
		for (int i=0; i < level+1; i++) System.out.print("-");
		System.out.println("condition");
		condition.dump(level+1);
		
		// if branch
		for (int i=0; i < level+1; i++) System.out.print("-");
		System.out.println("if-branch");
		for (Expression e : ifBranch) e.dump();
		
		// else branch
		for (int i=0; i < level+1; i++) System.out.print("-");
		System.out.println("else-branch");
		for (Expression e : elseBranch) e.dump();
	}
}
