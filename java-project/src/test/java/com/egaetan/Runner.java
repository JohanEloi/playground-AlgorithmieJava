package com.egaetan;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

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
			ByteArrayOutputStream baos = new ByteArrayOutputStream(10 * 1024);
			System.out = new PrintStream(baos);
			System.initPath(inputFile);

			ByteArrayOutputStream logs = new ByteArrayOutputStream(10 * 1024);
			System.err = new PrintStream(logs);

			Thread runnerThread = new Thread(this::doRun, "Runner");
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
			
			String logsOut = logs.toString().trim();
			if (logsOut.length() > 0) {
				Arrays.stream(logsOut.split("\n")).forEach(l -> msg("Log - "+testName, l));
			}
			
			String res = baos.toString().trim();
			Assert.assertEquals(testName, expected, res);

			msg("Résultats", "✔ "+ testName);
		} catch (AssertionError ae) {				
			isAllOk = false;
			msg("Résultats", "✘ " + ae.getMessage());
		}
	}


	private void doRun() {
		try {
			underTest.run();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError("Exception :" + e.getMessage());
		}
	}
	
	private void msg(String title, String texte) {
		communication.msg(title, texte);
	}
}