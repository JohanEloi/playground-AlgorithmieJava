package com.egaetan.generators;

import java.io.IOException;
import java.util.Random;

import org.junit.Test;

import com.egaetan.TestEntry;

public class HydrophonieGen {

	@Test
	public void generateLongListe() throws IOException {
		Random random = new Random();
		TestEntry entry = new TestEntry("hydroponie/test6.txt");
		entry.line("50");

		for (int i = 0; i < 50; i++) {
			StringBuilder line = new StringBuilder();
			for (int j = 0; j < 50; j++) {
				line.append(random.nextInt(100) < 10 ? 'X' : '.');
			}				
			entry.line(line);
		}
		
		entry.expect(10);
		entry.writeDown();
	}
	
}
