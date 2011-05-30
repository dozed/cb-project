package edu.trier.cs.cb.project.parser;

public class Constant implements Term {

	private Integer value;

	public Constant(Integer value) {
		super();
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
	public void dump() {
		dump(0);
	}
	
	public void dump(int level) {
		for (int i=0; i < level; i++) System.out.print("-");
		System.out.println("constant:"+value);
	}
}
