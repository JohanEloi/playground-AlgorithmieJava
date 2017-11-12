// { autofold
package com.egaetan;
// }

/*******
 * Lire les donnees depuis System.in
 * Utilise System.out.println pour afficher le resultat
 * Utilise System.err.println pour afficher des donnees de debug
 * ***/
import java.util.*;

public class RobotsLunaires2 {
	
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


	public void main() {
		String  line;
		Scanner sc = new Scanner(System.in);
		int maxX = sc.nextInt();
		int maxY = sc.nextInt();
		sc.nextLine();
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			int x = Integer.parseInt(line.split(" ")[0]);
			int y = Integer.parseInt(line.split(" ")[1]);
			Orientation o = Orientation.valueOf(line.split(" ")[2]);
			Robot r = new Robot(x, y, o);
			
			line = sc.nextLine();
			for (int i = 0; i < line.length(); i++) {
				Ordre ordre = Ordre.valueOf(line.substring(i, i+1));
				r = r.move(ordre);
				//System.err.println(r);
			}
			System.out.println(r);
			/* Lisez les données et effectuez votre traitement */
		}
	/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/

	}
}