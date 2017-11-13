package com.egaetan;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

public class ADNTest extends AbstractTestRunner {

	public ADNTest() {
		super(() -> new ADN().main(), "ADN décrypté 🔬");
	}
	
	@Test
	public void test() {
		Data data1 = new Data("AT", "G", "CC", "TAG");
		runTest(reader(data1::input), "Simple", s -> data1.check(s)); 		
	}

	static class Data {
		String[] brins;

		public Data(String... brins) {
			super();
			this.brins = brins;
		}
		
		public void check(String s) {
			verify(brins, s);
		}
		
		public String input() {
			return Arrays.stream(brins).collect(Collectors.joining("\n", brins.length + "\n", ""));
		}
	}
	
	private static void verify(String[] inputs, String sol) {
		Map<String, Long> counts = count(Arrays.stream(inputs));
		
		Pattern pat = Pattern.compile("#"); 
		Pattern patSpace = Pattern.compile(" ");		
		Map<String, Long> solCounts = count(pat.splitAsStream(sol).flatMap(s -> patSpace.splitAsStream(s)));
		
		Assert.assertEquals("Different size", counts.size(), solCounts.size());
		
		Optional<String> findAny = counts.keySet().stream()
			.filter(k -> counts.get(k).equals(solCounts.computeIfAbsent(k, s -> 0L)))
			.findAny();
		
		findAny.ifPresent(s -> Assert.fail(s + " count"));
		
		String[] split = pat.split(sol);
		
		String left = split[0].replaceAll(" ", "");
		String right = split[1].replaceAll(" ", "")
				.replaceAll("A", "U").replaceAll("T", "A").replaceAll("U", "T")
				.replaceAll("G", "U").replaceAll("C", "G").replaceAll("U", "C");
		
		Assert.assertEquals(left, right);
		
	}

	private static Map<String, Long> count(Stream<String> stream) {
		return stream.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
	}
	
	
}
