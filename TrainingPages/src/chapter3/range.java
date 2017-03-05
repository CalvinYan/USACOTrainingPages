package chapter3;

/*
ID: calviny1
LANG: JAVA
TASK: range
*/

import java.io.*;
import java.util.*;

public class range {
	
	private static int N;
	
	private static boolean[][] grid;

	public static void main(String[] args) throws IOException {
		BufferedReader in;
		PrintWriter out;
		boolean debug = false;
		if (debug) {
			in = new BufferedReader(new FileReader(new File("range.txt")));
			out = new PrintWriter(System.out);
		} else {
			in = new BufferedReader(new FileReader(new File("range.in")));
			out = new PrintWriter(new File("range.out"));
		}
		N = Integer.parseInt(in.readLine());
		grid = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String line = in.readLine();
			for (int j = 0; j < N; j++) {
				grid[i][j] = line.charAt(j) == '1';
			}
		}
		in.close();
		int ans = 0, size = 2;
		while ((ans = solve()) != 0) out.println(size++ + " " + ans);
		out.close();
		System.exit(0);
	}
	
	private static int solve() {
		int ans = 0;
		boolean[][] newGrid = new boolean[grid.length-1][grid.length-1];
		for (int i = 0; i < grid.length - 1; i++) {
			for (int j = 0; j < grid.length - 1; j++) {
				if (grid[i][j] && grid[i][j+1] && grid[i+1][j] && grid[i+1][j+1]) {
					ans++;
					newGrid[i][j] = true;
				}
			}
		}
		grid = newGrid;
		return ans;
	}

}
