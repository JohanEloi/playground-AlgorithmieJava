# Cubetto

## Original

https://www.youtube.com/watch?v=c0eB0NjMiiY

[Cubetto](https://www.primotoys.com/fr/)

## Enoncé

Cubetto est un robot de bois adorable qui apprend aux enfants les bases de la programmation informatique par le jeu et l’imaginaire.
Il est constitué de :
+ **Cubetto**. Fabriqué en bois robuste, Cubetto fera découvrir le monde de la programmation à votre enfant. Sans écran, intuitif et prêt à l’emploi.
+ **Un langage de programmation** à toucher et à manipuler comme des LEGO®. À chaque bloc, une action. Combinez-les pour créer des programmes.
+ **Un panneau de commandes**. Placez les blocs sur le panneau pour indiquer la direction à Cubetto. Appuyez sur le bouton bleu et Cubetto exécute votre premier programme.
+ Mappemondes et livres d'histoires. Poursuivez l’aventure avec nos mappemondes, livres d’histoires et **défis**
 
_extraits de la brochure commerciale_ 

Très vite papa et maman se sont lancés des défis. La mappemonde sur laquelle se déplace Cubetto est une grille de **6x6**.

Certaines cases contiennent des images de rivière et ne doivent pas être traversées. Le défi consiste à partir d'une case et d'une orientation donnée à déplacer le robot jusqu'à la case d'arrivée en évitant les cases interdites et en passant par les cases obligatoires.

Le langage de programmation consiste en blocs colorés :
+ 4 verts font avancer
+ 4 rouges tourner à droite
+ 4 jaunes tourner à gauche
+ 4 bleus appellent la fonction

Le panneau de commande accueille les blocs, il est séparé en deux parties :
+ 12 emplacement pour le programme principal
+ 4 emplacement pour la fonction

 




## Format des données

### Entrée

**Ligne 1 à 6** : les lignes de la carte représentées par des chaînes de N caractères. Les caractères de la ligne sont  :
+ `X` case interdite
+ `.` case vide
+ `O` case arrivée
+ `P` case passage obligatoire

### Sortie

Une chaîne de caractère correspondant au chemin avec
+ `V` pour vert
+ `R` pour rouge
+ `J` pour jaune
+ `B` pour bleu

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
