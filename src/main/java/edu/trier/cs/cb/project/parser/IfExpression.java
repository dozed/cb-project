package edu.trier.cs.cb.project.parser;

import java.util.List;

import edu.trier.cs.cb.project.compiler.AddressEnvironment;
import edu.trier.cs.cb.project.compiler.Label;
import edu.trier.cs.cb.project.machine.Instruction;
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

	@Override
	public void code(List<Instruction> i, AddressEnvironment rho, int nl) {
			getCondition().code(i, rho, nl);
			
			Label l1 = new Label();
			Label l2 = new Label();

			i.add(new Instruction(Instruction.IFZERO, l1));
		
			for (Expression exp : ifBranch) {
				exp.code(i, rho, nl);
			}
			
			i.add(new Instruction(Instruction.GOTO, l2));
			
			// create label for else branch
			Instruction nop = new Instruction(Instruction.NOP);
			nop.setLabel(l1);
			i.add(nop);
		
			for (Expression exp : elseBranch) {
				exp.code(i, rho, nl);
			}
			
			// create label for end of if
			nop = new Instruction(Instruction.NOP);
			nop.setLabel(l2);
			i.add(nop);
	}

}
