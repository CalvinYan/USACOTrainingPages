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
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(new File("prefix.out"));
		ArrayList<String> primitives = new ArrayList<String>();
		String s = "";
		while (!(s = in.next()).equals(".")) {
			primitives.add(s);
		}
		String sequence = "";
		in.nextLine();
		while (in.hasNextLine()) {
			sequence += in.nextLine();
		}
		in.close();
		DFS(sequence, "", primitives);
		System.out.println(longest);
		out.close();
		System.exit(0);
	}

}
