package chapter1;
/*
 ID: calviny1
 LANG: JAVA
 TASK: transform
 */

import java.io.*;
import java.util.*;

public class transform {
	
	private static int N;
	
	private static boolean matches(boolean[][] gridOne, boolean[][] gridTwo) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (gridOne[i][j] != gridTwo[i][j]) return false;
			}
		} return true;
	}
	
	private static boolean[][] t(boolean[][] gridOne, int[][] c) {
		boolean[][] newGrid = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newGrid[N * c[0][0] + i * c[0][1] + j * c[0][2] + -1 * c[0][3]]
						[N * c[1][0] + i * c[1][1] + j * c[1][2] + -1 * c[1][3]] = gridOne[i][j];
			}
		}
		return newGrid;
	}

	public static void main(String[] args) throws IOException {
		boolean[] isPossible = {false, false, false, false, false, false, true};
		Scanner in = new Scanner(new File("transform.in"));
		PrintWriter out = new PrintWriter(new File("transform.out"));
		N = Integer.parseInt(in.nextLine());
		boolean[][] gridOne = new boolean[N][N];
		for (int a = 0; a < N; a++) {
			String s = in.nextLine();
			for (int b = 0; b < N; b++) {
				gridOne[a][b] = (s.charAt(b) == '@');
			}
		}
		boolean[][] gridTwo = new boolean[N][N];
		for (int c = 0; c < N; c++) {
			String s = in.nextLine();
			for (int d = 0; d < N; d++) {
				gridTwo[c][d] = (s.charAt(d) == '@');
			}
		}
		in.close();
		int[][][] cArrays = {{{0, 0, 1, 0}, {1, -1, 0, 1}}, {{1, -1, 0, 1}, {1, 0, -1, 1}},
				{{1, 0, -1, 1}, {0, 1, 0, 0}}, {{0, 1, 0, 0}, {1, 0, -1, 1}}, {{1, 0, -1, 1}, {1, -1, 0, 1}},
				{{1, -1, 0, 1}, {0, 0, 1, 0}}, {{0, 0, 1, 0}, {0, 1, 0, 0}}, {{0, 1, 0, 0}, {0, 0, 1, 0}}};
		for (int j = 0; j < 8; j++) {
			boolean[][] testGrid = t(gridOne, cArrays[j]);
			if (matches(gridTwo, testGrid)) {
				if (j > 3 && j < 7) {
					isPossible[4] = true;
				} else if (j <= 3) {
					isPossible[j] = true;
				} else isPossible[5] = true;
			}
		}
		for (int k = 0; k < 7; k++) {
			if (isPossible[k]) {
				out.println(k+1);
				out.close();
				System.exit(0);
			}
		}
	}

}
