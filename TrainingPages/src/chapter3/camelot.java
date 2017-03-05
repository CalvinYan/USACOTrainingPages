//package chapter3;

/*
 ID: calviny1
 LANG: JAVA
 TASK: camelot
*/

import java.io.*;
import java.util.*;

public class camelot {
	
	private static int R, C, threshold = 4;
	
	private static int kingRow, kingCol;
	
	private static ArrayList<int[]> knights = new ArrayList<int[]>();
	
	private static int[][][][] distTo;
	
	private static class State implements Comparable<State> {
		int row, col, dist;
		
		public State(int r, int c, int d) {
			row = r;
			col = c;
			dist = d;
		}
		
		public int compareTo(State that) {
			if (this.dist != that.dist) return this.dist - that.dist;
			return (this.row * C + this.col) - (that.row * C + that.col);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in;
		PrintWriter out;
		boolean debug = false;
		if (debug) {
			in = new BufferedReader(new FileReader(new File("camelot.txt")));
			out = new PrintWriter(System.out);
		} else {
			in = new BufferedReader(new FileReader(new File("camelot.in")));
			out = new PrintWriter(new File("camelot.out"));
		}
		StringTokenizer str = new StringTokenizer(in.readLine());
		R = Integer.parseInt(str.nextToken());
		C = Integer.parseInt(str.nextToken());
		distTo = new int[R][C][R][C];
		for (int a = 0; a < R; a++) {
			for (int b = 0; b < C; b++) {
				for (int c = 0; c < R; c++) {
					for (int d = 0; d < C; d++) {
						distTo[a][b][c][d] = 100000000;
					}
				}
			}
		}
		str = new StringTokenizer(in.readLine());
		kingCol = toColumn(str.nextToken());
		kingRow = R - Integer.parseInt(str.nextToken());
		String line;
		while ((line = in.readLine()) != null) {
			str = new StringTokenizer(line);
			while (str.hasMoreTokens()) {
				int col = toColumn(str.nextToken()), row = R - Integer.parseInt(str.nextToken());
				knights.add(new int[]{row, col});
			}
		}
		in.close();
		for (int[] knight : knights) BFS(knight[0], knight[1]);
		for (int i = threshold/2 * -1; i < (threshold/2) + 1; i++) {
			for (int j = threshold/2 * -1; j < (threshold/2) + 1; j++) {
				int newRow = kingRow + i, newCol = kingCol + j;
				if (inRange(newRow, newCol)) BFS(newRow, newCol);
			}
		}
		int ans = Integer.MAX_VALUE;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				int dist = 0;
				for (int[] knight : knights) dist += distTo[knight[0]][knight[1]][r][c];
				dist += kingDist(r, c);
				ans = Math.min(ans, dist);
				
				
				for (int i = threshold/2 * -1; i < (threshold/2) + 1; i++) {
					for (int j = threshold/2 * -1; j < (threshold/2) + 1; j++) {
						int r1 = kingRow + i, c1 = kingCol + j;
						if (!inRange(r1, c1)) continue;
						int movesSaved = 0;
						for (int[] knight : knights) {
							int newDist = distTo[knight[0]][knight[1]][r1][c1] + kingDist(r1, c1) + distTo[r1][c1][r][c];
							int saved = kingDist(r, c) + distTo[knight[0]][knight[1]][r][c] - newDist;
							movesSaved = Math.max(movesSaved, saved);
						}
						ans = Math.min(ans, dist - movesSaved);
					}
				}
			}
		}
		out.println(ans);
		out.close();
		System.exit(0);
	}
	
	private static void shortestPath(int startRow, int startCol) {
		PriorityQueue<State> toVisit = new PriorityQueue<State>();
		boolean[][] visited = new boolean[R][C];
		toVisit.add(new State(startRow, startCol, 0));
		while (!toVisit.isEmpty()) {
			State current = toVisit.remove();
			int row = current.row, col = current.col, dist = current.dist;
			if (visited[row][col]) continue;
			visited[row][col] = true;
			distTo[startRow][startCol][row][col] = dist;
			int[][] directions = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
			for (int i = 0; i < 8; i++) {
				int newRow = row + directions[i][0], newCol = col + directions[i][1];
				if (inRange(newRow, newCol)) {
					if (!visited[newRow][newCol]) {
						toVisit.add(new State(newRow, newCol, dist+1));
					}
				}
			}
		}
	}
	
	private static void BFS(int startRow, int startCol) {
		LinkedList<State> toVisit = new LinkedList<State>();
		boolean[][] visited = new boolean[R][C];
		toVisit.add(new State(startRow, startCol, 0));
		while (!toVisit.isEmpty()) {
			State current = toVisit.remove();
			int row = current.row, col = current.col, dist = current.dist;
			if (visited[row][col]) continue;
			visited[row][col] = true;
			distTo[startRow][startCol][row][col] = dist;
			int[][] directions = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
			for (int i = 0; i < 8; i++) {
				int newRow = row + directions[i][0], newCol = col + directions[i][1];
				if (inRange(newRow, newCol)) {
					if (!visited[newRow][newCol]) {
						toVisit.add(new State(newRow, newCol, dist+1));
					}
				}
			}
		}
	}
	
	private static void floydWarshall() {
		for (int a = 0; a < R; a++) {
			for (int b = 0; b < C; b++) {
				int[][] directions = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
				for (int i = 0; i < 8; i++) {
					int newRow = a + directions[i][0], newCol = b + directions[i][1];
					if (inRange(newRow, newCol)) {
						distTo[a][b][newRow][newCol] = 1;
					}
				}
				for (int c = 0; c < R; c++) {
					for (int d = 0; d < C; d++) {
						if (distTo[a][b][c][d] != 1) distTo[a][b][c][d] = Integer.MAX_VALUE;
					}
				}
			}
		}
		
		for (int k = 0; k < R * C; k++) {
			for (int i = 0; i < R * C; i++) {
				for (int j = 0; j < R * C; j++) {
					System.out.printf("%d %d %d\n", i, j, k);
					int iRow = i / C, iCol = i % C, jRow = j / C, jCol = j % C, kRow = k / C, kCol = k % C;
					distTo[iRow][iCol][jRow][jCol] = Math.min(distTo[iRow][iCol][jRow][jCol], distTo[iRow][iCol][kRow][kCol] + distTo[kRow][kCol][jRow][jCol]);
				}
			}
		}
	}
	
	private static boolean inRange(int row, int col) {
		return row >= 0 && row < R && col >= 0 && col < C;
	}
	
	private static int kingDist(int row, int col) {
		return Math.max(Math.abs(kingRow - row), Math.abs(kingCol - col));
	}
	
	private static int toColumn(String letter) {
		return letter.charAt(0) - 65;
	}
		
}
