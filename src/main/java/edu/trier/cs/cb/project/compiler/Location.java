package edu.trier.cs.cb.project.compiler;

public class Location {
	public int address;

	public Location() {
		address = 0;
	}

	public Location(int address) {
		this.address = address;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return Integer.toString(address);
	}

}
