package edu.trier.cs.cb.project.parser;

import java.util.List;

import edu.trier.cs.cb.project.compiler.AddressEnvironment;
import edu.trier.cs.cb.project.machine.Instruction;
import edu.trier.cs.cb.project.parser.visitor.Visitor;

public class Constant implements Term {

	private Integer value;

	public Constant(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public void code(List<Instruction> i, AddressEnvironment rho, int nl) {
		i.add(new Instruction(Instruction.CONST, value));
	}
}
