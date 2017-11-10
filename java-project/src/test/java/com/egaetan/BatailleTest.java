package com.egaetan;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class BatailleTest {

	
	private void runTest(String inputFile, String testName, String expected)  throws FileNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		System.out = new PrintStream(baos);
		System.initPath(inputFile);
				
		Bataille.main(new String[0]);
		
		String res = baos.toString().trim();
		Assert.assertEquals(testName, expected, res);
		
		msg("Succès", ":white_check_mark: "+ testName);
	}
	
	@Test
	public void test() throws FileNotFoundException {
		try {
			msg("Succès", "Le code compile");
			runTest("bataille1.txt", "Simple A vainqueur", "A");
			runTest("bataille2.txt", "Simple B vainqueur", "B");
			
			
			
			
			
//			Assert.assertEquals("Running Universe.countAllStars(2, 3)...", 5, Universe.countAllStars(2, 3));
//			Assert.assertEquals("Running Universe.countAllStars(9, -3)...", 6, Universe.countAllStars(9, -3));
			success(true);

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
		} catch (AssertionError ae) {
			success(false);
			msg("Oops! 🐞", ae.getMessage());
			msg("Hint 💡", "");
		}
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
