package com.egaetan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BruteForce {

	public static void mainInt(String[] args) {
		List<List<Integer>> allCombinaisons = new BruteForce().allCombinaisons(IntStream.of(1, 2, 3, 4).mapToObj(i -> Integer.valueOf(i)).collect(Collectors.toList()));
		String map = allCombinaisons.stream()
		.map(i -> i.stream().map(j -> j.toString()).collect(Collectors.joining(" ")))
		.collect(Collectors.joining("\n"));
		System.out.println(map);
	}
	public static void main(String[] args) {
		List<List<String>> allCombinaisons = new BruteForce().allCombinaisons(Arrays.asList("A", "B", "C", "D"));
		String map = allCombinaisons.stream()
				.map(i -> i.stream().map(j -> j.toString()).collect(Collectors.joining(" ")))
				.collect(Collectors.joining("\n"));
		System.out.println(map);
	}
	
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

}
