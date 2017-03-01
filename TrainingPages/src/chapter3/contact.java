package chapter3;

/*
 ID: calviny1
 LANG: JAVA
 TASK: contact
 */

// Code from: https://github.com/lklein/usaco-training/blob/master/usaco/contact/contact.java#L10

import java.io.*;
import java.util.*;

public class contact {

	class Frequency implements Comparable<Frequency> {
		String seq;
		int freq;

		public int compareTo(Frequency other) {
			if (freq != other.freq) return other.freq - freq;
			if (seq.length() != other.seq.length()) return seq.length() - other.seq.length();
			return Integer.parseInt(seq, 2) - Integer.parseInt(other.seq, 2);
		}

		public Frequency(String s, int f) {
			seq = s;
			freq = f;
		}

		public String toString() {
			return String.format("(%s, %d)", seq, freq);
		}
	}

	public String[] solve(int minLen, int maxLen, int n, char[] seq) {
		// HashMap is easier to code, standard and solves the problem, a Trie may also do here
		HashMap<String, Integer> frequencies = new HashMap<String, Integer>();
		String sequ = new String(seq);

		// Straightforward, using DP is actually slower
		for (int len = minLen; len <= maxLen; len++) {
			for (int start = 0; start + len <= sequ.length(); start++) {
				updateFrequencies(frequencies, sequ.substring(start, start + len));
			}
		}

		ArrayList<Frequency> outputFreq = new ArrayList<contact.Frequency>();
		for (String k : frequencies.keySet()) {
			outputFreq.add(new Frequency(k, frequencies.get(k)));
		}
		Collections.sort(outputFreq);

		ArrayList<String> res = new ArrayList<String>();
		int currentFreq = -1;
		int printedFreq = 0;
		int printedPatterns = 0;
		StringBuilder patternSb = new StringBuilder();

		for (int i = 0; i < outputFreq.size() && printedFreq <= n; i++) {
			Frequency f = outputFreq.get(i);
			if (f.freq != currentFreq) { // print freq
				printedFreq++;
				if (printedFreq > n) break;
				printedPatterns = 0;
				if (patternSb.length() > 0) {
					res.add(patternSb.toString().trim());
				}
				patternSb = new StringBuilder();

				res.add(String.valueOf(f.freq));
				currentFreq = f.freq;
			}
			// print frequencies
			patternSb.append(f.seq);
			patternSb.append(" ");
			printedPatterns++;
			if (printedPatterns == 6) {
				res.add(patternSb.toString().trim());
				patternSb = new StringBuilder();
				printedPatterns = 0;
			}
		}
		if (patternSb.length() > 0) {
			res.add(patternSb.toString().trim());
		}

		return res.toArray(new String[0]);
	}

	private void updateFrequencies(HashMap<String, Integer> frequencies, String thisPos) {
		if (!frequencies.containsKey(thisPos)) frequencies.put(thisPos, 0);
		frequencies.put(thisPos, frequencies.get(thisPos) + 1);
	}

	public static void main(String[] args) throws IOException {
		String problemName = "contact";
		BufferedReader f = new BufferedReader(new FileReader(problemName + ".in"));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		String line = f.readLine();
		while (line != null) {
			sb.append(line);
			line = f.readLine();
		}

		String[] res = (new contact()).solve(a, b, n, sb.toString().toCharArray());

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));
		for (String s : res) {
			out.println(s);
		}
		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}

}

/*import java.io.*;
import java.util.*;

public class contact {

	private static int A, B, N;
	
	private static String string = "";
	
	private static HashMap<String, Integer> sequences = new HashMap<String, Integer>();
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		long before, after;
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(new File("contact.out"));
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		before = System.currentTimeMillis();
		A = in.nextInt();
		B = in.nextInt();
		N = in.nextInt();
		String line = in.nextLine();
		while (line != null) {
			sb.append(in.nextLine());
			line = in.nextLine();
		}
		string = sb.toString();
		in.close();
		after = System.currentTimeMillis();
		//if (N == 50) out.println(after - before);
		StringBuilder[] subs = new StringBuilder[string.length()];
		before = System.currentTimeMillis();
		for (int i = A; i <= B; i++) {
			for (int j = 0; j < string.length(); j++) {
				int end = i + j;
				if (end > string.length()) break;
				if (subs[j] == null) subs[j] = new StringBuilder(string.substring(j, end));
				else subs[j].append(string.charAt(end - 1));
				update(subs[j].toString());
			}
		}
		after = System.currentTimeMillis();
		//if (N == 50) out.println(after - before);
		Integer[] values = sequences.values().toArray(new Integer[0]);
		String[] keys = sequences.keySet().toArray(new String[0]);
		int[][] ans = new int[keys.length][2];
		for (int i = 0; i < values.length; i++) {
			ans[i][0] = values[i];
			ans[i][1] = i;
		}
		Arrays.sort(ans, new Comparator<int[]>() {
			public int compare(int[] one, int[] two) {
				return two[0] - one[0];
			}
		});
		int lol = 0;
		for (int i = 0; lol < N && i < ans.length; i++) {
			TreeSet<String> set = new TreeSet<String>(new Comparator<String>() {
				public int compare(String one, String two) {
					if (one.length() == two.length()) {
						return (Integer.valueOf(one, 2) - Integer.valueOf(two, 2));
					}
					return one.length() - two.length();
				}
			});
			//out.println("Now looking at: " + i);
			int freq = ans[i][0], ind = ans[i][1], count = 1;
			out.print(freq);
			set.add(keys[ind]);
			while (i + 1 < ans.length && ans[i+1][0] == freq) {
				i++;
				freq = ans[i][0];
				ind = ans[i][1];
				set.add(keys[ind]);
			}
			for (String s : set) {
				if (count % 6 == 1) out.println();
				else out.print(" ");
				out.print(s);
				count++;
			}
			out.println();
			lol++;
		}
		out.close();
		System.exit(0);
	}
	
	private static void update(String str) {
		Integer value = sequences.get(str);
		if (value != null) {
			sequences.put(str, value + 1);
		} else sequences.put(str, 1);
	}

}
*/