package com.egaetan;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.junit.Ignore;
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

	@Test
	@Ignore
	public void generateLongListe() throws IOException {
		String[] elements = new String[] {"Chaise", "Table", "Canapé", "Coussin", "Lampe", "Miroir"};
		Random random = new Random();
		TestEntry entry = new TestEntry();
		entry.line("10000");
		entry.line(elements[0]);

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 10000; i++) {
			String element = elements[random.nextInt(elements.length)];
			int prix = random.nextInt(100) + 1;
			if (element==elements[0] && min > prix) {
				min = prix;
			}
			entry.line(element + " " + prix);
		}
		entry.expected = elements[0] + min;
		FileWriter writer = new FileWriter("src/main/resources/prixLePlusBas/prix4.txt");
		writer.write(entry.input());
		writer.close();
		System.err.println(entry.expected);
	}
	
}