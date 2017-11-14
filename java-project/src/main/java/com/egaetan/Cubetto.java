// { autofold
package com.egaetan;
// }

/*******
 * Lire les donnees depuis System.in
 * Utilise System.out.println pour afficher le resultat
 * Utilise System.err.println pour afficher des donnees de debug
 * ***/
import java.util.*;

public class Cubetto {
	
	public void main() {
		String  line;
		Scanner sc = new Scanner(System.in);
		for (int iy = 0; iy < 6; iy++) {
			line = sc.nextLine();
			for (int ix = 0; ix < 6; ix++) {
				switch (line.charAt(ix)) {
				case 'X':
					
					break;
				case 'O':
					
					break;
				case 'P':
					
					break;
				case '^':
					
				case '<':
					
				case 'v':
					
				case '>':
					
					break;

				case '.':
				default:
					break;
				}
			}
			/* Lisez les données et effectuez votre traitement */
		}
	/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/

	}
}
