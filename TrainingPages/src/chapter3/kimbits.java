package chapter3;

/*
 ID: calviny1
 LANG: JAVA
 TASK: kimbits
 */

import java.io.*;
import java.util.*;

public class kimbits {

	private static int N, L;
	private static long I;

	private static String solve(long value, long offset, int index, int numOnes) {
		if (numOnes == L) return toBinaryString(value);
		
		System.out.println(toBinaryString(value) + " " + offset + " " + numOnes);
		
		long boundary = offset + numCombinations(N - index - 1, L - numOnes);
		
		if (I > boundary) {
			long newValue = value + (long)Math.pow(2, N - index - 1);
			if (index == N - 1) {
				return toBinaryString(newValue);
			} else {
				return solve(newValue, boundary, index + 1, numOnes + 1);
			}
		} else {
			if (index == N - 1) {
				return toBinaryString(value);
			} else {
				return solve(value, offset, index + 1, numOnes);
			}
		}
	}
	
	private static long numCombinations(int n, int k) {
		
		if (k < n / 2) {
			long sum = 0;
			for (int i = 0; i <= Math.min(n, k); i++) {
				sum += choose(n, i);
				//System.out.println(sum);
			}
			return sum;
		} else {
			long sum = 1 << n;
			for (int i = n; i > k; i--) {
				sum -= choose(n, i);
			}
			return sum;
		}
		
	}
	
	private static long choose(int n, int k) {
		k = Math.min(k, n-k);
		
		long numerator = 1, denominator = 1;
		for (int i = 0; i < k; i++) {
			numerator *= n - i;
			denominator *= i + 1;
		}
		
		return numerator / denominator;
	}
	
	private static String toBinaryString(long num) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			if (isOne(num, i)) sb.append("1");
			else sb.append("0");
		}
		return sb.toString();
	}
	
	private static boolean isOne(long num, int index) {
		long power = 1 << (N - index - 1);
		return (num & power) == power;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("kimbits.in"));
		PrintWriter out = new PrintWriter(new File("kimbits.out"));
		
		System.out.println(numCombinations(30,30));
		
		N = in.nextInt();
		L = in.nextInt();
		I = in.nextLong();
		in.close();
		
		out.println(solve(0, 0, 0, 0));
		out.close();
		System.exit(0);
	}
}
