# Prix le plus bas [Facile]

## Inspiré de [BattleDev](https://battledev.blogdumoderateur.com/) Novembre2017


## Enoncé

Pendant les soldes d'hiver, les bonnes affaires partent vite. C'est souvent le premier arrivé sur place qui déniche les bonnes occasions. Une seule solution : se rendre dans un maximum de magasins en quelques heures.

Afin d'optimiser au maximum votre temps, vous décidez de créer votre propre comparateur de prix. Vous alimentez votre comparateur depuis une base de données contenant des catalogues de produits provenant de plusieurs enseignes différentes.

L'objectif de ce challenge est de déterminer le prix le plus bas pour un produit donné. Un produit peut apparaître plusieurs fois dans le comparateur avec des prix différents (en fonction du prix affiché par les différentes enseignes).

## Format des données

### Entrée

Ligne 1 : un entier N compris entre 10 et 10000 représentant le nombre de produits dans votre comparateur.

Ligne 2 : une chaîne P comprenant entre 2 et 50 caractères représentant le produit recherché.

Lignes 3 à N+2 : une chaîne comprenant entre 2 et 50 caractères et un entier séparés par un espace représentant respectivement le nom d'un produit et le prix associé. Le produit P apparaîtra au moins une fois dans la liste.

### Sortie
Un entier représentant le prix le plus bas du produit P dans la liste.


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
Il faut écrire `"A"` ou `"B"` suivant leurs points respectifs.

💡  On peut ici utiliser une expression ternaire, qui montre efficacement l'alternative.

:::
:::


@[Bataille]({"stubs": ["src/main/java/com/egaetan/Bataille.java"], "command": "com.egaetan.BatailleTest#test"})
