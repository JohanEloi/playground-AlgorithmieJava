package com.egaetan;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;

public class Runner {
	private final Runnable underTest;
	private final Communication communication;

	boolean isAllOk = true;

	
	public Runner(Communication communication, Runnable underTest) {
		super();
		this.communication = communication;
		this.underTest = underTest;
	}


	public void run(String inputFile, String testName, String expected) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			System.out = new PrintStream(baos);
			System.initPath(inputFile);

			Thread runnerThread = new Thread(() -> underTest.run());
			runnerThread.start();
			
			try {
				runnerThread.join(100);
				if (runnerThread.isAlive()) {
					msg("Résultats", "✘ " + testName + " - Temps dépassé");
					isAllOk = false;
					return;
				}
			} catch (InterruptedException e) {
				throw new RuntimeException("Should never happen...", e);
			}
			

			String res = baos.toString().trim();
			Assert.assertEquals(testName, expected, res);

			msg("Résultats", "✔ "+ testName);
		} catch (AssertionError ae) {				
			isAllOk = false;
			msg("Résultats", "✘ " + ae.getMessage());
		}
	}
	
	private void msg(String title, String texte) {
		communication.msg(title, texte);
	}
}