package chapter2;
/*
 ID: calviny1
 LANG: JAVA
 TASK: prefix
 */

import java.io.*;
import java.util.*;

public class prefix {

	private static int longest = 0;
	
	private static HashMap<String, Boolean> canMake = new HashMap<String, Boolean>();
	
	private static void DFS(String sequence, String current, ArrayList<String> primitives) {
		int len = current.length();
		if (len > longest) longest = len;
		String next = sequence.substring(len);
		for (int i = 0; i < next.length(); i++) {
			String prefix = next.substring(0, i + 1);
			if (!canMake.containsKey(prefix)) {
				canMake.put(prefix, canMake(prefix, primitives));
			}
			if (canMake.get(prefix)) {
				DFS(sequence, current + prefix, primitives);
			}
		}
	}
	
	private static boolean canMake(String prefix, ArrayList<String> primitives) {
		if (canMake.containsKey(prefix)) return canMake.get(prefix);
		for (String p : primitives) {
			if (prefix.equals(p)) return true;
		}
		for (int i = 1; i < prefix.length(); i++) {
			if (canMake(prefix.substring(0, i), primitives) && canMake(prefix.substring(i), primitives)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean contains(String string, int index, String prefix) {
		int len = prefix.length();
		for (int i = index; i < index + len; i++) {
			if (string.charAt(i) != prefix.charAt(i - index)) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("prefix.in"));
		PrintWriter out = new PrintWriter(new File("prefix.out"));
		TreeSet<String> primitives = new TreeSet<String>();
		String s = "";
		while (!(s = in.next()).equals(".")) {
			primitives.add(s);
		}
		StringBuilder sequence = new StringBuilder();
		in.nextLine();
		while (in.hasNextLine()) {
			sequence.append(in.nextLine());
		}
		in.close();
		int len = sequence.length();
		String string = sequence.toString();
		boolean[] dp = new boolean[len + 1];
		String[] prim = primitives.toArray(new String[0]);
		Arrays.fill(dp, false);
		dp[0] = true;
		for (int i = 0; i <= len; i++) {
			if (dp[i]) {
				for (String p : prim) {
					int pLen = p.length(), j = i + pLen;
					if (j <= len) {
						if (contains(string, i, p)) {
							dp[j] = true;
							if (j > longest) longest = j;
						}
					}
					
				}
			}
			
		}
		//DFS(sequence.toString(), "", primitives);
		out.println(longest);
		out.close();
		System.exit(0);
	}

}
