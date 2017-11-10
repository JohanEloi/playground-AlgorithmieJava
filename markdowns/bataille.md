# Bataille


## Enoncé

Un soir, deux frères profitent de l'absence de leurs parents pour commander des burgers. Pour ne pas laisser de trace, l'un d'entre eux doit descendre les poubelles avant minuit. Ils décident de se départager au jeu de la bataille. Le principe est simple, chaque joueur a le même nombre de cartes et à chaque tour, chaque joueur présente une carte. Celui qui a la carte de plus grande valeur remporte le point. Si les deux cartes ont la même valeur, personne ne remporte le point. Le gagnant du jeu est celui qui a le plus de points à la fin.

L'objectif de ce challenge est de déterminer qui ne descendra pas les poubelles ce soir (gagnant de la partie).

On va nommer les deux frères A et B. Pour simplifier, nous allons considérer que les cartes ont des valeurs pouvant aller de 1 à 10. Nous vous garantissons qu'il y a bien un gagnant à la fin de la partie (pas d'égalité possible entre les deux joueurs).

## Format des données

### Entrée

**Ligne 1** : un entier N compris entre 10 et 100 représentant le nombre de tours.

**Lignes 2 à N+1** : deux entiers compris entre 1 et 10 séparés par un espace représentant la carte du joueur A et celle du joueur B.

### Sortie
Le caractère A ou le caractère B représentant le gagnant de la partie.


::: Explications

::: Lecture des entrées
Il faut tout d'abord lire les données d'entrées
``` java
	Scanner sc = new Scanner(System.in);
	int nombreDeTours = sc.nextInt();
	sc.nextLine();
```

Puis dans une boucle lire les cartes des joueurs A et B
``` java
	for (int i = 0; i< n; i++) {		
		int carteJoueurA = sc.nextInt();
		int carteJoueurB = sc.nextInt();

		sc.nextLine();
	}
```		

💡 il ne faut pas oublier le `sc.nextLine()` pour passer à la ligne suivante.
:::

::: Compter les points
Il faut compter les points de chacun des joueurs dans la boucle.
Les variables comptant les points sont définies en dehors de la boucle

``` java
	int pointsJoueurA = 0;
	int pointsJoueurB = 0;
	for (int i = 0; i< n; i++) {		
		int carteJoueurA = sc.nextInt();
		int carteJoueurB = sc.nextInt();

		if (carteJoueurA > carteJoueurB) {
			pointsJoueurA++;
		}
		else if (carteJoueurB > carteJoueurA) {
			pointsJoueurB++;
		}
		
		sc.nextLine();
	}
```	
💡 il ne faut pas oublier le cas d'égalité, où les scores des joueurs ne changent pas. Ici, il est implicite, grâce au `else if`.

:::

::: Afficher le résultat

``` java
	System.out.println(pointsJoueurA > pointsJoueurB ? "A" : "B");
```
Il faut écrire `A` ou `B` suivant leurs points respectifs.

💡  On peut ici utiliser une expression ternaire, qui montre efficacement l'alternative.

:::
:::


@[Bataille]({"stubs": ["src/main/java/com/egaetan/Bataille.java"], "command": "com.egaetan.BatailleTest#test"})
