package com.egaetan;

import org.junit.Test;

public class BatailleTest extends AbstractTestRunner {

	public BatailleTest() {
		super(() -> new Bataille().main(), "Bataille gagnée 🃏");
	}
	
	@Test
	public void test() {
		runTestFromFile("bataille/bataille1.txt", "Simple test, A vainqueur", "A");
		runTestFromFile("bataille/bataille2.txt", "Simple test, B vainqueur", "B");
		runTestFromFile("bataille/bataille3.txt", "Test 2", "B");
		runTestFromFile("bataille/bataille4.txt", "Ex-Aequos", "A");
		runTestFromFile("bataille/bataille5.txt", "100 valeurs", "A");
		runTestFromFile("bataille/bataille6.txt", "Pas de triches", "A");
	}

	
	
}