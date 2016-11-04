package chapter2;
/*
 ID: calviny1
 LANG: JAVA
 TASK: subset
 */

import java.io.*;
import java.util.*;

public class subset {
	
	private static int N = 0, targetSum = 0;
	
	private static HashMap<String, Long> solutions = new HashMap<String, Long>();
	
	private static void search(int max, int sum) {
		int[] data = {max, sum};
		if (max == 0) {
			solutions.put(Arrays.toString(data), (long) 0);
			return;
		}
		if (sum == 0 || sum == 1) {
			solutions.put(Arrays.toString(data), (long) 1);
			return;
		}
		int[] arr1 = {max - 1, sum}, arr2 = {max - 1, sum - max};
		
		if (!solutions.containsKey(Arrays.toString(arr1))) {
			search(max - 1, sum);
		}
		if (!solutions.containsKey(Arrays.toString(arr2))) {
			search(max - 1, sum - max);
		}
		long part1 = solutions.get(Arrays.toString(arr1)), part2 = solutions.get(Arrays.toString(arr2));
		solutions.put(Arrays.toString(data), part1 + part2);
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("subset.in"));
		PrintWriter out = new PrintWriter(new File("subset.out"));
		N = in.nextInt();
		in.close();
		if ((N * (N + 1) / 2) % 2 != 0) {
			out.println(0);
			out.close();
			System.exit(0);
		}
		targetSum = N * (N + 1) / 4;
		search(N, targetSum);
		int[] toFind = {N, targetSum};
		out.println(solutions.get(Arrays.toString(toFind)) / 2);
		out.close();
		System.exit(0);
	}

}
