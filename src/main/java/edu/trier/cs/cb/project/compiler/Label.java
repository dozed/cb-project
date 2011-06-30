package edu.trier.cs.cb.project.compiler;

public class Label extends Location {
	public Label() {
		this.address = -1;
	}

	public Label(int address) {
		this.address = address;
	}
}
