# Robots Lunaires [Facile]


## Enoncé

Une escouade de robots rovers doit être débarquée par la NASA sur un plateau de la lune 🌘.

Ce plateau, curieusement rectangulaire, doit être parcouru par les robots afin que leurs caméras embarquées puissent avoir une vue complète du terrain environnant à renvoyer sur Terre. 🌍

La position et l'emplacement d'un robot sont représentés par une combinaison de x et y, ses coordonnées et une lettre représentant l'un des quatre points cardinaux (N, W, E, S). Le plateau est divisé en une grille pour simplifier la navigation.

Afin de contrôler un robot, la NASA envoie une simple suite de lettres. 

Les lettres possibles sont `L`, `R` et `M`. `L` et `R` font tourner le robot de 90 degrés gauche ou droit respectivement, sans bouger de son emplacement actuel. `M` signifie avancer d'un point de grille et maintenir le même cap.
Supposons que le carré directement au nord de (x, y) est (x, y + 1).

## Format des données

### Entrée

**Ligne 1** : Les coordonnées en haut à droite du plateau, les coordonnées en bas à gauche sont supposées être 0, 0.

**Lignes 2 à 2xN+1** : Deux lignes par robots, la première ligne donne position de départ du robot, la seconde ligne donne une série d'instructions. 
La position est composée de deux entiers et d'une lettre séparés par des espaces, correspondant aux coordonnées x et y et à l'orientation du robot.

### Sortie
Pour chaque robot, ses coordonnées finales et son orientation. Chaque robot exécute ses instructions séquentiellement, ce qui signifie que le deuxième robot ne commencera à bouger que lorsque le premier aura fini de bouger.


::: Explications

::: Lecture des entrées
Il faut tout d'abord lire les données d'entrées:
+ les coordonnées maximales
+ les lignes pour chaque robot.

``` java
	Scanner sc = new Scanner(System.in);
	int maxX = sc.nextInt();
	int maxY = sc.nextInt();
	sc.nextLine();
	
	while(sc.hasNextLine()) {
		line = sc.nextLine();
		int x = Integer.parseInt(line.split(" ")[0]);
		int y = Integer.parseInt(line.split(" ")[1]);
		String orientation = line.split(" ")[2];
		
		String orders = sc.nextLine();
	}
```

💡 `Scanner.hasNextLine()` pour savoir s'il y a encore des lignes à lire


Il faut lire ensuite les ordres un par un
```java
for (int i = 0; i < line.length(); i++) {
	String ordre = line.substring(i, i+1);
}
```
:::



::: Algorithme
On nous donne ici un état initial, une suite d'actions et on nous demande de calculer l'état final.

L'algorithme sera un automate qui fera évoluer l'état du système à chaque action.

En pseudo code:
+ `LIRE ETAT INITIAL`
+ `POUR CHAQUE ACTION`
  + `EXECUTER L'ACTION`
+ `ECRIRE ETAT FINAL`


:::

::: Orientation

Deux actions peuvent modifier l'orientation, `L` tourne à gauche et `R` tourne à droite.

Tourne à gauche :
+ N ➡  W
+ W ➡  S
+ S ➡  E
+ E ➡  N


Tourne à droite :
+ N ➡  E
+ E ➡  S
+ S ➡  W
+ W ➡  N

💡 
> L'orientation peut être implémentée avec un `enum`

> On peut créer des classes internes dans le même fichier

```java
public class RobotsLunaires {
	
	enum Orientation {
		N, W, E, S;
	}
}
```

Il reste à coder les fonctions pour tourner à gauche et à droite.

Ces fonctions concernent uniquement l'orientation et ont leurs places dans la classe enum.

```java
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
```

```java
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
```

💡 Il est préférable de conserver le vocabulaire de l'énoncé, on nomme donc les fonctions `left` et `right`
:::

:::Mouvement
Le quadrillage proposé est particulier avec le bas à gauche en (0, 0), sinon rien de compliqué

```java
switch (orientation) {
	case N:
		y++;
		break;
	case S:
		y--;
		break;
	case W:
		x--;
		break;
	case E:
		x++;
		break;
}
```
:::

::: Afficher le résultat

``` java
	System.out.println(pointsJoueurA > pointsJoueurB ? "A" : "B");
```
Il faut écrire `"A"` ou `"B"` suivant leurs points respectifs.

💡  On peut ici utiliser une expression ternaire, qui montre efficacement l'alternative.

:::
:::


@[Robots Lunaires]({"stubs": ["src/main/java/com/egaetan/RobotsLunaires.java"], "command": "com.egaetan.RobotsLunairesTest#test"})
