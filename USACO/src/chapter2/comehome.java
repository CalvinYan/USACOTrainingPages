package chapter2;
/*
 ID: calviny1
 LANG: JAVA
 TASK: comehome
 */

import java.io.*;
import java.util.*;

public class comehome {
	
	@SuppressWarnings("unchecked")
	private static ArrayList<int[]>[] adj = new ArrayList[58];
	
	private static long[] distTo = new long[58];
	
	private static boolean[] hasCow = new boolean[25];
	
	private static ArrayList<Integer> toVisit = new ArrayList<Integer>();

	private static void shortestPath() {
		Arrays.fill(distTo, Integer.MAX_VALUE);
		distTo[25] = 0;
		boolean[] visited = new boolean[58];
		int current = 25;
		while (current != -1) {
			visited[current] = true;
			for (int[] a : adj[current]) {
				int field = a[0];
				long dist = distTo[current] + a[1];
				if (!visited[field]) {
					if (dist < distTo[field] || distTo[field] == 0) {
						distTo[field] = dist;
					}
					toVisit.add(field);
				}
			}
			current = nextField(visited);
		}
	}
	
	private static int nextField(boolean[] visited) {
		long min = -1;
		int next = -1;
		for (int i = 0; i < 58; i++) {
			if (!visited[i] && (distTo[i] < min || min == -1)) {
				min = distTo[i];
				next = i;
			}
		}
		return next;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("comehome.in"));
		PrintWriter out = new PrintWriter(new File("comehome.out"));
		int N = in.nextInt();
		in.nextLine();
		for (int i = 0; i < 58; i++) {
			adj[i] = new ArrayList<int[]>();
		}
		for (int i = 0; i < N; i++) {
			String line = in.nextLine();
			int p1 = (int)line.charAt(0) - 65, p2 = (int)line.charAt(2) - 65, dist = Integer.parseInt(line.substring(4));
			int[] p1Data = {p1, dist}, p2Data = {p2, dist};
			adj[p1].add(p2Data);
			adj[p2].add(p1Data);
			if (p1 < 25) hasCow[p1] = true;
			if (p2 < 25) hasCow[p2] = true;
		}
		in.close();
		shortestPath();
		long ans = Integer.MAX_VALUE, index = 0;
		for (int i = 0; i < 25; i++) {
			if (hasCow[i]) {
				long dist = distTo[i];
				if (dist < ans) {
					ans = dist;
					index = i;
				}
			}
		}
		out.println((char)(index + 65) + " " + ans);
		out.close();
		System.exit(0);
	}

}
