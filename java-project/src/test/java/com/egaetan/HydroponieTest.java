package com.egaetan;

import org.junit.Test;

public class HydroponieTest extends AbstractTestRunner {

	public HydroponieTest() {
		super(() -> new Hydroponie().main(), "Réussite de l'exploitation 🚜");
	}
	
	@Test
	public void test() {
		runTestFromFile("hydroponie/test1.txt", "Au centre 🌹", "8");
		runTestFromFile("hydroponie/test2.txt", "Au bord 🌿", "5");
		runTestFromFile("hydroponie/test3.txt", "Au bord opposé 🌷", "5");
		runTestFromFile("hydroponie/test4.txt", "En couple 🍒", "10");
		runTestFromFile("hydroponie/test5.txt", "Moyen 🌾", "58");
		runTestFromFile("hydroponie/test6.txt", "Grand 🌳", "58");
	}

	
	
}
