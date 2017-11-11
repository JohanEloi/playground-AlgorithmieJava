package com.egaetan;

import org.junit.Test;

public class BatailleTest extends AbstractTestRunner {

	public BatailleTest() {
		super(() -> new Bataille().main(), "Bataille gagnée 🃏");
	}
	
	@Test
	public void test() {
		runTest("bataille1.txt", "Simple test, A vainqueur", "A");
		runTest("bataille2.txt", "Simple test, B vainqueur", "B");
		runTest("bataille3.txt", "Test 2", "B");
		runTest("bataille4.txt", "Ex-Aequos", "A");
		runTest("bataille5.txt", "100 valeurs", "A");
		runTest("bataille6.txt", "Pas de triches", "A");
	}

	
	
}