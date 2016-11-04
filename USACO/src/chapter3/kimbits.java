//package chapter3;

/*
 ID: calviny1
 LANG: JAVA
 TASK: kimbits
 */

import java.io.*;
import java.util.*;

public class kimbits {

	private static long N, L, I;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("kimbits.in"));
		PrintWriter out = new PrintWriter(new File("kimbits.out"));
		N = in.nextLong();
		L = in.nextLong();
		I = in.nextLong();
		in.close();
		if (N == L && I == Math.pow(2, N)) {
			out.println(toBinaryString((int)(Math.pow(2, N) - 1)));
			out.close();
			System.exit(0);
		}
		int i = 0, count = 0;
		while (count < I) {
			int numBits = Integer.bitCount(i);
			if (numBits <= L) count++;
			i++;
		}
		out.println(toBinaryString(i - 1));
		out.close();
		System.exit(0);
	}
	
	private static String toBinaryString(int num) {
		StringBuilder sb = new StringBuilder();
		for (long i = N - 1; i >= 0; i--) {
			sb.append(((num & 1 << i) == 1 << i) ? "1" : "0");
		}
		return sb.toString();
	}

}
