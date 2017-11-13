package com.egaetan;

import org.junit.Test;

public class ADNTest extends AbstractTestRunner {

	public ADNTest() {
		super(() -> new ADN().main(), "ADN décrypté 🔬");
	}
	
	@Test
	public void test() {
		runTestFromFile("adn/test1.txt", "Simple", "8");
	}

	
	
}
