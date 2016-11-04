package chapter3;
/*
 ID: calviny1
 LANG: JAVA
 TASK: humble
 */

import java.io.*;
import java.util.*;

public class humble {
	
	private static int K, N;
	
	private static int[] primes;
	
	private static TreeSet<Integer> primeSet = new TreeSet<Integer>();

	public static void main(String[] args) throws IOException{
		long before, after;
		Scanner in = new Scanner(new File("humble.in"));
		PrintWriter out = new PrintWriter(new File("humble.out"));
		K = in.nextInt();
		N = in.nextInt();
		primes = new int[K];
		for (int i = 0; i < K; i++) {
			primes[i] = in.nextInt();
			primeSet.add(primes[i]);
		}
		in.close();
		int count = 1;
		long current = (long)primes[0];
		long[] arr = new long[N + 1];
		int[] indices = new int[K];
		arr[0] = 1;
		TreeSet<Long> humbles = new TreeSet<Long>();
		humbles.add(1L);
		while (count <= N) {
			int index = 0;
			long min = Long.MAX_VALUE;
			for (int j = 0; j < K; j++) {
				long val = primes[j] * arr[indices[j]];
				if (val < min) {
					min = val;
					index = j;
				}
			}
			if (!humbles.contains(min)) {
				arr[count] = min;
				humbles.add(min);
				count++;
			}
			indices[index]++;
		}
		out.println(arr[N]);
/*		TreeSet<Long> humbles = new TreeSet<Long>();
		for (int p : primes) humbles.add((long)p);*/
		//before = System.currentTimeMillis();
		PriorityQueue<Long> queue = new PriorityQueue<Long>();
		for (int p : primes) queue.add((long)p);
		/*while (true) {
			count++;
			current = queue.remove();
			if (count == N) break;
			for (int prime : primes) {
				if (prime > current) break;
				long product = current * prime;
				//if (!humbles.contains(product))
				queue.add(product);
			}
		}*/
		//after = System.currentTimeMillis();
		//System.out.println(after - before);
		//out.println(current);
		out.close();
		System.exit(0);
	}
}
