package com.egaetan;

import java.io.FileWriter;
import java.io.IOException;

public class TestEntry {

	StringBuilder inputs = new StringBuilder();
	StringBuilder expecteds = new StringBuilder();
	private final String fileName;

	public TestEntry(String fileName) {
		this.fileName = fileName;
	}

	public void line(String string) {
		inputs.append(string).append("\n");
	}

	public void expect(String string) {
		expecteds.append(string).append("\n");
	}

	private String input() {
		return inputs.toString();
	}

	private String expected() {
		return expecteds.toString();
	}

	public void writeDown() throws IOException {
		FileWriter writer = new FileWriter("src/main/resources/"+fileName);
		writer.write(input());
		writer.close();
		System.err.println(expected());		
	}
}