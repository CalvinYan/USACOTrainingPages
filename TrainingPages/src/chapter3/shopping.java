package chapter3;

/*
 ID: calviny1
 LANG: JAVA
 TASK: shopping
 */

import java.io.*;
import java.util.*;

public class shopping {
	
	private static int S;
	
	private static int[] idToItem = new int[1000];
	
	private static int target = 0;
	
	private static Deal[] deals;
	
	private static int counter = 1;
	
	private static class Deal {
		int amounts = 0;
		int cost = 0;
		
		public Deal(int a, int cost) {
			amounts = a;
			this.cost = cost;
		}
		
		public String toString() {
			return amounts + " " + cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in;
		PrintWriter out;
		boolean debug = false;
		if (debug) {
			in = new BufferedReader(new FileReader(new File("shopping.txt")));
			out = new PrintWriter(System.out);
		} else {
			in = new BufferedReader(new FileReader(new File("shopping.in")));
			out = new PrintWriter(new File("shopping.out"));
		}
		S = Integer.parseInt(in.readLine());
		deals = new Deal[S + 5];
		for (int i = 0; i < S; i++) {
			StringTokenizer str = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(str.nextToken());
			int amount = 0;
			for (int j = 0; j < n; j++) {
				int c = Integer.parseInt(str.nextToken()), k = Integer.parseInt(str.nextToken());
				amount += k * Math.pow(6, idToItem(c));
			}
			deals[i] = new Deal(amount, Integer.parseInt(str.nextToken()));
		}
		int b = Integer.parseInt(in.readLine());
		for (int i = 0; i < b; i++) {
			StringTokenizer str = new StringTokenizer(in.readLine());
			int c = idToItem(Integer.parseInt(str.nextToken())), k = Integer.parseInt(str.nextToken()), p = Integer.parseInt(str.nextToken());
			target += k * (int)Math.pow(6, c);
			deals[S + i] = new Deal((int)Math.pow(6, c), p);
		}
		in.close();
		out.println(solve());
		out.close();
		System.exit(0);
	}
	
	private static int idToItem(int id) {
		if (idToItem[id] == 0) idToItem[id] = counter++;
		return idToItem[id]-1;
	}
	
	private static int solve() {
		int[] dp = new int[(int)Math.pow(6, 5)];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i < target; i++) {
			for (Deal deal : deals) {
				if (deal == null) continue;
				boolean flag = true;
				int newAmounts = 0;
				for (int j = 0; j < 5; j++) {
					int pow = (int) Math.pow(6, j);
					int digit = (int)(i / pow) % 6 + (int)(deal.amounts / pow) % 6;
					if (digit > (int)(target / pow) % 6) {
						flag = false;
					}
					newAmounts += digit * pow;
				}
				if (flag) {
					dp[newAmounts] = Math.min(dp[newAmounts], dp[i] + deal.cost);
				}
			}
		}
		return dp[target];
	}
}