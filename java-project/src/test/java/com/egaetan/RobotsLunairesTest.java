package com.egaetan;

import java.io.IOException;
import java.util.Random;

import org.junit.Test;

public class RobotsLunairesTest extends AbstractTestRunner {

	public RobotsLunairesTest() {
		super(() -> new RobotsLunaires().main(), "Lune explorée ! 🌝  \n En route vers les étoiles  🚀");
	}

	@Test
	public void test() {
		runTestFromFile("robotsLunaires/test1.txt", "🌑  Un seul robot", "1 7 S");
		runTestFromFile("robotsLunaires/test2.txt", "🌒  Deux robots", "6 9 S\n4 4 S\n");
		runTestFromFile("robotsLunaires/test3.txt", "🌓  Petit plateau", "39 79 E\n32 26 W\n18 29 S\n");
		runTestFromFile("robotsLunaires/test4.txt", "🌔  Grand plateau", "154 25 W\n86 151 E\n31 24 W\n148 134 E\n166 57 N\n61 110 E\n15 115 E\n9 113 S\n97 163 S\n140 189 N");
		runTestFromFile("robotsLunaires/test5.txt", "🌕  Plateau géant", "419 110 N\n41 306 W\n21 173 N\n298 274 N\n250 177 E\n475 425 N\n17 306 W\n12 84 S\n165 207 N\n386 419 N\n496 223 W\n181 407 S\n473 44 S\n19 377 S\n322 285 N\n238 272 W\n249 135 E\n59 348 S\n279 26 N\n381 415 S\n276 245 N\n344 247 W\n294 335 S\n412 309 S\n17 433 W\n269 187 W\n22 59 N\n162 242 W\n124 255 W\n212 478 W\n441 432 E\n283 180 S\n359 480 W\n278 180 W\n164 220 W\n86 340 S\n487 291 S\n313 56 S\n190 350 E\n231 331 N\n46 338 E\n404 181 E\n214 353 N\n328 416 S\n174 284 N\n313 107 N\n390 330 W\n483 12 W\n496 48 W\n59 411 S\n121 281 S\n174 435 S\n492 478 W\n157 17 S\n399 350 E\n475 371 S\n202 106 W\n303 147 N\n120 231 E\n34 159 S\n367 242 W\n372 178 W\n134 498 E\n471 281 N\n309 62 E\n166 484 S\n296 4 W\n415 384 W\n143 198 E\n326 482 N\n488 331 W\n189 392 S\n120 221 N\n247 247 W\n77 45 E\n425 80 N\n8 27 N\n7 44 W\n66 69 S\n488 345 E\n444 164 N\n390 80 N\n372 363 W\n485 458 S\n347 339 W\n439 105 E\n192 178 S\n458 71 E\n274 401 E\n171 459 E\n471 403 E\n212 124 S\n258 362 N\n388 360 W\n414 30 S\n174 293 W\n438 179 S\n154 226 S\n302 291 S\n220 324 N\n");
	}

	
	@Test
	//@Ignore
	public void generatePath() throws IOException {
		Random random = new Random();
		String fileName = "robotsLunaires/test5.txt";
		TestEntry entry = new TestEntry(fileName);
		int maxX = 500;
		int maxY = 500;
		int nbRobot = 100;
		int maxLength = 20000;
		int minLength = 15000;

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


}