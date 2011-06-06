package edu.trier.cs.cb.project.parser.visitor;

import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

public class DomBuilderVisitor implements Visitor {

	private Document doc;
	
	private Stack<Element> stack = new Stack<Element>();
	
	public DomBuilderVisitor() {
		try {
			createDocument();
		} catch (Exception e) {
			throw new RuntimeException("Could not create document builder.", e);
		}
	}

	private void createDocument() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = factory.newDocumentBuilder();
		doc = parser.newDocument();
	}
	
	public Document getDocument() {
		return doc;
	}
	
	@Override
	public void visit(Let let) {
		Element e = doc.createElement("let");
		stack.push(e);
		doc.appendChild(e);
		
		// definitions
		Element defs = doc.createElement("definitions");
		e.appendChild(defs);
		stack.push(defs);
		for (FunctionDefinition d : let.getDefinitions()) {
			visit(d);
		}
		stack.pop();
		
		// expressions
		Element expressions = doc.createElement("expressions");
		e.appendChild(expressions);
		stack.push(defs);
		for (Expression expr : let.getExpressions()) {
			visit(expr);
		}
		stack.pop();
	}

	private void append(Element e) {
		if (stack.size() > 0) {
			stack.peek().appendChild(e);
		} else {
			doc.appendChild(e);
			stack.add(e);
		}
	}
	
	@Override
	public void visit(FunctionDefinition functionDefinition) {
		Element e = doc.createElement("function");
		e.setAttribute("name", functionDefinition.getName());
		append(e);

		// variables
		Element vars = doc.createElement("variables");
		e.appendChild(vars);
		stack.push(vars);
		for (Identifier id : functionDefinition.getVariables()) {
			id.accept(this);
		}
		stack.pop();

		// expressions
		Element expressions = doc.createElement("expressions");
		e.appendChild(expressions);
		stack.push(expressions);
		for (Expression expr : functionDefinition.getExpressions()) {
			expr.accept(this);
		}
		stack.pop();
	}

	@Override
	public void visit(Relation relation) {
		Element e = doc.createElement("relation");
		e.setAttribute("type", relation.getType());
		append(e);
		stack.push(e);
		relation.getLeft().accept(this);
		relation.getRight().accept(this);
		stack.pop();
	}

	@Override
	public void visit(Operation operation) {
		Element e = doc.createElement("operation");
		e.setAttribute("type", operation.getType());
		append(e);
		stack.push(e);
		operation.getLeft().accept(this);
		operation.getRight().accept(this);
		stack.pop();
	}

	@Override
	public void visit(Constant constant) {
		Element e = doc.createElement("constant");
		e.setTextContent(constant.getValue().toString());
		append(e);
	}

	@Override
	public void visit(Identifier identifier) {
		Element e = doc.createElement("identifier");
		e.setTextContent(identifier.getName().toString());
		append(e);
	}

	@Override
	public void visit(FunctionCall functionCall) {
		Element e = doc.createElement("call");
		e.setAttribute("name", functionCall.getName());
		append(e);

		// arguments
		Element args = doc.createElement("arguments");
		e.appendChild(args);
		stack.push(args);
		for (Expression expr : functionCall.getArguments()) {
			expr.accept(this);
		}
		stack.pop();
	}

	@Override
	public void visit(IfExpression ifExpression) {
		Element e = doc.createElement("if");
		append(e);

		// if branch
		Element ifBranch = doc.createElement("ifBranch");
		e.appendChild(ifBranch);
		stack.push(ifBranch);
		for (Expression expr : ifExpression.getIfBranch()) {
			expr.accept(this);
		}
		stack.pop();
		
		// else branch
		Element elseBranch = doc.createElement("elseBranch");
		e.appendChild(elseBranch);
		stack.push(elseBranch);
		for (Expression expr : ifExpression.getElseBranch()) {
			expr.accept(this);
		}
		stack.pop();
	}

	@Override
	public void visit(Assignment assignment) {
		Element e = doc.createElement("assignment");
		append(e);
	
		stack.push(e);
		assignment.getIdentifier().accept(this);
		assignment.getExpression().accept(this);
		stack.pop();
	}

	@Override
	public void visit(Expression e) {
		e.accept(this);
	}

}
