package com.egaetan;

import org.junit.After;
import org.junit.Before;

public class AbstractTestRunner {

	protected Communication communication = new Communication();
	protected Runner runner;
	private final String victoryMsg;

	public AbstractTestRunner(Runnable underTest, String victoryMsg) {
		this.victoryMsg = victoryMsg;
		runner = new Runner(communication, () -> new Bataille().main());
	}

	@Before
	public void before() {
		msg("Résultats", "Le code compile");
	}

	@After
	public void conditionVictory() {
		if (runner.isAllOk) {
			msg("Succès", this.victoryMsg);
			success();
		} else {
			communication.success(false);
			fail();
		}
	}

	protected void fail() {
		msg("Oops! 🐞", "Certains validateurs ne passent pas. :(");
	}

	protected void success() {
		communication.success(true);
	}

	protected void runTest(String inputFile, String testName, String expected) {
		runner.run(inputFile, testName, expected);
	}

	protected void msg(String title, String texte) {
		communication.msg(title, texte);
	}
}