package chapter1;
/*
 ID: calviny1
 LANG: JAVA
 TASK: milk
 */

import java.io.*;
import java.util.*;

public class milk {

	private static class Pair implements Comparable<Pair> {
		int price, quantity;
		
		private Pair(int p, int q) {
			price = p;
			quantity = q;
		}
		
		public int compareTo(Pair that) {
			return price - that.price;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("milk.in"));
		PrintWriter out = new PrintWriter(new File("milk.out"));
		int N = in.nextInt(), M = in.nextInt();
		Pair[] farms = new Pair[M];
		for (int i = 0; i < M; i++) {
			int p = in.nextInt(), q = in.nextInt();
			farms[i] = new Pair(p, q);
		}
		in.close();
		Arrays.sort(farms);
		int cost = 0, ptr = 0;
		while (N > 0 && ptr < M) {
			Pair farm = farms[ptr];
			if (farm.quantity < N) {
				N -= farm.quantity;
				cost += farm.quantity * farm.price;
				ptr++;
			} else {
				cost += N * farm.price;
				N = 0;
			}
		}
		out.println(cost);
		out.close();
		System.exit(0);
	}

}
