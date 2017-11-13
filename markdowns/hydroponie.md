# Hydroponie [Moyen]

## Inspiré de [BattleDev](https://battledev.blogdumoderateur.com/) Novembre2017


## Enoncé


Il y a fort longtemps, dans une galaxie lointaine, très lointaine… Vous habitez une planète désertique à deux soleils, et êtes le modeste exploitant d’une ferme hydroponique. Votre exploitation est représentée par une grille carrée sur laquelle sont disposés des évaporateurs d’humidité. Chaque évaporateur d’humidité irrigue et rend ainsi cultivables les 8 cases qui lui sont adjacentes (on ne peut rien cultiver sur une case contenant un évaporateur d’humidité).

Le but de ce challenge est de déterminer le nombre de cases cultivables de votre exploitation.

## Format des données

### Entrée


**Ligne 1**: un entier **N** compris entre 3 et 50 représentant la taille de votre ferme (une grille carrée de dimension **N** x **N**)
**Lignes 2 à N+1**: les lignes de la carte représentées par des chaînes de N caractères. Les caractères de la ligne sont soit `X` (un évaporateur) soit `.` (une case vide).

### Sortie
Un entier représentant le nombre de cases cultivables de votre ferme.


::: Explications

::: Lecture des entrées
Il faut tout d'abord lire la taille de la carte
``` java
	Scanner sc = new Scanner(System.in);
	int taille = sc.nextInt();
	sc.nextLine();
```

Puis dans une double boucle lire la carte
``` java
	for (int i = 0; i < taille; i++) {
		line = sc.nextLine();
		for (int j = 0; j < n; j++) {
			switch (line.charAt(j)) {
				case 'X':
				    /* evaporateur en (j, i) */
                    break;
                case '.'
                    /* case vide en (j, i) */
                    break;
			}
		}
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

🔥 `String.equals(String other)` pour vérifier l'égalité entre deux chaînes de caractères

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

💡 `Integer.MAX_VALUE` est la plus grande valeur possible pour un entier de type `int` 


:::

::: Code
Tout ensemble :

``` java
    int minimum = Integer.MAX_VALUE;
    for (int i = 0; i < nombreProduits; i++) {
		line = sc.nextLine();
		String produitCourant = line.split(" ")[0];
		int prix = Integer.parseInt(line.split(" ")[1]);
	    if (produitCourant.equals(nomProduit) && prix < minimum) {
	        minimum = prix
	    }
	}
	System.out.println(minimum);
```	


💡 Il faut déclarer et initialiser la variable `minimum` en dehors de la boucle.


:::

:::


@[Hydroponie]({"stubs": ["src/main/java/com/egaetan/Hydroponie.java"], "command": "com.egaetan.HydroponieTest#test"})
