package chapter1;
/*
 ID: calviny1
 LANG: JAVA
 TASK: crypt1
 */

import java.util.*;
import java.io.*;

public class crypt1 {

	private static boolean isValid(int num, int[] digits) {
		String s = Integer.toString(num);
		for (int i = 0; i < s.length(); i++) {
			String digit = s.substring(i, i + 1);
			boolean flag = false;
			for (int d : digits) {
				if (Integer.parseInt(digit) == d) flag = true;
			}
			if (!flag) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("crypt1.in"));
		PrintWriter out = new PrintWriter(new File("crypt1.out"));
		int N = in.nextInt();
		int[] digits = new int[N];
		for (int i = 0; i < N; i++) {
			digits[i] = in.nextInt();
		}
		Arrays.sort(digits);
		in.close();
		int ans = 0;
		for (int a = 0; a < N; a++) {
			int aDigit = digits[a];
			for (int b = 0; b < N; b++) {
				int bDigit = digits[b];
				for (int c = 0; c < N; c++) {
					int cDigit = digits[c];
					for (int d = 0; d < N; d++) {
						int dDigit = digits[d];
						int dPartial = (aDigit*100 + bDigit*10 + cDigit) * dDigit;
						if (dPartial >= 1000) break;
						if (!isValid(dPartial, digits)) continue;
						for (int e = 0; e < N; e++) {
							int eDigit = digits[e];
							int ePartial = (aDigit*100 + bDigit*10 + cDigit) * eDigit;
							if (ePartial >= 1000) break;
							if (!isValid(ePartial, digits)) continue;
							if (isValid(dPartial * 10 + ePartial, digits)) {
								//out.println(dPartial + " " + ePartial + " " + (dPartial * 10 + ePartial));
								ans++;
							}
						}
					}
				}
			}
		}
		out.println(ans);
		out.close();
		System.exit(0);
	}

}
