package chapter1;
/*
 ID: calviny1
 LANG: JAVA
 TASK: numtri
 */

import java.io.*;
import java.util.*;

public class numtri {

	private static int N = 0, ans = 0;
	
	private static void BFS(int[][] triangle) {
		int[][] sums = new int[N][N];
		sums[0][0] = triangle[0][0];
		for (int row = 1; row < N; row++) {
			int leftParent = 0, rightParent = 0;
			for (int col = 0; col < row + 1; col++) {
				rightParent = (col < row) ? sums[row - 1][col] : 0;
				int value = triangle[row][col];
				int sum = Math.max(leftParent, rightParent) + value;
				sums[row][col] = sum;
				if (row == N - 1) {
					if (sum > ans) {
						ans = sum;
					}
				}
				leftParent = rightParent;
			}
		}
		return;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("numtri.in"));
		PrintWriter out = new PrintWriter(new File("numtri.out"));
		N = in.nextInt();
		int[][] triangle = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i + 1; j++) {
				triangle[i][j] = in.nextInt();
			}
		}
		in.close();
		BFS(triangle);
		out.println(ans);
		out.close();
		System.exit(0);
	}

}
