package com.egaetan;

import org.junit.Test;

public class PrixLePlusBasTest extends AbstractTestRunner {

	public PrixLePlusBasTest() {
		super(() -> new PrixLePlusBas().main(), "Prix remporté ! 💶");
	}
	
	@Test
	public void test() {
		runTestFromFile("prixLePlusBas/prix1.txt", "Poivre & Sel 🍴", "10");
		runTestFromFile("prixLePlusBas/prix2.txt", "Panier de fruits 🍒 🍌🍍", "21");
		runTestFromFile("prixLePlusBas/prix3.txt", "Compote 🍎", "11");
		runTestFromFile("prixLePlusBas/prix4.txt", "Grand salon 🏠", "1");
	}

}