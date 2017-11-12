package com.egaetan;

import org.junit.Test;

public class HydroponieTest extends AbstractTestRunner {

	public HydroponieTest() {
		super(() -> new Hydroponie().main(), "Bataille gagnée 🃏");
	}
	
	@Test
	public void test() {
		runTestFromFile("bataille/bataille1.txt", "Simple test, A vainqueur", "A");
	}

	
	
}