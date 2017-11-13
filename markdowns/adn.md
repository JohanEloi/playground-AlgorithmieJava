# ADN [Difficile]

## Inspiré de [BattleDev](https://battledev.blogdumoderateur.com/) Novembre2017


## Enoncé


Vous avez pour mission d’évaluer le potentiel génétique des candidats d’un nouveau centre spatial 🌠. Pour cela, vous avez développé une méthode de séquençage de leur ADN. Le candidat est une espèce à 1 chromosome double brin. C’est-à-dire que son ADN peut être vu comme deux chaînes de caractères a et b composées exclusivement des lettres `A`, `C`, `G` et `T`. Par ailleurs ces deux chaînes sont complémentaires, elles sont donc de même longueur et vérifient :
- a[i] = `A` ⇔ b[i] = `T`
- a[i] = `T` ⇔ b[i] = `A`
- a[i] = `C` ⇔ b[i] = `G`
- a[i] = `G` ⇔ b[i] = `C`

Lors du séquençage, les deux chaînes se cassent en plusieurs petits fragments qui sont mélangés entre eux. Votre méthode n’est pas si mauvaise que ça, vous savez que l’ordre des caractères dans un fragment n’a pas été inversé. Vous êtes cependant obligé écrire un programme pour recoller les morceaux dans un ordre plausible. Pour vous assurer que vous n’avez oublié aucun fragment, votre programme renverra les deux chaînes a et b en séparant les différents fragments par des espaces.

_Indication_ : l’ADN d’un candidat n’est pas très complexe, il n’y aura jamais plus de **8** fragments, vous pourrez donc procéder par énumération exhaustive (force brute).

## Exemple

Si vous avez les fragments suivants :
- `AT`
- `G`
- `CC`
- `TAG`

Une solution possible est alors :

| ----- | ---- |
| Brin1 | `TAG` `G` |
| Brin2 | `AT` `CC` |


Ce qui donne la sortie suivante (voir plus bas pour le format de sortie) :

`TAG␣G#AT␣CC`

On pourrait bien sûr permuter les brins 1 et 2 et la solution serait aussi correcte.

## Format des données

### Entrée
**Ligne 1**: un entier **N** compris entre 2 et 8, indiquant le nombre de fragments d’ADN.

**Lignes 2 à N+1**: une chaine comprenant entre 1 et 16 caractères, composée exclusivement des lettres `A`, `T`, `C`, et `G` représentant un fragment.

### Sortie
Une chaîne de caractères représentant les 2 brins. Les 2 brins sont séparés par le caractère `#` et dans un brin donné les fragments utilisés sont séparés par des espaces.


::: Explications

Le sujet consiste à brute-forcer toutes les combinaisons possibles pour trouver une solution.

:::Brut-force

Méthodologie :
+ Générer toutes les combinaisons
+ Tester les combinaisons

:::

::: Générer les combinaisons

Le plus simple est la méthode récursive.

On considère que l'on va générer toutes les combinaisons de nombres de 1 à **N**, correspondants

 + On choisit le premier élément
 + puis le second en vérifiant que le second est différent du premier
 + puis le troisième en vérifiant le troisième est différent du premier et du second
 + ...
 + puis le nième en vérifiant qu'il est différent du premier, second, ... et du (n-1)ème

```java
    public <T> List<List<T>> allCombinaisons(List<T> source) {
		return allCombinaisons(source.size(), new ArrayList<>(), source.size())
				.stream()
				.map(l -> l.stream()
						.map(i -> source.get(i))
						.collect(Collectors.toList()))
				.collect(Collectors.toList());
	}

	public List<List<Integer>> allCombinaisons(int nb, List<Integer> passed, int n) {
		List<List<Integer>> res = new ArrayList<>();
		if (n == 0) {
			return Collections.singletonList(passed);
		}
		for (int i = 0; i < nb; i++) {
			if (passed.contains(i)) {
				continue;
			}
			List<Integer> passedNext = new ArrayList<>(passed);
			passedNext.add(i);
			res.addAll(allCombinaisons(nb, passedNext, n -1));
		}
		return res;
	}
```

On peut choisir de travailler uniquement sur les indices des permutations et mapper ensuite nos objets.

🤔 Notre algorithme ici génère des doublons lorsque la source contient des doublons

:::


:::


@[ADN]({"stubs": ["src/main/java/com/egaetan/ADN.java"], "command": "com.egaetan.ADNTest#test"})
