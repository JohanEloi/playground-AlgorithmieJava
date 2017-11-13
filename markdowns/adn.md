# ADN [Difficile]

## Inspiré de [BattleDev](https://battledev.blogdumoderateur.com/) Novembre2017


## Enoncé


Vous avez pour mission d’évaluer le potentiel génétique des candidats d’un nouveau centre spatial. Pour cela, vous avez développé une méthode de séquençage de leur ADN. Le candidat est une espèce à 1 chromosome double brin. C’est-à-dire que son ADN peut être vu comme deux chaînes de caractères a et b composées exclusivement des lettres `A`, `C`, `G` et `T`. Par ailleurs ces deux chaînes sont complémentaires, elles sont donc de même longueur et vérifient :
- a[i] = `A` ⇔ b[i] = `T`
- a[i] = `T` ⇔ b[i] = `A`
- a[i] = `C` ⇔ b[i] = `G`
- a[i] = `G` ⇔ b[i] = `C`

Lors du séquençage, les deux chaînes se cassent en plusieurs petits fragments qui sont mélangés entre eux. Votre méthode n’est pas si mauvaise que ça, vous savez que l’ordre des caractères dans un fragment n’a pas été inversé. Vous êtes cependant obligé écrire un programme pour recoller les morceaux dans un ordre plausible. Pour vous assurer que vous n’avez oublié aucun fragment, votre programme renverra les deux chaînes a et b en séparant les différents fragments par des espaces.

Indication : l’ADN d’un candidat n’est pas très complexe, il n’y aura jamais plus de **8** fragments, vous pourrez donc procéder par énumération exhaustive (force brute).

## Exemple

Si vous avez les fragments suivants :
- `AT`
- `G`
- `CC`
- `TAG`

Une solution possible est alors :

| ----- | ---- |
| Brin1 | TAGG |
| Brin2 | ATCC |


Ce qui donne la sortie suivante (voir plus bas pour le format de sortie) :

`TAG G#AT CC`

On pourrait bien sûr permuter les brins 1 et 2 et la solution serait aussi correcte.

## Format des données

### Entrée
**Ligne 1**: un entier **N** compris entre 2 et 8, indiquant le nombre de fragments d’ADN.
**Lignes 2 à N+1**: une chaine comprenant entre 1 et 16 caractères, composée exclusivement des lettres `A`, `T`, `C`, et `G` représentant un fragment.

### Sortie
Une chaîne de caractères représentant les 2 brins. Les 2 brins sont séparés par le caractère `#` et dans un brin donné les fragments utilisés sont séparés par des espaces.


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


@[Hydroponie]({"stubs": ["src/main/java/com/egaetan/ADN.java"], "command": "com.egaetan.ADN#test"})
