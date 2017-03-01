package chapter3;

/*
 ID: calviny1
 LANG: JAVA
 TASK: stamps
 */

import java.io.*;
import java.util.*;

public class stamps {
	
	private static int K, N;
	
	private static int[] stamps;
	
	private static int[] stampsUsed;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("stamps.in"));
		PrintWriter out = new PrintWriter(new File("stamps.out"));
		
		K = in.nextInt();
		N = in.nextInt();
		stamps = new int[N];
		for (int i = 0; i < N; i++) {
			int val = in.nextInt();
			stamps[i] = val;
		}
		Arrays.sort(stamps);
		in.close();
		
		int max = stamps[stamps.length - 1] * K;
		stampsUsed = new int[max + 1];
		for (int stamp : stamps) stampsUsed[stamp] = 1;
		
		if (stamps[0] != 1) {
			out.println(0);
			out.close();
			System.exit(0);
		} else {
			stampsUsed[1] = 1;
			if (stampsUsed[2] == 0) stampsUsed[2] = 2;
		}
		
		int i = 2, count;
		while (i <= max && (count = stampsUsed[i]) != 0 && count <= K) {
			for (int j : stamps) {
				if (i + j > max) break;
				int value = stampsUsed[i + j];
				if (value > 0 && count >= value) continue;
				int newVal = stampsUsed[i] + stampsUsed[j];
				stampsUsed[i + j] = (value == 0) ? newVal : Math.min(value, newVal);
			}
			i++;
		}
		
		out.println(i - 1);
		out.close();
		System.exit(0);
	}

}
