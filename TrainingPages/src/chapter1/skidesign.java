package chapter1;
/*
 ID: calviny1
 LANG: JAVA
 TASK: skidesign
 */

import java.io.*;
import java.util.*;

public class skidesign {

	private static int costOf(int[] hills, int leftBound, int rightBound) {
		int cost = 0;
		for (int a = 0; a < hills.length; a++) {
			if (hills[a] < leftBound) {
				cost += Math.pow(leftBound - hills[a], 2);
			} else break;
		}
		for (int b = hills.length - 1; b >= 0; b--) {
			if (hills[b] > rightBound) {
				cost += Math.pow(hills[b] - rightBound, 2);
			} else break;
		}
		return cost;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("skidesign.in"));
		PrintWriter out = new PrintWriter(new File("skidesign.out"));
		int N = in.nextInt();
		int[] hills = new int[N];
		for (int i = 0; i < N; i++) {
			hills[i] = in.nextInt();
		}
		in.close();
		Arrays.sort(hills);
		int dif = hills[N - 1] - hills[0], toCut = dif - 17, ans = Integer.MAX_VALUE;
		if (toCut <= 0) {
			out.println(0);
			out.close();
			System.exit(0);
		}
		for (int j = 0; j <= toCut; j++) {
			int result = costOf(hills, hills[0] + j, hills[N - 1] - toCut + j);
			if (result < ans) ans = result;
		}
		out.println(ans);
		out.close();
		System.exit(0);
	}

}
