/*
 ID: calviny1
 LANG: JAVA
 TASK: cowtour
 */

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class cowtour {
	
	private static int N = 0;
	
	private static int[][] points, adj;
	
	private static double[][] floydWarshall() {
		double[][] distTo = new double[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i != j) {
					if (adj[i][j] == 1) {
						distTo[i][j] = distance(points[i], points[j]);
						continue;
					}
				}
				distTo[i][j] = Double.POSITIVE_INFINITY;
			}
		}
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i != j) {
						double intermediate = distTo[i][k] + distTo[k][j];
						if (intermediate < distTo[i][j]) distTo[i][j] = intermediate;
					}
				}
			}
		}
		return distTo;
	}
	
	private static double distance(int[] p1, int[] p2) {
		int x = p1[0] - p2[0], y = p1[1] - p2[1];
		return Math.sqrt(x * x + y * y);
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("cowtour.in"));
		PrintWriter out = new PrintWriter(new File("cowtour.out"));
		N = in.nextInt();
		points = new int[N][2];
		for (int i = 0; i < N; i++) {
			points[i][0] = in.nextInt();
			points[i][1] = in.nextInt();
		}
		adj = new int[N][N];
		in.nextLine();
		for (int i = 0; i < N; i++) {
			String line = in.nextLine();
			for (int j = 0; j < N; j++) {
				adj[i][j] = Character.digit(line.charAt(j), 10);
			}
		}
		in.close();
		double[][] distTo = floydWarshall();
		double[] maxDistance = new double[N];
		for (int i = 0; i < N; i++) {
			double max = 0;
			for (int j = 0; j < N; j++) {
				double dist = distTo[i][j];
				if (dist != Double.POSITIVE_INFINITY && dist > max) max = dist;
			}
			maxDistance[i] = max;
		}
		double ans = Double.POSITIVE_INFINITY;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i != j && distTo[i][j] == Double.POSITIVE_INFINITY) {
					double dist = maxDistance[i] + distance(points[i], points[j]) + maxDistance[j];
					double newDist = dist;
					for (double d : maxDistance) {
						if (d > newDist) newDist = d;
					}
					if (newDist < ans) {
						ans = newDist;
					}
				}
			}
		}
		DecimalFormat format = new DecimalFormat(".000000");
		out.println(format.format(ans));
		out.close();
		System.exit(0);
	}

}
