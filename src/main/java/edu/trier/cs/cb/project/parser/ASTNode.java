package edu.trier.cs.cb.project.parser;

import java.util.ArrayList;
import java.util.List;

public class ASTNode implements IASTNode {

	private String type;
	
	private List<ASTNode> children = new ArrayList<ASTNode>();
	
	public ASTNode() {
		
	}
	
	public ASTNode(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void addChild(ASTNode node) {
		children.add(node);
	}

	public List<ASTNode> getChildren() {
		return children;
	}
	
	public void dump() {
		dump(this, 0);
	}
	
	private void dump(ASTNode node, int level) {
		for (int i=0; i < level; i++) System.out.print("-");
		System.out.println(node.getType());
		for (ASTNode child : node.getChildren()) {
			dump(child, level+1);
		}
	}
	
}
