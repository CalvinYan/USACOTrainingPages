package chapter2;
/*
 ID: calviny1
 LANG: JAVA
 TASK: maze1
 */

import java.io.*;
import java.util.*;

public class maze1 {

	private static int W = 0, H = 0;
	
	private static ArrayList<Integer>[] adj;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("maze1.in"));
		PrintWriter out = new PrintWriter(new File("maze1.out"));
		W = in.nextInt();
		H = in.nextInt();
		in.nextLine();
		adj = new ArrayList[W * H];
		int exit1 = -1, exit2 = -1;
		for (int a = 0; a < W * H; a++) {
			adj[a] = new ArrayList<Integer>();
		}
		for (int i = 0; i < 2 * H + 1; i++) {
			String line = in.nextLine();
			for (int j = 0; j < W + i % 2; j++) {
				int index = 2 * j + 1 - i % 2;
				if (line.charAt(index) != '-' && line.charAt(index) != '|') {
					if (i == 0) {
						if (exit1 == -1) {
							exit1 = j;
						} else {
							exit2 = j;
						}
					} else if (i == 2 * H) {
						if (exit1 == -1) {
							exit1 = (H - 1) * W + j;
						} else {
							exit2 = (H - 1) * W + j;
						}
					} else if (index == 0) {
						if (exit1 == -1) {
							exit1 = (i - 1) / 2 * W;
						} else {
							exit2 = (i - 1) / 2 * W;
						}
					} else if (index == 2 * W) {
						if (exit1 == -1) {
							exit1 = (i + 2) / 2 * W - 1;
						} else {
							exit2 = (i + 2) / 2 * W - 1;
						}
					} else {
						if (i % 2 == 0) {
							int nodeA = (i / 2 - 1) * W + j, nodeB = (i / 2) * W + j;
							adj[nodeA].add(nodeB);
							adj[nodeB].add(nodeA);
						} else {
							int nodeA = (i - 1) / 2 * W + j - 1, nodeB = (i - 1) / 2 * W + j;
							adj[nodeA].add(nodeB);
							adj[nodeB].add(nodeA);
						}
					}
				}
			}
		}
		in.close();
		int ans = 0;
		int[] paths1 = shortestPath(exit1), paths2 = shortestPath(exit2);
		for (int i = 0; i < W * H; i++) {
			int path1 = paths1[i] + 1, path2 = paths2[i] + 1;
			int path = Math.min(path1, path2);
			if (ans < path) ans = path;
		}
		out.println(ans);
		out.close();
		System.exit(0);
	}
	
	private static int[] shortestPath(int from) {
		int[] distTo = new int[W * H];
		Arrays.fill(distTo, -1);
		distTo[from] = 0;
		boolean[] visited = new boolean[W * H];
		djikstras(distTo, visited, from);
		return distTo;
	}
	
	private static void djikstras(int[] distTo, boolean[] visited, int current) {
		visited[current] = true;
		for (int a : adj[current]) {
			if (distTo[current] + 1 < distTo[a] || distTo[a] == -1) {
				distTo[a] = distTo[current] + 1; 
			}
		}
		int next = nextVertex(distTo, visited);
		if (next != -1) djikstras(distTo, visited, next);
	}
	
	private static int nextVertex(int[] distTo, boolean[] visited) {
		int min = -1, index = -1;
		for (int i = 0; i < W * H; i++) {
			if (visited[i] || distTo[i] == -1) continue;
			if (min > distTo[i] || min == -1) {
				min = distTo[i];
				index = i;
			}
		}
		return index;
	}
	
}
