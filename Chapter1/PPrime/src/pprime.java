/*
 ID: calviny1
 LANG: JAVA
 TASK: pprime
 */

import java.io.*;
import java.util.*;

public class pprime {

	private static int A = 0, B = 0;
	
	private static ArrayList<Integer> pprimes = new ArrayList<Integer>();
	
	private static void check(int numDigits, int index, int num) {
		for (int i = 0; i < 10; i++) {
			if (index == 0 && i == 0) continue;
			int temp = num;
			temp += i * Math.pow(10, numDigits - index - 1);
			if (numDigits - index - 1 != index) {
				temp += i * Math.pow(10, index);
			}
			if (index == (numDigits - 1) / 2) {
				if (temp < A) continue;
				if (temp > B) return;
				if (prime(temp)) {
					pprimes.add(temp);
				}
			} else {
				check(numDigits, index + 1, temp);
			}
		}
	}
	
	private static boolean prime(int num) {
		if (num < 2) return false;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) return false;
		} return true;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("pprime.in"));
		PrintWriter out = new PrintWriter(new File("pprime.out"));
		A = in.nextInt();
		B = in.nextInt();
		in.close();
		int min = Integer.toString(A).length(), max = Integer.toString(B).length();
		for (int i = min; i <= max; i++) {
			check(i, 0, 0);
		}
		for (int p : pprimes) {
			out.println(p);
		}
		out.close();
		System.exit(0);
	}

}
