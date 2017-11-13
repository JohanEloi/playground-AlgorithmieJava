package com.egaetan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

public class ADNTest extends AbstractTestRunner {

	public ADNTest() {
		super(() -> new ADN().main(), "ADN décrypté 🔬");
	}
	
	
	static Random random = new Random();
	public String generateBrin(int size) {
		char[] vals = new char[]{'A','T','G','C'};
		StringBuilder sb = new StringBuilder();
		for (int i =0; i < size; i++) {
			sb.append(vals[random.nextInt(4)]);
		}
		return sb.toString();
	}
	
	@Test
	public void gen() {
		String origine = generateBrin(24 + random.nextInt(5));
		String alls = origine + "#" + reverse(origine);
		
		int m = origine.length();
		
		while (true) {
			
			List<String> cutted = new ArrayList<>();
			String alls$ = new String(alls); 
			
			while (alls$.length() != 0) {
				int a = Math.min(alls$.length(), random.nextInt(16 - 1) + 1);
				
				String substring = alls$.substring(0, a);
				cutted.add(substring);
				alls$ = alls$.substring(a);
			}
			
			cutted = cutted.stream().flatMap(s -> s(s)).filter(s -> s.length() > 0).collect(Collectors.toList());
			
			if (cutted.size() >= 2 && cutted.size() <=  8 && cutted.size() == 8) {
				System.out.println(cutted.stream().collect(Collectors.joining("\", \"", "\"", "\"")));
				break;
			}
			
		}
		
		
		
	}

	public Stream<? extends String> s(String s) {
		try {
			return s.contains("#") && !s.endsWith("#") ? Stream.of(s.split("#")[0], s.split("#")[1]) : Stream.of(s.replace("#", ""));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test() {
		runFromData("Simple 4",new Data("AT", "G", "CC", "TAG"));
		runFromData("Simple 6", new Data("CCGATG", "TGG", "TGAA", "GGCT", "AC", "ACCACTT"));
		runFromData("simple 7", new Data("AGTATTTGTC", "GTATGCA", "ATGG", "TCAT", "AAAC", "AGC", "ATACGTTACC"));
		runFromData("Simple 8", new Data("GGC", "A", "AAACT", "A", "AGA", "CCGTTTTGAT", "TC", "T"));
		runFromData("Binaire 8", new Data("T", "T", "T", "A", "A", "A", "AA", "TT"));
		runFromData("Long", new Data("AAGGGTAG", "AGACAAA", "AA", "TTATCGA", "TT", "CCCATCTCTGTTTTT", "AATAG", "CT")); 		
	}

	public void runFromData(String title, Data data) {
		runTest(reader(data::input), title, data::check);
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
		
		private void verify(String[] inputs, String sol) {
			Map<String, Long> counts = count(Arrays.stream(inputs));
			
			Pattern pat = Pattern.compile("#"); 
			Pattern patSpace = Pattern.compile(" ");		
			Map<String, Long> solCounts = count(pat.splitAsStream(sol).flatMap(s -> patSpace.splitAsStream(s)));
			
			Assert.assertEquals("Different size", counts.size(), solCounts.size());
			
			Optional<String> findAny = counts.keySet().stream()
					.filter(k -> 
					!counts.get(k).equals(solCounts.computeIfAbsent(k, s -> 0L))
							)
					.findAny();
			
			findAny.ifPresent(s -> Assert.fail("'" + s + "' count"));
			
			String[] split = pat.split(sol);
			
			String left = split[0].replaceAll(" ", "");
			String right = reverse(split[1].replaceAll(" ", ""));
			
			
			Assert.assertEquals(left, right);
			
		}

		private Map<String, Long> count(Stream<String> stream) {
			return stream.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		}
	}
	

	static String reverse(String e) {
		return e.replaceAll("A", "U").replaceAll("T", "A").replaceAll("U", "T")
				.replaceAll("G", "U").replaceAll("C", "G").replaceAll("U", "C");
	}


	
	
}
