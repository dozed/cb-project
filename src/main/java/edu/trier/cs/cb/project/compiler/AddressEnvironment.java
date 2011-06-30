package edu.trier.cs.cb.project.compiler;

import java.util.HashMap;
import java.util.Map;

public class AddressEnvironment implements Cloneable {

	private Map<String, AddressPair> map = new HashMap<String, AddressPair>();
	
	public void map(String id, AddressPair loc) {
		map.put(id, loc);
	}

	public AddressPair get(String id) {
		return map.get(id);
	}
	
	public AddressEnvironment copy() {
		try {
			return (AddressEnvironment)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
	
}
