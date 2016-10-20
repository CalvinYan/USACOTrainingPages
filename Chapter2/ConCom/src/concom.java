/*
 ID: calviny1
 LANG: JAVA
 TASK: concom
 */

import java.io.*;
import java.util.*;

public class concom {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("concom.in"));
		PrintWriter out = new PrintWriter(new File("concom.out"));
		int N = in.nextInt();
		int[][] percentOf = new int[100][100];
		boolean[] visit = new boolean[100 * 100];
		ArrayList<int[]> ownerships = new ArrayList<int[]>();
		boolean[][] owns = new boolean[100][100];
		for (int i = 0; i < N; i++) {
			int owner = in.nextInt() - 1, owned = in.nextInt() - 1, share = in.nextInt();
			percentOf[owner][owned] = share;
			visit[owner * 100 + owned] = true;
			if (share > 50) {
				int[] data = {owner, owned};
				ownerships.add(data);
				owns[owner][owned] = true;
			}
		}
		in.close();
		int count = 0;
		while (!ownerships.isEmpty()) {
			int[] ownership = ownerships.remove(0);
			int owner = ownership[0], owned = ownership[1];
			owns[owner][owned] = true;
			for (int i = 0; i < 100; i++) {
				if (owned != i) {
					int toAdd = percentOf[owned][i];
					percentOf[owner][i] += toAdd;
					if (percentOf[owner][i] > 50) {
						if (!owns[owner][i]) {
							owns[owner][i] = true;
							int[] data = {owner, i};
							ownerships.add(data);
						}
					}
				}
				
				if (owner != i) {
					if (owns[i][owned]) {
						int toAdd = percentOf[owner][owned];
						percentOf[i][owned] += toAdd;
						if (percentOf[i][owned] > 50) {
							if (!owns[i][owned]) {
								owns[i][owned] = true;
								int[] data = {i, owned};
								ownerships.add(data);
							}
						}
					}
				}
			}
			
		}
		/*boolean done = false;
		while(!done) {
			done = true;
			for (int k = 0; k < 100 * 100; k++) {
				if (visit[k]) {
					visit[k] = false;
					int i = k / 100, j = k % 100;
					if (percentOf[i][j] > 50) {
						if (!owns[i][j]) {
							owns[i][j] = true;
							for (int a = 0; a < 100; a++) {
								if (a != j) {
									percentOf[i][a] += percentOf[j][a];
									if (percentOf[j][a] > 0) {
										visit[i * 100 + a] = true;
									}
									done = false;
								}
							}
						}
					}
				}
			}
		}*/
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (i != j && owns[i][j]) {
					out.println((i + 1) + " " + (j + 1));
				}
			}
		}
		out.close();
		System.exit(0);
	}
}
