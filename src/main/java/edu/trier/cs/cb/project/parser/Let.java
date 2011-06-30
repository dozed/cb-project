package edu.trier.cs.cb.project.parser;

import java.util.List;

import edu.trier.cs.cb.project.compiler.AddressEnvironment;
import edu.trier.cs.cb.project.compiler.AddressPair;
import edu.trier.cs.cb.project.compiler.Label;
import edu.trier.cs.cb.project.machine.Instruction;
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
	
	public void elab_def(AddressEnvironment env, int nl) {
		for (FunctionDefinition def : definitions) {
			env.map(def.getName(), new AddressPair(new Label(), nl));
		}
	}

	@Override
	public void code(List<Instruction> i, AddressEnvironment rho, int nl) {
		nl = nl + 1;
		Label l1 = new Label();
		i.add(new Instruction(Instruction.GOTO, l1));

		// add definitions to address environment
		elab_def(rho, nl);
		
		// compile definitions
		for (FunctionDefinition def : definitions) {
			def.code(i, rho, nl);
		}

		// create code label
		Instruction nop = new Instruction(Instruction.NOP);
		nop.setLabel(l1);
		i.add(nop);

		// compile expressions
		for (Expression exp : expressions) {
			exp.code(i, rho, nl);
		}
	}
	
}
