package com.egaetan;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Assert;
import org.junit.Test;

public class BatailleTest {

	public static class Runner {
		final Runnable underTest;

		boolean isAllOk = true;
		public Runner(Runnable underTest) {
			super();
			this.underTest = underTest;
		}

		public void run(String inputFile, String testName, String expected) throws FileNotFoundException {
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
	}


	Runner runner;


	@Test
	public void test() throws FileNotFoundException {
		runner = new Runner(() -> new Bataille().main());
		msg("Résultats", "Le code compile");
		runTest("bataille1.txt", "Simple test, A vainqueur", "A");
		runTest("bataille2.txt", "Simple test, B vainqueur", "B");
		runTest("bataille3.txt", "Test 2", "B");
		runTest("bataille4.txt", "Ex-Aequos", "A");
		runTest("bataille5.txt", "100 valeurs", "A");
		runTest("bataille6.txt", "Pas de triches", "A");

		

		//			Assert.assertEquals("Running Universe.countAllStars(2, 3)...", 5, Universe.countAllStars(2, 3));
		//			Assert.assertEquals("Running Universe.countAllStars(9, -3)...", 6, Universe.countAllStars(9, -3));
		if (runner.isAllOk) {
			msg("Succès", "Bataille gagnée 🃏");
			success(true);
		}
		else {
			success(false);
			msg("Oops! 🐞", "Certains validateurs ne passent pas. :(");
			//msg("Aide 💡", "");
		}

		/*if (existsInFile("Arrays.stream(galaxies).sum()", new File("./src/main/java/com/yourself/Universe.java"))) {
				msg("My personal Yoda, you are. 🙏", "* ● ¸ .　¸. :° ☾ ° 　¸. ● ¸ .　　¸.　:. • ");
				msg("My personal Yoda, you are. 🙏", "           　★ °  ☆ ¸. ¸ 　★　 :.　 .   ");
				msg("My personal Yoda, you are. 🙏", "__.-._     ° . .　　　　.　☾ ° 　. *   ¸ .");
				msg("My personal Yoda, you are. 🙏", "'-._\\7'      .　　° ☾  ° 　¸.☆  ● .　　　");
				msg("My personal Yoda, you are. 🙏", " /'.-c    　   * ●  ¸.　　°     ° 　¸.    ");
				msg("My personal Yoda, you are. 🙏", " |  /T      　　°     ° 　¸.     ¸ .　　  ");
				msg("My personal Yoda, you are. 🙏", "_)_/LI");
			} else {
				msg("Kudos 🌟", "Did you know that since Java8 is out you can use streams? Try it!");
				msg("Kudos 🌟", "");
				msg("Kudos 🌟", "int[] galaxies = {37, 3, 2};");
				msg("Kudos 🌟", "int totalStars = Arrays.stream(galaxies).sum(); // 42");
			}*/
	}

	private void runTest(String inputFile, String testName, String expected)  throws FileNotFoundException {
		runner.run(inputFile, testName, expected);
	}


	private static void msg(String channel, String msg) {
		java.lang.System.out.println(String.format("TECHIO> message --channel \"%s\" \"%s\"", channel, msg));
	}

	private static void success(boolean success) {
		java.lang.System.out.println(String.format("TECHIO> success %s", success));
	}

	// check if a string exists in a text file
	private static boolean existsInFile(String str, File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		try {
			while (scanner.hasNextLine()) {
				if (scanner.nextLine().contains(str))
					return true;
			}
			return false;
		} finally {
			scanner.close();
		}
	}
}

