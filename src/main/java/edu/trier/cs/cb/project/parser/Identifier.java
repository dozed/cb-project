package edu.trier.cs.cb.project.parser;

import java.util.List;

import edu.trier.cs.cb.project.compiler.AddressEnvironment;
import edu.trier.cs.cb.project.compiler.AddressPair;
import edu.trier.cs.cb.project.machine.Instruction;
import edu.trier.cs.cb.project.parser.visitor.Visitor;

public class Identifier implements Term {

	private String name;

	public Identifier(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public void code(List<Instruction> i, AddressEnvironment rho, int nl) {
		AddressPair address = rho.get(getName());
		i.add(new Instruction(Instruction.LOAD, address.getLocation(), nl - address.getNestingLevel()));
	}

}
