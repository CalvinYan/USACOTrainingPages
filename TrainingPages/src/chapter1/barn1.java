package chapter1;
/*
 ID: calviny1
 LANG: JAVA
 TASK: barn1
 */

import java.io.*;
import java.util.*;

public class barn1 {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("barn1.in"));
		PrintWriter out = new PrintWriter(new File("barn1.out"));
		int M = in.nextInt(), S = in.nextInt(), C = in.nextInt();
		int[] gaps = new int[C - 1];
		int[] stalls = new int[C];
		int previous = 0, ans = 0, min = 0, max = 0;
		for (int i = 0; i < C; i++) {
			stalls[i] = in.nextInt();
			
		}
		Arrays.sort(stalls);
		for (int j = 0; j < C; j++) {
			int stall = stalls[j];
			if (j > 0) {
				gaps[j - 1] = stall - previous - 1;
			} else min = stall;
			if (j == C - 1) max = stall;
			previous = stall;
		}
		in.close();
		Arrays.sort(gaps);
		ans = max - min + 1;
		for (int i = C - 2; i > C - 1 - M && i >= 0; i--) {
			ans -= gaps[i];
		}
		out.println(ans);
		out.close();
		System.exit(0);
	}

}
