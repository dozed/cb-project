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
//		stack.peek().appendChild(e);
		append(e);

		// variables
		Element vars = doc.createElement("variables");
		e.appendChild(vars);
		stack.push(vars);
		for (Identifier i : functionDefinition.getVariables()) {
			visit(i);
		}
		stack.pop();

		// expressions
		Element expressions = doc.createElement("expressions");
		e.appendChild(expressions);
		stack.push(expressions);
		for (Expression expr : functionDefinition.getExpressions()) {
			visit(expr);
		}
		stack.pop();
	}

	@Override
	public void visit(Relation relation) {
		Element e = doc.createElement("relation");
		e.setAttribute("type", relation.getType());
//		stack.peek().appendChild(e);
		append(e);
		stack.push(e);
		visit(relation.getLeft());
		visit(relation.getRight());
		stack.pop();
	}

	@Override
	public void visit(Operation operation) {
		Element e = doc.createElement("operation");
		e.setAttribute("type", operation.getType());
//		stack.peek().appendChild(e);
		append(e);
		stack.push(e);
		visit(operation.getLeft());
		visit(operation.getRight());
		stack.pop();
	}

	@Override
	public void visit(Constant constant) {
		Element e = doc.createElement("constant");
		e.setTextContent(constant.getValue().toString());
//		stack.peek().appendChild(e);
		append(e);
	}

	@Override
	public void visit(Identifier identifier) {
		Element e = doc.createElement("identifier");
		e.setTextContent(identifier.getName().toString());
//		stack.peek().appendChild(e);
		append(e);
	}

	@Override
	public void visit(FunctionCall functionCall) {
		Element e = doc.createElement("call");
		e.setAttribute("name", functionCall.getName());
//		stack.peek().appendChild(e);
		append(e);

		// arguments
		Element args = doc.createElement("arguments");
		e.appendChild(args);
		stack.push(args);
		for (Expression expr : functionCall.getArguments()) {
			visit(expr);
		}
		stack.pop();
	}

	@Override
	public void visit(IfExpression ifExpression) {
		Element e = doc.createElement("if");
//		stack.peek().appendChild(e);
		append(e);

		// if branch
		Element ifBranch = doc.createElement("ifBranch");
		e.appendChild(ifBranch);
		stack.push(ifBranch);
		for (Expression expr : ifExpression.getIfBranch()) {
			visit(expr);
		}
		stack.pop();
		
		// else branch
		Element elseBranch = doc.createElement("elseBranch");
		e.appendChild(elseBranch);
		stack.push(elseBranch);
		for (Expression expr : ifExpression.getElseBranch()) {
			visit(expr);
		}
		stack.pop();
	}

	@Override
	public void visit(Assignment assignment) {
		Element e = doc.createElement("assignment");
//		stack.peek().appendChild(e);
		append(e);
	
		stack.push(e);
		visit(assignment.getIdentifier());
		visit(assignment.getExpression());
		stack.pop();
	}

	@Override
	public void visit(Expression e) {
		if (e instanceof Relation) {
			visit((Relation) e);
		} else if (e instanceof Operation) {
			visit((Operation) e);
		} else if (e instanceof Constant) {
			visit((Constant) e);
		} else if (e instanceof FunctionCall) {
			visit((FunctionCall) e);
		} else if (e instanceof Identifier) {
			visit((Identifier) e);
		} else if (e instanceof IfExpression) {
			visit((IfExpression) e);
		} else if (e instanceof Assignment) {
			visit((Assignment) e);
		} else {
			throw new RuntimeException("Invalid type: " + e.getClass().getName());
		}
	}

}
