package edu.trier.cs.cb.project.parser;

import java.util.List;

import edu.trier.cs.cb.project.compiler.AddressEnvironment;
import edu.trier.cs.cb.project.machine.Instruction;
import edu.trier.cs.cb.project.parser.visitor.Visitor;

public interface ASTNode {

	public void accept(Visitor v);
	
	public void code(List<Instruction> i, AddressEnvironment rho, int nl);
	
}
