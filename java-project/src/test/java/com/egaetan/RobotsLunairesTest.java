package com.egaetan;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

public class RobotsLunairesTest extends AbstractTestRunner {

	public RobotsLunairesTest() {
		super(() -> new PrixLePlusBas().main(), "Prix remporté ! 💶");
	}

	//@Test
	public void test() {
		runTestFromFile("robotsLunaires/test1.txt", "Un seul robot", "10");
	}

	private enum Orientation {
		N, W, E, S;

		public Orientation left() {
			switch (this) {
			case N:
				return W;
			case W:
				return S;
			case S:
				return E;
			case E:
				return N;
			}
			return null;
		}

		public Orientation right() {
			switch (this) {
			case N:
				return E;
			case W:
				return N;
			case S:
				return W;
			case E:
				return S;
			}
			return null;
		}
	}

	private enum Ordre {
		L, R, M;
	}
	
	public static class Robot {
		int x;
		int y;
		Orientation orientation;

		public Robot(int x, int y, Orientation orientation) {
			super();
			this.x = x;
			this.y = y;
			this.orientation = orientation;
		}

		public Robot move(Ordre ordre) {
			Robot next = new Robot(x, y, orientation);
			switch (ordre) {
			case L:
				next.orientation = orientation.left();
				break;
			case R:
				next.orientation = orientation.right();
				break;
			case M:
				switch (orientation) {
				case N:
					next.y++;
					break;
				case S:
					next.y--;
					break;
				case W:
					next.x--;
					break;
				case E:
					next.x++;
					break;
				}
			}
			return next;
		}

		public boolean isValide(int maxX, int maxY) {
			return x >= 0 && x < maxX && y >= 0 && y < maxY;
		}
		
		@Override
		public String toString() {
			return x + " " + y + " " + orientation;
		}

	}


	@Test
	//@Ignore
	public void generatePath() throws IOException {
		Random random = new Random();
		String fileName = "robotsLunaires/test2.txt";
		TestEntry entry = new TestEntry(fileName);
		int maxX = 10;
		int maxY = 10;
		int nbRobot = 1;
		int maxLength = 10;
		int minLength = 10;

		entry.line(maxX + " " + maxY);

		for (int i = 0; i < nbRobot; i++) {
			Robot p = new Robot(random.nextInt(maxX), random.nextInt(maxY), Orientation.values()[random.nextInt(Orientation.values().length)]);

			entry.line(p.toString());
			
			StringBuilder orders = new StringBuilder();
			int nbSteps = (maxLength - minLength > 0 ? random.nextInt(maxLength - minLength) : 0) + minLength;

			for (int j = 0; j < nbSteps; j++) {
				boolean orderFound = false;
				Ordre next = null;
				Robot p2 = null;
				while (!orderFound) {
					next = Ordre.values()[random.nextInt(Ordre.values().length)];
					p2 = p.move(next);

					if (p2.isValide(maxX, maxY)) {
						orderFound = true;
					}
				}
				orders.append(next);
				p = p2;
			}
			entry.line(orders.toString());
			entry.expect(p.toString());

		}
		entry.writeDown();
	}

}