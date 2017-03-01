package chapter3;

/*
 ID: calviny1
 LANG: JAVA
 TASK: camelot
 */

import java.io.*;
import java.util.*;

public class camelot {
	
	private static int R, C;
	
	private static int[] king;
	
	private static ArrayList<int[]> knights = new ArrayList<int[]>();
	
	private static int[][][][] distTo;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		R = in.nextInt();
		C = in.nextInt();
		king = indexOf(in.next(), in.nextInt());
		while (in.hasNext()) {
			knights.add(indexOf(in.next(), in.nextInt()));
		}
		in.close();
		distTo = djikstra();
		int ans = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int moves = 0;
				int[] target = {i, j};
				TreeSet<int[]> knightSet = new TreeSet<int[]>(new Comparator<int[]> () {
					public int compare(int[] one, int[] two) {
						return movesSaved(two, target) - movesSaved(one, target);
					}
				});
				for (int[] knight : knights) {
					moves += 
					knightSet.add(knight);
				}
				int[] first = knightSet.first();
				int movesSaved = movesSaved(first, target);
				if (movesSaved > 0) {
					
				}
			}
		}
		out.println(king[0] + " " + king[1]);
		out.close();
		System.exit(0);
	}
	
	private static int[][][][] djikstra() {
		knights.add(king);
		int[][][][] distTo = new int[R][C][R][C];
		int[][] directions = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
		for (int[] knight : knights) {
			boolean[][] visited = new boolean[R][C];
			int firstRow = knight[0], firstCol = knight[1];
			PriorityQueue<int[]> toVisit = new PriorityQueue<int[]>(new Comparator<int[]>() {
				public int compare(int[] one, int[] two) {
					int distOne = one[2], distTwo = two[2];
					if (distOne == distTwo) {
						return (one[0] * R + one[1] * C) - (two[0] * R + two[1] * C);
					}
					return distOne - distTwo;
				}
			});
			toVisit.add(new int[]{firstRow, firstCol, 0});
			while (!toVisit.isEmpty()) {
				int[] current = toVisit.remove();
				int row = current[0], col = current[1], dist = current[2];
				if (visited[row][col]) continue;
				visited[row][col] = true;
				distTo[firstRow][firstCol][row][col] = distTo[row][col][firstRow][firstCol] = dist;
				for (int[] direction : directions) {
					int newRow = row - direction[0], newCol = col - direction[1];
					if (valid(newRow, newCol)) toVisit.add(new int[]{newRow, newCol, dist + 1});
				}
			}
		}
		knights.remove(king);
		return distTo;
	}
	
	private static int movesSaved(int[] knight, int[] target) {
		int kingDist = distance(knight, king) + distance(king, target);
		int normalDist = distance(knight, target) + kingDistance(target);
		return normalDist - kingDist;
	}
	
	private static boolean valid(int row, int col) {
		return row >= 0 && col >= 0 && row < R && col < C;
	}
		
	private static int[] indexOf(String col, int row) {
		return new int[]{8 - row, col.charAt(0) - 65};
	}
	
	private static int distance(int[] pt1, int[] pt2) {
		return distTo[pt1[0]][pt1[0]][pt2[0]][pt2[1]];
	}
	
	private static int kingDistance(int[] target) {
		int rowDist = Math.abs(target[0] - king[0]), colDist = Math.abs(target[1] - king[1]);
		return Math.max(rowDist, colDist);
	}

}
