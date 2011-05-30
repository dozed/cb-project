package edu.trier.cs.cb.project.parser;

import java.util.ArrayList;
import java.util.List;

public class ASTNode implements IASTNode {

	private String type;
	
	private List<IASTNode> children = new ArrayList<IASTNode>();
	
	public ASTNode() {
		
	}
	
	public ASTNode(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void addChild(IASTNode node) {
		children.add(node);
	}

	public List<IASTNode> getChildren() {
		return children;
	}

	@Override
	public void dump() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dump(int i) {
		// TODO Auto-generated method stub
		
	}

	
}
