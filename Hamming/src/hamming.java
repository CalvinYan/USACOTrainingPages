/*
 ID: calviny1
 LANG: JAVA
 TASK: hamming
 */

import java.io.*;
import java.util.*;

public class hamming {
	
	private static int N, B, D;

	private static int distance(int num1, int num2) {
		int count = 0;
		for (int i = 0; i < B; i++) {
			int pow = (int)Math.pow(2, i);
			if ((num1 & pow) != (num2 & pow)) count++;
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("hamming.in"));
		PrintWriter out = new PrintWriter(new File("hamming.out"));
		N = in.nextInt();
		B = in.nextInt();
		D = in.nextInt();
		in.close();
		TreeSet<Integer> nums = new TreeSet<Integer>();
		nums.add(0);
		int previous = 0, max = (int)Math.pow(2, B);
		for (int i = 1; i < N; i++) {
			for (int j = previous; j < max; j++) {
				boolean flag = true;
				for (int n : nums) {
					if (distance(j, n) < D) {
						flag = false;
						break;
					}
				}
				if (flag) {
					previous = j;
					nums.add(j);
					break;
				}
			}
		}
		Integer[] arr = nums.toArray(new Integer[0]);
		out.print(0);
		for (int a = 1; a < N; a++) {
			out.print(" " + arr[a]);
			if (a % 10 == 9) {
				if (a + 1 < arr.length) {
					out.println();
					out.print(arr[++a]);
				}
				
			}
		}
		out.println();
		out.close();
		System.exit(0);
	}

}
