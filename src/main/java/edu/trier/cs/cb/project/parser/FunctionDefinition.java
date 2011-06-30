package edu.trier.cs.cb.project.parser;

import java.util.List;

import edu.trier.cs.cb.project.compiler.AddressEnvironment;
import edu.trier.cs.cb.project.compiler.AddressPair;
import edu.trier.cs.cb.project.compiler.Label;
import edu.trier.cs.cb.project.compiler.Location;
import edu.trier.cs.cb.project.machine.Instruction;
import edu.trier.cs.cb.project.parser.visitor.Visitor;

public class FunctionDefinition implements ASTNode {

	private String name;

	private List<Identifier> parameters;
	
	private List<Expression> expressions;
  
	public FunctionDefinition(String name, List<Identifier> parameters, List<Expression> expressions) {
		this.name = name;
		this.parameters = parameters;
		this.expressions = expressions;
	}

	public String getName() {
		return name;
	}

	public List<Identifier> getParameters() {
		return parameters;
	}

	public List<Expression> getExpressions() {
		return expressions;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	@Override
	public void code(List<Instruction> i, AddressEnvironment rho, int nl) {
		Label l1 = rho.get(getName()).getLocation();

		// create code label
		Instruction nop = new Instruction(Instruction.NOP);
		nop.setLabel(l1);
		i.add(nop);
		
		for (int pos = 0; pos < parameters.size(); pos++) {
			rho.map(parameters.get(pos).getName(), new AddressPair(new Location(pos), nl));
		}
		
		for (Expression exp : expressions) {
			exp.code(i, rho, nl);
		}
		
		i.add(new Instruction(Instruction.RETURN));
	}
	
}
