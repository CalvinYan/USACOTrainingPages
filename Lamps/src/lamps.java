/*
 ID: calviny1
 LANG: JAVA
 TASK: lamps
 */

import java.io.*;
import java.util.*;

public class lamps {
	
	private static int N = 0, C = 0;
	
	private static ArrayList<Integer> on = new ArrayList<Integer>(),
			off = new ArrayList<Integer>();
	
	private static TreeSet<String> solutions = new TreeSet<String>
		(new Comparator<String>() {
			public int compare(String s1, String s2) {
				for (int i = 0; i < N; i++) {
					int digit1 = Character.digit(s1.charAt(i), 10),
						digit2 = Character.digit(s2.charAt(i), 10);
					if (digit1 > digit2) return 1;
					if (digit1 < digit2) return -1;
				}
				return 0;
			}
		});
	
	private static HashMap<String, Integer> exists = new HashMap<String, Integer>();
	
	private static void DFS(String state, int depth) {
		if (exists.containsKey(state)) return;
		exists.put(state, 0);
		if (valid(state)) solutions.add(state);
		if (depth == C) return;
		String newState = state;
		for (int i = 0; i < N; i ++) {
			int digit = Character.digit(state.charAt(i), 10);
			newState = newState.substring(0, i) + Integer.toString(1 - digit) + newState.substring(i + 1);
		}
		DFS(newState, depth + 1);
		newState = state;
		for (int i = 0; i < N; i += 2) {
			int digit = Character.digit(state.charAt(i), 10);
			newState = newState.substring(0, i) + Integer.toString(1 - digit) + newState.substring(i + 1);
		}
		DFS(newState, depth + 1);
		newState = state;
		for (int i = 1; i < N; i += 2) {
			int digit = Character.digit(state.charAt(i), 10);
			newState = newState.substring(0, i) + Integer.toString(1 - digit) + newState.substring(i + 1);
		}
		DFS(newState, depth + 1);
		newState = state;
		for (int i = 0; i < N; i += 3) {
			int digit = Character.digit(state.charAt(i), 10);
			newState = newState.substring(0, i) + Integer.toString(1 - digit) + newState.substring(i + 1);
		}
		DFS(newState, depth + 1);
		newState = new String(state);
	}
	
	private static boolean valid(String state) {
		for (int i : on) {
			if (state.charAt(i - 1) != '1') return false;
		}
		for (int i : off) {
			if (state.charAt(i - 1) != '0') return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("lamps.in"));
		PrintWriter out = new PrintWriter(new File("lamps.out"));
		N = in.nextInt();
		C = in.nextInt();
		int next = 0;
		while ((next = in.nextInt()) != -1) {
			on.add(next);
		}
		while ((next = in.nextInt()) != -1) {
			off.add(next);
		}
		in.close();
		String state = "";
		for (int i = 0; i < N; i++) {
			state += "1";
		}
		DFS(state, 0);
		for (String s : solutions) {
			out.println(s);
		}
		if (solutions.isEmpty()) {
			out.println("IMPOSSIBLE");
		}
		out.close();
		System.exit(0);
	}

}
