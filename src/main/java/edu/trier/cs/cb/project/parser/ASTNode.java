package edu.trier.cs.cb.project.parser;

import edu.trier.cs.cb.project.parser.visitor.Visitor;

public interface ASTNode {

	public void accept(Visitor v);
	
}
