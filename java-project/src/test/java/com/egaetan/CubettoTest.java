package com.egaetan;

import org.junit.Test;

public class CubettoTest extends AbstractTestRunner {

	public CubettoTest() {
		super(() -> new Cubetto().main(), "Bataille gagnée 🃏");
	}
	
	@Test
	public void test() {
		runTestFromFile("cubetto/test1.txt", "Simple test, A vainqueur", "A");
	}

	
	
}