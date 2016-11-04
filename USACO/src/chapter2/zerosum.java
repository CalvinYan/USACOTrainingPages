package chapter2;
/*
 ID: calviny1
 LANG: JAVA
 TASK: zerosum
 */

import java.io.*;
import java.util.*;

public class zerosum {
	
	private static int N = 0;
	
	private static TreeSet<String> solutions = new TreeSet<String>();
	
	private static void DFS(int[] state, int depth) {
		if (depth == N - 1) {
			int i = 0, sum = 0;
			String s = Integer.toString(1);
			while (i < N - 1 && state[i] == 3) {
				s = s + Integer.toString(i + 2);
				i++;
			}
			sum += Integer.parseInt(s);
			while (i < N - 1) {
				int operation = state[i];
				i++;
				s = Integer.toString(i + 1);
				while (i < N - 1 && state[i] == 3) {
					s = s + Integer.toString(i + 2);
					i++;
				}
				if (operation == 1) {
					sum += Integer.parseInt(s);
				} else {
					sum -= Integer.parseInt(s);
				}
			}
			if (sum == 0) solutions.add(toString(state));
			return;
		}
		for (int i = 1; i <= 3; i++) {
			state[depth] = i;
			DFS(state, depth + 1);
		}
	}
	
	private static String toString(int[] state) {
		String[] chars = {"", "+", "-", " "};
		String string = "1";
		for (int i = 0; i < N - 1; i++) {
			string += chars[state[i]];
			string += Integer.toString(i + 2);
		}
		return string;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("zerosum.in"));
		PrintWriter out = new PrintWriter(new File("zerosum.out"));
		N = in.nextInt();
		in.close();
		int[] state = new int[N - 1];
		DFS(state, 0);
		for (String s : solutions) {
			out.println(s);
		}
		out.close();
		System.exit(0);
	}

}
