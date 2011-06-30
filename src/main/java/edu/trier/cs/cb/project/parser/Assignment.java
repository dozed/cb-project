package edu.trier.cs.cb.project.parser;

import java.util.List;

import edu.trier.cs.cb.project.compiler.AddressEnvironment;
import edu.trier.cs.cb.project.compiler.AddressPair;
import edu.trier.cs.cb.project.machine.Instruction;
import edu.trier.cs.cb.project.parser.visitor.Visitor;

public class Assignment implements Expression {

	private Identifier identifier;

	private Expression expression;

	public Assignment(Identifier i, Expression e) {
		identifier = i;
		expression = e;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public Expression getExpression() {
		return expression;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public void code(List<Instruction> i, AddressEnvironment rho, int nl) {
		expression.code(i, rho, nl);
		AddressPair address = rho.get(identifier.getName());
		i.add(new Instruction(Instruction.STORE, address.getLocation(), nl - address.getNestingLevel()));
		i.add(new Instruction(Instruction.LOAD, address.getLocation(), nl - address.getNestingLevel()));
	}

}
