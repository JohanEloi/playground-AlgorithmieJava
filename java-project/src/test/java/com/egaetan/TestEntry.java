package com.egaetan;

public class TestEntry {
	String expected;
	
	StringBuilder inputs = new StringBuilder();
	
	public void line(String string) {
		inputs.append(string).append("\n");
	}

	public String input() {
		return inputs.toString();
	}
}