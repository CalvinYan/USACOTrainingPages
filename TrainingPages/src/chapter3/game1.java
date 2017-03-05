package chapter3;

/*
 ID: calviny1
 LANG: JAVA
 TASK: game1
 */

import java.io.*;
import java.util.*;

public class game1 {
	
	private static int N;
	
	private static int[] board, sum;

	public static void main(String[] args) throws IOException {
		Scanner in;
		PrintWriter out;
		boolean debug = false;
		if (debug) {
			in = new Scanner(System.in);
			out = new PrintWriter(System.out);
		} else {
			in = new Scanner(new File("game1.in"));
			out = new PrintWriter(new File("game1.out"));
		}
		N = in.nextInt();
		board = new int[N];
		sum = new int[N];
		int i = 0;
		while (in.hasNextInt()) {
			board[i] = in.nextInt();
			sum[i] = (i > 0) ? sum[i-1] + board[i] : board[i];
			i++;
		}
		in.close();
		solveOne(out);
		out.close();
		System.exit(0);
	}
	
	public static void solveOne(PrintWriter out) {
		int[][][] dpOne = new int[N][N][2], dpTwo = new int[N][N][2];
		for (int len = 1; len <= N; len++) {
			for (int start = 0; start + len <= N; start++) {
				int end = start + len - 1;
				if (len == 1) {
					dpOne[start][end][0] = board[start];
					dpTwo[start][end][1] = board[start];
				} else {
					dpOne[start][end][0] = Math.max(board[start] + rangeSum(start+1, end) - dpTwo[start+1][end][1], 
							board[end] + rangeSum(start, end-1) - dpTwo[start][end-1][1]);
					dpOne[start][end][1] = rangeSum(start, end) - dpTwo[start][end][1];
					dpTwo[start][end][1] = Math.max(board[start] + rangeSum(start+1, end) - dpOne[start+1][end][0], 
							board[end] + rangeSum(start, end-1) - dpOne[start][end-1][0]);
					dpTwo[start][end][0] = rangeSum(start, end) - dpOne[start][end][0];
				}
			}
		}
		out.println(dpOne[0][N-1][0] + " " + dpTwo[0][N-1][0]);
	}
	
	public static int rangeSum(int start, int end) {
		if (start == 0) return sum[end];
		return sum[end] - sum[start-1];
	}

}
