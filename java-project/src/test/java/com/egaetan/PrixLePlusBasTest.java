package com.egaetan;

import org.junit.Test;

public class PrixLePlusBasTest extends AbstractTestRunner {

	public PrixLePlusBasTest() {
		super(() -> new PrixLePlusBas().main(), "Prix remporté ! € $");
	}
	
	@Test
	public void test() {
		runTest("prixLePlusBas/prix1.txt", "Poivre & Sel", "10");
		runTest("prixLePlusBas/prix2.txt", "Panier de fruits", "21");
		runTest("prixLePlusBas/prix3.txt", "Compote", "11");
		runTest("prixLePlusBas/prix3.txt", "Long", "11");
	}

	
	
}