/*
 ID: calviny1
 LANG: JAVA
 TASK: runround
 */

import java.io.*;
import java.util.*;

public class runround {

	private static int M;
	
	private static boolean runaround(long num) {
		
		if (!valid(num)) return false;
		String s = Long.toString(num);
		boolean[] covered = new boolean[s.length()];
		Arrays.fill(covered, false);
		int len = s.length(), index = 0;
		for (int i = 0; i < len; i++) {
			if (covered[index]) return false;
			covered[index] = true;
			index = wrapAround(index + Character.digit(s.charAt(index), 10), len - 1);
		}
		return index == 0;
	}
	
	private static int wrapAround(int num, int max) {
		while (num < 0) num += max;
		while (num > max) num -= max + 1;
		return num;
	}
	
	private static boolean valid(long num) {
		TreeSet<Integer> digits = new TreeSet<Integer>();
		String s = Long.toString(num);
		for (int i = 0; i < s.length(); i++) {
			int digit = Character.digit(s.charAt(i), 10);
			if (digit == 0) return false;
			if (digits.contains(digit)) return false;
			digits.add(digit);
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(new File("runround.out"));
		M = in.nextInt();
		in.close();
		long num = M + 1;
		while(!runaround(num)) num++; 
		out.println(num);
		out.close();
		System.exit(0);
	}

}
