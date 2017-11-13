package com.egaetan.generators;

import java.io.IOException;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

import com.egaetan.TestEntry;

public class PrixLePlusBasGen {

	@Test
	@Ignore
	public void generateLongListe() throws IOException {
		String[] elements = new String[] {"Chaise", "Table", "Canapé", "Coussin", "Lampe", "Miroir"};
		Random random = new Random();
		TestEntry entry = new TestEntry("prixLePlusBas/prix4.txt");
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
		entry.expect(elements[0] + min);
		entry.writeDown();
	}
	
}
