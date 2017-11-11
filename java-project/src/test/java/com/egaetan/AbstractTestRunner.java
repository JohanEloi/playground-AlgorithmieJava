package com.egaetan;

import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.After;
import org.junit.Before;

public class AbstractTestRunner {

	protected Communication communication = new Communication();
	protected Runner runner;
	private final String victoryMsg;

	public AbstractTestRunner(Runnable underTest, String victoryMsg) {
		this.victoryMsg = victoryMsg;
		runner = new Runner(communication, () -> underTest.run());
	}

	@Before
	public void before() {
		msg("Résultats", "Le code compile");
	}

	@After
	public void conditionVictory() {
		if (runner.isAllOk) {
			success();
		} else {
			fail();
		}
	}

	protected void fail() {
		msg("Oops! 🐞", "Certains validateurs ne passent pas. :(");
		communication.success(false);
	}

	protected void success() {
		msg("Succès", this.victoryMsg);
		communication.success(true);
	}

	protected void runTestFromFile(String inputFile, String testName, String expected) {
		runner.run(() -> fromFile(inputFile), testName, expected);
	}

	private Reader fromFile(String path) {
		try {
			Reader reader = new InputStreamReader(System.class.getClassLoader().getResourceAsStream(path));
			return reader;
		} catch (Exception e) {
			throw new RuntimeException("Test files in error", e);
		}
	}

	protected void msg(String title, String texte) {
		communication.msg(title, texte);
	}
}