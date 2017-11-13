package com.egaetan;

import org.junit.Test;

public class HydroponieTest extends AbstractTestRunner {

	public HydroponieTest() {
		super(() -> new Hydroponie().main(), "Réussite de l'exploitation 🚜");
	}
	
	@Test
	public void test() {
		runTestFromFile("hydroponie/test1.txt", "Simple test", "8");
	}

	
	
}
