/*
 ID: calviny1
 LANG: JAVA
 TASK: combo
 */

import java.io.*;
import java.util.*;

public class combo {
	
	private static boolean isValid(int[] num, int comboNum, int[][] combos, int N) {
		int[] combo = combos[comboNum];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int dist = Math.abs(num[i] - combo[i]);
				if (dist > 2 && dist < N - 2)  {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("combo.in"));
		PrintWriter out = new PrintWriter(new File("combo.out"));
		int N = in.nextInt();
		int[][] combos = new int[2][3];
		for (int a = 0; a < 2; a++) {
			for (int b = 0; b < 3; b++) {
				combos[a][b] = in.nextInt();
			}
		}
		in.close();
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					int[] num = {i, j, k};
					if (isValid(num, 0, combos, N) || isValid(num, 1, combos, N)) {
						//out.println((i + " " + j + " " + k));
						ans++;
					}
				}
			}
		}
		out.println(ans);
		out.close();
		System.exit(0);
	}
}
