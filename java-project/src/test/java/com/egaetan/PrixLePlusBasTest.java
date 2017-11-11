package com.egaetan;

import org.junit.Test;

public class PrixLePlusBasTest extends AbstractTestRunner {

	public PrixLePlusBasTest() {
		super(() -> new PrixLePlusBas().main(), "Prix remporté ! € $");
	}
	
	@Test
	public void test() {
		runTest("prixLePlusBas/prix1.txt", "Simple test", "10");
	}

	
	
}