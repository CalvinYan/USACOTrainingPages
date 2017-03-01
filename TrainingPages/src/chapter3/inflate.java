package chapter3;
/*
 ID: calviny1
 LANG: JAVA
 TASK: inflate
 */

import java.io.*;
import java.util.*;

public class inflate {

	private static int N = 0;
	
	private static int[][] categories;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("inflate.in"));
		PrintWriter out = new PrintWriter(new File("inflate.out"));
		int T = in.nextInt();
		N = in.nextInt();
		categories = new int[N][2];
		int minTime = -1;
		for (int i = 0; i < N; i++) {
			categories[i][0] = in.nextInt();
			categories[i][1] = in.nextInt();
			if (minTime == -1 || categories[i][1] < minTime) minTime = categories[i][1];
		}
		in.close();
		int[] dp = new int[T + 1];
		dp[0] = 0;
		for (int i = 1; i <= T; i++) {
			int best = 0;
			for (int j = 0; j < N; j++) {
				if (categories[j][1] <= i) {
					best = Math.max(best, dp[i - categories[j][1]] + categories[j][0]);
				}
			}
			dp[i] = best;
		}
		/*Arrays.sort(categories, new  Comparator<int[]>() {
			public int compare(int[] one, int[] two) {
				double[] ratios = {(double)one[0]/(double)one[1], (double)two[0]/(double)two[1]};
				if (ratios[0] < ratios[1]) {
					return 1;
				} else if (ratios[0] == ratios[1]) {
					return 0;
				} else {
					return -1;
				}
			}
		});
		for (int i = 0; i < N; i++) {
			int[] category = categories[i];
			int times = (int)(T / category[1]);
			if (T % category[1] < minTime) times--;
			ans += category[0] * times;
			T %= category[1];
		}*/
		out.println(dp[T]);
		out.close();
		System.exit(0);
	}

}
