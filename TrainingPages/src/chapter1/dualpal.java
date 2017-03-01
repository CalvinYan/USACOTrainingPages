package chapter1;
/*
 ID: calviny1
 LANG: JAVA
 TASK: dualpal
 */

import java.io.*;
import java.util.*;

public class dualpal {

	private static boolean isPalindrome(String s) {
		int len = s.length();
		for (int a = 0; a < len; a++) {
			if (s.charAt(a) != s.charAt(len - a - 1)) return false;
		}
		return true;
	}
	
	private static String toBase(int i, int b) {
		String s = "";
		int maxPower = 0;
		while (Math.pow(b, maxPower) <= i) maxPower++;
		maxPower--;
		for (int a = maxPower; a >= 0; a--) {
			int remainder = i % (int)Math.pow(b, a);
			int coefficient = (i - remainder) / (int)Math.pow(b, a);
			s = s + coefficient;
			i = remainder;
		}
		return s;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("dualpal.in"));
		PrintWriter out = new PrintWriter(new File("dualpal.out"));
		int N = in.nextInt(), S = in.nextInt();
		in.close();
		int nCount = 0, i = S + 1;
		while (nCount < N) {
			int numBases = 0;
			for (int a = 2; a <= 10 && numBases < 2; a++) {
				if (isPalindrome(toBase(i, a))) numBases++;
			}
			if (numBases == 2) {
				out.println(i);
				nCount++;
			}
			i++;
		}
		out.close();
		System.exit(0);
	}

}
