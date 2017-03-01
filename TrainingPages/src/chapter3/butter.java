package chapter3;

/*
 ID: calviny1
 LANG: JAVA
 TASK: butter
 */

import java.io.*;
import java.util.*;

public class butter {
	
	private static int N, C, P;
	
	private static ArrayList<int[]>[] adj;

	private static int[] locations;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("butter.in"));
		PrintWriter out = new PrintWriter(new File("butter.out"));
		N = in.nextInt();
		P = in.nextInt();
		C = in.nextInt();
		locations = new int[N];
		for (int i = 0; i < N; i++) {
			locations[i] = in.nextInt();
		}
		adj = new ArrayList[P + 1];
		for (int i = 0; i <= P; i++) {
			adj[i] = new ArrayList<int[]>();
		}
		for (int i = 0; i < C; i++) {
			int from = in.nextInt(), to = in.nextInt(), dist = in.nextInt();
			adj[from].add(new int[]{to, dist});
			adj[to].add(new int[]{from, dist});
		}
		in.close();
		int[][] distTo = paths();
		int ans = Integer.MAX_VALUE;
		for (int i = 1; i <= P; i++) {
			int total = 0;
			for (int location : locations) total += distTo[i][location];
			ans = Math.min(total, ans);
		}
		out.println(ans);
		out.close();
		System.exit(0);
	}
	
	private static int[][] paths() {
		int[][] distTo = new int[P + 1][P + 1];
		
		for (int i = 0; i <= P; i++) {
			for (int j = 0; j <= P; j++) {
				distTo[i][j] = (i != j) ? Integer.MAX_VALUE : 0;
			}
		}
		
		for (int i = 0; i < P; i++) {
			ArrayList<int[]> list = adj[i];
			for (int[] path : list) {
				distTo[i][path[0]] = path[1];
			}
		}
		
		for (int source : locations) {
			PriorityQueue<int[]> toVisit = new PriorityQueue<int[]>(new Comparator<int[]>() {
				public int compare(int[] one, int[] two) {
					int diff = one[1] - two[1];
					return (diff != 0) ? diff : one[0] - two[0];
				}
			});
			
			boolean[] visited = new boolean[P + 1];
			
			int count = 0;
			toVisit.add(new int[]{source, 0});
			while (count < P) {
				int[] next = toVisit.remove();
				int current = next[0], dist = next[1];
				if (visited[current]) continue;
				count++;
				distTo[source][current] = distTo[current][source] = dist;
				visited[current] = true;
				for (int[] node : adj[current]) {
					int nextNode = node[0], nextDist = dist + node[1];
					if (!visited[nextNode]) toVisit.add(new int[]{nextNode, nextDist});
				}
			}
		}
		return distTo;
	}
}
	
