package com.egaetan;

import org.junit.Test;

public class ADNTest extends AbstractTestRunner {

	public ADNTest() {
		super(() -> new ADN().main(), "ADN décrypté 🔬");
	}
	
	@Test
	public void test() {
		Data data1 = new Data("AT", "G", "CC", "TAG");
		runTest(reader(() -> input(data1), "Simple", s -> verify(data1.brins, s)); 		
	}

	static class Data {
		String[] brins;

		public Data(String... brins) {
			super();
			this.brins = brins;
		}
		
		public void verify(String s) {
			verify(brins, s);
		}
	}
	
	private String input(String...sol) {
		return Arrays.stream(sol).collect(Collectors.joining("\n", sol.length + "\n", ""));
	}
	
	private void verify(String[] inputs, String sol) {
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

	private Map<String, Long> count(Stream<String> stream) {
		return stream.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
	}
	
	
}
