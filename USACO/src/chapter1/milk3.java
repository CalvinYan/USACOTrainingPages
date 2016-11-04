package chapter1;
/* 
 ID: calviny1
 LANG: JAVA
 TASK: milk3
 */

import java.io.*;
import java.util.*;

public class milk3 {
	
	private static Hashtable<String, Boolean> exists = new Hashtable<String, Boolean>();
	
	private static int[] maxPails = new int[3];

	private static void solve(int[] pails, TreeSet<Integer> amounts) {
		String index = Integer.toString(pails[0]) + " " + Integer.toString(pails[1]) + " " + Integer.toString(pails[2]);
		if (exists.get(index) != null) return;
		exists.put(index, true);
		if (pails[0] == 0) amounts.add(pails[2]);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j) continue;
				if (pails[j] == maxPails[j]) continue;
				int[] nextPails = Arrays.copyOf(pails, 3);
				nextPails[j] = (pails[i] > maxPails[j] - pails[j]) ? maxPails[j] : nextPails[j] + pails[i];
				nextPails[i] = (pails[i] > maxPails[j] - pails[j]) ? nextPails[i] - (maxPails[j] - pails[j]) : 0;
				solve(nextPails, amounts);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("milk3.in"));
		PrintWriter out = new PrintWriter(new File("milk3.out"));
		for (int i = 0; i < 3; i++) maxPails[i] = in.nextInt();
		in.close();
		TreeSet<Integer> amounts = new TreeSet<Integer>();
		int[] pails = {0, 0, maxPails[2]};
		solve(pails, amounts);
		Integer[] arr = amounts.toArray(new Integer[0]);
		for (int j = 0; j < arr.length; j++) {
			out.print(arr[j]);
			if (j < arr.length - 1) out.print(" ");
		}
		out.println();
		out.close();
		System.exit(0);
	}

}
