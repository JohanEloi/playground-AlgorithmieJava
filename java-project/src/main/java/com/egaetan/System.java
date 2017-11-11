package com.egaetan;

import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.function.Supplier;

public class System {

	public static Readable in;
	public static PrintStream out = java.lang.System.out;
	public static PrintStream err = java.lang.System.err;

	public void exit(int n) {
		java.lang.System.exit(n);
	}

	public static void initPath(String path) {
		try {
			Reader reader = //new FileReader(path); 
					new InputStreamReader(System.class.getClassLoader().getResourceAsStream(path));
			in = reader;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initSystemIn(Supplier<Reader> supplier) {
		in = supplier.get();
	}
}
