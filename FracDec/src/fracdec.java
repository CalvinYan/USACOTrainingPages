/*
 ID: calviny1
 LANG: JAVA
 TASK: fracdec
 */

import java.io.*;
import java.util.*;

public class fracdec {
	
	private static int repeat(int D) {
		int mod = 0;
		for (int i = 1; i < D; i++) {
			mod = (mod * 10 + 9) % D;
			if (mod == 0) {
				return i;
			}
		}
		return -1;
	}
	
	private static int gcd(int N, int D) {
		for (int i = N; i > 1; i--) {
			if (N % i == 0 && D % i == 0) return i; 
		}
		return 1;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("fracdec.in"));
		PrintWriter out = new PrintWriter(new File("fracdec.out"));
		int N = in.nextInt(), D = in.nextInt();
		in.close();
		StringBuilder ans = new StringBuilder();
		ans.append(Integer.toString((int)(N / D)) + ".");
		int oldN = N, oldD = D;
		N %= D;
		while (N != 0) {
			int digit = (int)(10 * N / D);
			int gcd = gcd(N, D);
			N /= gcd;
			D /= gcd;
			int repeat;
			if ((repeat = repeat(D)) != -1) {
				ans.append("(");
				for (int i = 0; i < repeat; i++) {
					int nextDigit = (int)(10 * N / D);
					ans.append(Integer.toString(nextDigit));
					N = N * 10 - D * nextDigit;
				}
				ans.append(")");
				break;
			} else ans.append(digit);
			N = N * 10 - D * digit;
		}
		if (oldN % oldD == 0) ans.append("0");
		if (ans.length() <= 76) {
			out.println(ans);
		} else {
			for (int j = 0; j <= ans.length() / 76; j++) {
				int endIndex = (j + 1) * 76;
				if (endIndex < ans.length()) {
					out.println(ans.substring(j * 76, endIndex));
				} else {
					out.println(ans.substring(j * 76));
				}
			}
		}
		out.close();
		System.exit(0);
	}

}
