package com.egaetan;

import org.junit.Assert;
import org.junit.Test;

public class CubettoTest extends AbstractTestRunner {

	public CubettoTest() {
		super(() -> new Cubetto().main(), "Bataille gagnée 🃏");
	}
	
	@Test
	public void test() {
		runFromData("Cubetto", new CubettoData(
				  "......"
				+ ".>..O."
				+ "......"
				+ "......"
				+ "......"
				+ "......"
						  ));
	}

	static class CubettoData implements Data {

		boolean possible = true;
		String map;
		
		
		
		public CubettoData(String map) {
			super();
			this.map = map;
		}

		@Override
		public void check(String actual) {
			checkNb(actual);
			// TODO Auto-generated method stub
			
		}

		private void checkNb(String actual) {
			int verts= 0;
			int jaunes = 0;
			int rouges = 0;
			int bleus = 0;
			for (int i = 0; i < actual.length(); i++) {
				switch (actual.charAt(i)) {
				case 'V':
					verts ++;
				case 'R':
					rouges ++;
				case 'J':
					jaunes ++;
				case 'B':
					bleus ++;
					break;
				case '#':
					break;
				default:
					Assert.fail("'" + actual.charAt(i) + "' innatendu");
					break;
				}
			}
			Assert.assertTrue("Nombre de verts", verts <= 4);
			Assert.assertTrue("Nombre de jaunes", jaunes <= 4);
			Assert.assertTrue("Nombre de rouges", rouges <= 4);
			Assert.assertTrue("Nombre de bleus", bleus <= 4);
		}

		@Override
		public String input() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	
}