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
>`String.charAt(int index)` pour accéder au ième caractère d'une chaîne de caractères
:::

::: Algorithme
Le sujet consiste à compter des cases :
+ Sans compter deux fois la même case _(irriguée par deux évaporateurs)_
+ En ignorant certaines cases _(contenant un évaporateur)_
+ Sans sortir de la grille _(un évaporateur sur un bord, n'irrigue que les cases à l'intérieur de la grille)_
 
:::


:::


@[Hydroponie]({"stubs": ["src/main/java/com/egaetan/Hydroponie.java"], "command": "com.egaetan.HydroponieTest#test"})
