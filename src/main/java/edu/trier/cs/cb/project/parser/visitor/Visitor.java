package edu.trier.cs.cb.project.parser.visitor;

import edu.trier.cs.cb.project.parser.Assignment;
import edu.trier.cs.cb.project.parser.Constant;
import edu.trier.cs.cb.project.parser.Expression;
import edu.trier.cs.cb.project.parser.FunctionCall;
import edu.trier.cs.cb.project.parser.FunctionDefinition;
import edu.trier.cs.cb.project.parser.Identifier;
import edu.trier.cs.cb.project.parser.IfExpression;
import edu.trier.cs.cb.project.parser.Let;
import edu.trier.cs.cb.project.parser.Operation;
import edu.trier.cs.cb.project.parser.Relation;

public interface Visitor {

	public void visit(Let let);

	public void visit(FunctionDefinition functionDefinition);

	public void visit(Expression e);

	public void visit(Relation relation);

	public void visit(Operation operation);

	public void visit(Constant constant);

	public void visit(FunctionCall functionCall);

	public void visit(Identifier identifier);

	public void visit(IfExpression ifExpression);

	public void visit(Assignment assignment);

}
