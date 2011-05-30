package edu.trier.cs.cb.project.parser;

import edu.trier.cs.cb.project.parser.visitor.Visitor;

public interface IASTNode {

	public void accept(Visitor v);
	
//	public void dump();
//
//	public void dump(int level);

}
