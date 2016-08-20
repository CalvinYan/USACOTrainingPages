/*
 ID: calviny1
 LANG: JAVA
 TASK: wormhole
 */

import java.io.*;
import java.util.*;

public class wormhole {
	
	private static int N;
	
	private static int loops(int[] adj, Integer[][] holes, TreeSet<Integer> remaining) {
		if (remaining.isEmpty()) {
			//Search for infinite loop
			for (int i = 0; i < N - 1; i++) {
				if (holes[i][1].equals(holes[i+1][1])) {
					int to = adj[i+1];
					if (to == i) {
						return 1;
					} else {
						while (to < N - 1 && holes[to][1].equals(holes[to+1][1])) {
							if (adj[to+1] == i) return 1;
							to = adj[to+1];
						}
					}
				}
			}
			return 0;
		}
		int loops = 0, next = remaining.first();
		remaining.remove(next);
		TreeSet<Integer> temp = new TreeSet<Integer>(remaining);
		for (int r : temp) {
			adj[next] = r;
			adj[r] = next;
			remaining.remove(r);
			loops += loops(adj, holes, remaining);
			adj[r] = adj[next] = -1;
			remaining.add(r);
		}
		remaining.add(next);
		return loops;
	}

 	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("wormhole.in"));
		PrintWriter out = new PrintWriter(new File("wormhole.out"));
		N = in.nextInt();
		Integer[][] holes = new Integer[N][2];
		for (int i = 0; i < N; i++) {
			holes[i][0] = in.nextInt();
			holes[i][1] = in.nextInt();
		}
		Arrays.sort(holes, new Comparator<Integer[]>() {
			public int compare(Integer[] one, Integer[] two) {
				return one[0] - two[0];
			}
		});
		Arrays.sort(holes, new Comparator<Integer[]>() {
			public int compare(Integer[] one, Integer[] two) {
				return one[1] - two[1];
			}
		});
		int[] adj = new int[N];
		Arrays.fill(adj, -1);
		TreeSet<Integer> remaining = new TreeSet<Integer>();
		for (int j = 0; j < N; j++) {
			remaining.add(j);
		}
		in.close();
		out.println(loops(adj, holes, remaining));
		out.close();
		System.exit(0);
	}

}
