# Prix le plus bas [Facile]

## Inspiré de [BattleDev](https://battledev.blogdumoderateur.com/) Novembre2017


## Enoncé

Pendant les soldes d'hiver, les bonnes affaires partent vite. C'est souvent le premier arrivé sur place qui déniche les bonnes occasions. Une seule solution : se rendre dans un maximum de magasins en quelques heures.

Afin d'optimiser au maximum votre temps, vous décidez de créer votre propre comparateur de prix. Vous alimentez votre comparateur depuis une base de données contenant des catalogues de produits provenant de plusieurs enseignes différentes.

L'objectif de ce challenge est de déterminer le prix le plus bas pour un produit donné. Un produit peut apparaître plusieurs fois dans le comparateur avec des prix différents (en fonction du prix affiché par les différentes enseignes).

## Format des données

### Entrée

**Ligne 1** : un entier **N** compris entre 10 et 10000 représentant le nombre de produits dans votre comparateur.

**Ligne 2** : une chaîne **P** comprenant entre 2 et 50 caractères représentant le produit recherché.

**Lignes 3 à N+2** : une chaîne comprenant entre 2 et 50 caractères et un entier séparés par un espace représentant respectivement le nom d'un produit et le prix associé. Le produit **P** apparaîtra au moins une fois dans la liste.

### Sortie
Un entier représentant le prix le plus bas du produit **P** dans la liste.


::: Explications

::: Lecture des entrées
Il faut tout d'abord lire les données d'entrées
``` java
	Scanner sc = new Scanner(System.in);
	int nombreProduits = sc.nextInt();
	sc.nextLine();
	String nomProduit = sc.nextLine();
	sc.nextLine();
```

Puis dans une boucle lire les cartes des joueurs A et B
``` java
	for (int i = 0; i < nombreProduits; i++) {
		line = sc.nextLine();
		String produitCourant = line.split(" ")[0];
		int prix = Integer.parseInt(line.split(" ")[1]);
	}
```		

💡 
>`String[] java.lang.String.split(String regex)` pour séparer la ligne en un tableau
>`Integer.parseInt(String s)` pour transformer une chaîne de caractères en nombre
:::

::: Chercher le prix le plus bas
A l'intérieur de la boucle, il faut d'abord savoir si le produit courant correspond au produit demandé

``` java
	if (produitCourant.equals(nomProduit)) {
	}
```	

💡 String.equals(String other) pour vérifier l'égalité entre deux chaînes de caractères

Il faut chercher le prix le plus petit.
On compare chaque prix au plus petit prix déjà trouvé.
Si il est plus petit, on met à jour le plus petit prix trouvé.

``` java
	if (produitCourant.equals(nomProduit) && prix < minimum) {
	    minimum = prix
	}
```	


Il ne faut pas oublier de déclarer et d'initialiser la variable minimum.
On choisit de l'initialiser avec une valeur très grande.
```java
    int minimum = Integer.MAX_VALUE;
```

:::

:::


@[Prix le plus bas]({"stubs": ["src/main/java/com/egaetan/PrixLePlusBas.java"], "command": "com.egaetan.PrixLePlusBasTest#test"})
