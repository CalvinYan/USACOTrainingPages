/*
 ID: calviny1
 LANG: JAVA
 TASK: sprime
 */

import java.io.*;
import java.util.*;

public class sprime {

	private static int N = 0;
	
	private static ArrayList<Integer> sprimes = new ArrayList<Integer>();
	
	private static void DFS(int num) {
		for (int i = 1; i < 10; i++) {
			int temp = num;
			temp = temp * 10 + i;
			if (prime(temp)) {
				if (Integer.toString(temp).length() == N) {
					sprimes.add(temp);
				} else {
					DFS(temp);
				}
			}
		}
	}
	
	private static boolean prime(int num) {
		if (num < 2) return false;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("sprime.in"));
		PrintWriter out = new PrintWriter(new File("sprime.out"));
		N = in.nextInt();
		in.close();
		DFS(0);
		for (int s : sprimes) {
			out.println(s);
		}
		out.close();
		System.exit(0);
	}

}
