/*
 ID: calviny1
 LANG: JAVA
 TASK: castle
 */

import java.io.*;
import java.util.*;

public class castle {

	private static int N = 0, M = 0, numRooms = 0, maxSize = 0;
	
	private static int[][] modules;
	
	private static ArrayList<Integer[]> edges = new ArrayList<Integer[]>();
	
	private static void floodFill(int[][] modules) {
		boolean[][] visited = new boolean[N][M];
		for (int a = 0; a < N; a++) {
			for (int b = 0; b < N; b++) {
				visited[a][b] = false;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				toVisit.add(i * M + j);
			}
		}
		while (!toVisit.isEmpty()) {
			int size = 0;
			TreeSet<Integer> toFill = new TreeSet<Integer>();
			numRooms++;
			int next = toVisit.first();
			toFill.add(next);
			while(!toFill.isEmpty()) {
				size++;
				int filled = toFill.first(), row = filled / M, col = filled % M;
				toFill.remove(filled);
				toVisit.remove(filled);
				if (!((modules[row][col] & 1) == 1) && toVisit.contains(row * M + col - 1)) {
					toFill.add(row * M + col - 1);
				}
				if (!((modules[row][col] & 2) == 2) && toVisit.contains((row - 1) * M + col)) {
					toFill.add((row - 1) * M + col);
				}
				if (!((modules[row][col] & 4) == 4) && toVisit.contains(row * M + col + 1)) {
					toFill.add(row * M + col + 1);
				}
				if (!((modules[row][col] & 8) == 8) && toVisit.contains((row + 1) * M + col)) {
					toFill.add((row + 1) * M + col);
				}
			}
			if (size > maxSize) maxSize = size;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("castle.in"));
		PrintWriter out = new PrintWriter(new File("castle.out"));
		M = in.nextInt();
		N = in.nextInt();
		modules = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				modules[i][j] = in.nextInt();
				boolean hasRight = (modules[i][j] & 4) == 4, hasDown = (modules[i][j] & 8)== 8;
				if (j < M - 1 && hasRight) {
					Integer[] edge = {i, j, 0};
					edges.add(edge);
				}
				if (i < N - 1 && hasDown) {
					Integer[] edge = {i, j, 1};
					edges.add(edge);
				}
			}
		}
		in.close();
		floodFill(modules);
		out.println(numRooms);
		out.println(maxSize);
		int newMaxSize = 0;
		int[] sizes = new int[edges.size()];
		int counter = 0;
		ArrayList<int[]> optimals = new ArrayList<int[]>();
		Integer[][] edgeArr = new Integer[edges.size()][3];
		for (Integer[] edge : edges) {
			edgeArr[counter] = edge;
			int[][] temp = new int[N][M];
			for (int p = 0; p < N; p++) {
				for (int q = 0; q < M; q++) {
					temp[p][q] = modules[p][q];
				}
			}
			int dir = edge[2], row = edge[0], col = edge[1];
			if (dir == 0) {
				temp[row][col] -= 4;
				temp[row][col + 1] -= 1;
			}
			if (dir == 1) {
				temp[row][col] -= 8;
				temp[row + 1][col] -= 2;
			}
			maxSize = 0;
			floodFill(temp);
			sizes[counter++] = maxSize;
			if (maxSize > newMaxSize) {
				newMaxSize = maxSize;
			}
		}
		out.println(newMaxSize);
		for (int a = 0; a < sizes.length; a++) {
			if (sizes[a] == newMaxSize) {
				Integer[] edge = edgeArr[a];
				if (edge[2] == 0) {
					int[] optimal = {edge[0] + 1, edge[1] + 1, edge[2]};
					optimals.add(optimal);
				} else {
					int[] optimal = {edge[0] + 2, edge[1] + 1, edge[2]};
					optimals.add(optimal);
				}
			}
		}
		Integer[][] oArr = new Integer[optimals.size()][3];
		for (int a = 0; a < optimals.size(); a++) {
			int[] optimal = optimals.get(a);
			for (int b = 0; b < 3; b++) {
				oArr[a][b] = optimal[b];
			}
		}
		Arrays.sort(oArr, new Comparator<Integer[]>() {
			public int compare(Integer[] arr1, Integer[] arr2) {
				if (arr1[1] != arr2[1]) {
					return arr1[1] - arr2[1];
				} else if (arr1[0] != arr2[0]) {
					return arr2[0] - arr1[0];
				} else if (arr1[2] != arr2[2]) {
					return arr2[2] - arr1[2];
				}
				return 0;
			}
		});
		Integer[] edge = oArr[0];
		out.print(edge[0] + " " + edge[1] + " ");
		if (edge[2] == 0) {
			out.print("E");
		} else {
			out.print("N");
		}
		out.println();
		out.close();
		System.exit(0);
	}
}
