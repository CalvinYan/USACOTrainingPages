/*
 ID: calviny1
 LANG: JAVA
 TASK: preface
 */

import java.io.*;
import java.util.*;

public class preface {

	private static int N, maxSize = 7;
	
	private static char[] letters = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("preface.in"));
		PrintWriter out = new PrintWriter(new File("preface.out"));
		N = in.nextInt();
		in.close();
		Hashtable<Integer, int[]> freqTable = new Hashtable<Integer, int[]>();
		int[] frequencies = new int[maxSize];
		for (int i = 1; i <= N; i++) {
			String s = Integer.toString(i);
			int len = s.length();
			for (int j = 0; j < len; j++) {
				int digit = Integer.parseInt(s.substring(len - j - 1, len - j)),
					number = digit * (int)Math.pow(10, j);
				int[] frequency;
				if (freqTable.contains(number)) {
					frequency = freqTable.get(number);
				} else {
					frequency = new int[maxSize];
					if (digit % 5 == 4) {
						frequency[2 * j] = 1;
						frequency[2 * j + (int)Math.ceil(((double)digit / 5))] = 1;
					} else if (digit == 5) {
						frequency[2 * j + 1] = 1;
					} else {
						frequency[2 * j] = digit % 5;
						if (digit > 5) {
							frequency[2 * j + 1] = 1;
						}
					}
					freqTable.put(number, frequency);
				}
				for (int k = 0; k < maxSize; k++) {
					frequencies[k] += frequency[k];
				}
			}
		}
		for (int a = 0; a < maxSize; a++) {
			if (frequencies[a] > 0) {
				out.println(letters[a] + " " + frequencies[a]);
			}
		}
		out.close();
		System.exit(0);
	}

}
