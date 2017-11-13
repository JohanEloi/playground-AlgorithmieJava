package com.egaetan;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import com.egaetan.ADNTest.ADNData;

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
		Arrays.stream(victoryMsg.split("\n")).forEach(v -> msg("Succès ✨ ", v));
		communication.success(true);
	}

	protected void runTestFromFile(String inputFile, String testName, String expected) {
		runner.run(() -> fromFile(inputFile), testName, actual -> Assert.assertEquals(expected.trim(), actual.trim()));
	}

	protected void runTest(Supplier<Reader> inputs, String testName, Consumer<String> valideur) {
		runner.run(inputs, testName, valideur);
	}
	
	protected void runFromData(String title, Data data) {
		runTest(reader(data::input), title, data::check);
	}

	protected Supplier<Reader> reader(Supplier<String> from) {
		return () -> new StringReader(from.get());
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
