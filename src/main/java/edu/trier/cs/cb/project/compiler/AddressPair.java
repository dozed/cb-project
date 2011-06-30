package edu.trier.cs.cb.project.compiler;

public class AddressPair {
	private Location loc;
	private int nl;

	public AddressPair(Location loc, int nl) {
		this.loc = loc;
		this.nl = nl;
	}

	public <T extends Location> T getLocation() {
		return (T)loc;
	}

	public int getNestingLevel() {
		return nl;
	}

}
