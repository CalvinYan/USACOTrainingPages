/*
 ID: calviny1
 LANG: JAVA
 TASK: money
 */

import java.io.*;
import java.util.*;

public class money {

	private static int V = 0, N = 0, ans = 0;
	
	private static int[] coins;
	
	private static void DFS(int sum, int level) {
		if (sum == N) ans++;
		for (int i = level; i < V; i++) {
			int value = coins[i];
			if (value <= N - sum) {
				DFS(sum + value, i);
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(new File("money.out"));
		V = in.nextInt();
		N = in.nextInt();
		coins = new int[V];
		for (int i = 0; i < V; i++) {
			coins[i] = in.nextInt();
		}
		in.close();
		long[][] dp = new long[N + 1][V + 1];
		for (int i = 0; i <= N; i++) {
			dp[i][0] = 0;
		}
		Arrays.fill(dp[0], 1);
		for (int sum = 1; sum <= N; sum++) {
			for (int index = 0; index < V; index++) {
				int value = coins[index];
				dp[sum][index + 1] += dp[sum][index];
				if (value > sum) continue;
				for (int count = 1; count * value <= sum; count++) {
					dp[sum][index + 1] += dp[sum - count * value][index];
				}	
			}
		}
		//DFS(0, 0);
		out.println(dp[N][V]);
		out.close();
		System.exit(0);
	}

}
