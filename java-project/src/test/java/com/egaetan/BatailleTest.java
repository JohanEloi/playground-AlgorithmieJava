package com.egaetan;

import org.junit.Test;

public class BatailleTest extends AbstractTestRunner {

	public BatailleTest() {
		super(() -> new Bataille().main(), "Bataille gagnée 🃏");
	}
	
	@Test
	public void test() {
		runTest("bataille/bataille1.txt", "Simple test, A vainqueur", "A");
		runTest("bataille/bataille2.txt", "Simple test, B vainqueur", "B");
		runTest("bataille/bataille3.txt", "Test 2", "B");
		runTest("bataille/bataille4.txt", "Ex-Aequos", "A");
		runTest("bataille/bataille5.txt", "100 valeurs", "A");
		runTest("bataille/bataille6.txt", "Pas de triches", "A");
	}

	
	
}