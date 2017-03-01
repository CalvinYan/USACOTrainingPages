package chapter3;

/*
 ID: calviny1
 LANG: JAVA
 TASK: fence
 */

import java.io.*;
import java.util.*;

public class fence {
	
	private static int N;
	
	private static int[][] adj = new int[501][501];
	
	private static int[] path = new int[1025], degree = new int[501];
	
	private static int count = 0;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("fence.in"));
		PrintWriter out = new PrintWriter(new File("fence.out"));
		N = in.nextInt();
		for (int i = 0; i < N; i++) {
			int from = in.nextInt(), to = in.nextInt();
			adj[from][to]++;
			adj[to][from]++;
			degree[from]++;
			degree[to]++;
		}
		in.close();
		int source = 0;
		for (source = 1; source <= 500 && degree[source] % 2 == 0; source++);
		if (source == 501) source = 1;
		getEulerian(source);
		for (int i = N; i >= 0; i--) out.println(path[i]);
		out.close();
		System.exit(0);
	}
	
	private static void getEulerian(int node) {
		if (degree[node] == 0) {
			path[count++] = node;
			return;
		}
		int next;
		for (next = 1; next <= 500; next++) {
			if (adj[node][next] > 0) {
				adj[node][next]--;
				adj[next][node]--;
				degree[node]--;
				degree[next]--;
				getEulerian(next);
			}
		}
		path[count++] = node;
	}
}
