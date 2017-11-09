package com.egaetan;

import java.io.FileReader;
import java.io.PrintStream;

public class System {

	
	public static Readable in;
	public static PrintStream out = java.lang.System.out;
	public static PrintStream err = java.lang.System.err;
	
	static {
		init();
	}

	private static void init() {
		initPath("in.txt");
	}

	public void exit(int n) {
		java.lang.System.exit(n);
	}
	
	public static void initPath(String path) {
		try {
			FileReader reader = new FileReader(path);
			in = reader;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
