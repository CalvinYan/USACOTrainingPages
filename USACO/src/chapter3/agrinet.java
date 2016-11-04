package chapter3;
/*
 ID: calviny1
 LANG: JAVA
 TASK: agrinet
 */

import java.io.*;
import java.util.*;

public class agrinet {

	private static int[][] distTo;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("agrinet.in"));
		PrintWriter out = new PrintWriter(new File("agrinet.out"));
		final int N = in.nextInt();
		distTo = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				distTo[i][j] = in.nextInt();
			}
		}
		in.close();
		int ans = 0, count = 0;
		TreeSet<int[]> edges = new TreeSet<int[]>(new Comparator<int[]>() {
			public int compare(int[] one, int[] two) {
				int diff = distTo[one[0]][one[1]] - distTo[two[0]][two[1]];
				if (diff == 0) {
					if (Arrays.equals(one, two)) {
						return 0;
					} else {
						return one[0] * N + one[1] - two[0] * N - two[1];
					}
				}
				return diff;
			}
		});
		boolean[] inTree = new boolean[N];
		edges.add(new int[]{0, 0});
		while (count < N) {
			int[] edge = edges.first();
			edges.remove(edges.first());
			if (inTree[edge[0]] && inTree[edge[1]]) continue;
			inTree[edge[0]] = inTree[edge[1]] = true;
			ans += distTo[edge[0]][edge[1]];
			count++;
			for (int i = 0; i < N; i++) {
				if (i == edge[1]) continue;
				edges.add(new int[]{edge[1], i});
			}
		}
		out.println(ans);
		out.close();
	}

}
