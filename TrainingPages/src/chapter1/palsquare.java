package chapter1;
/*
 ID: calviny1
 LANG: JAVA
 TASK: palsquare
 */

import java.io.*;
import java.util.*;

public class palsquare {

	private static boolean isPalindrome(String s) {
		int len = s.length();
		for (int a = 0; a < len; a++) {
			if (s.charAt(a) != s.charAt(len - a - 1)) return false;
		}
		return true;
	}
	
	private static String toBase(int i, int b) {
		String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
				"B", "C", "D", "E", "F", "G", "H", "I", "J"};
		String s = "";
		int maxPower = 0;
		while (Math.pow(b, maxPower) <= i) maxPower++;
		maxPower--;
		for (int a = maxPower; a >= 0; a--) {
			int remainder = i % (int)Math.pow(b, a);
			int coefficient = (i - remainder) / (int)Math.pow(b, a);
			s = s + digits[coefficient];
			i = remainder;
		}
		return s;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("palsquare.in"));
		PrintWriter out = new PrintWriter(new File("palsquare.out"));
		int B = in.nextInt();
		in.close();
		for (int i = 1; i <= 300; i++) {
			int square = i * i;
			String iString = toBase(i, B), squareString = toBase(square, B);
			if (isPalindrome(squareString)) {
				out.println(iString + " " + squareString);
			}
		}
		out.close();
		System.exit(0);
	}

}
