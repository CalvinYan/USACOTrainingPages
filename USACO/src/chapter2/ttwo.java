package chapter2;
/*
 ID: calviny1
 LANG: JAVA
 TASK: ttwo
 */

import java.io.*;
import java.util.*;

public class ttwo {
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("ttwo.in"));
		PrintWriter out = new PrintWriter(new File("ttwo.out"));
		boolean[][] obstacle = new boolean[10][10];
		int[] cow = new int[3], john = new int[3];
		for (int i = 0; i < 10; i++) {
			String line = in.nextLine();
			for (int j = 0; j < 10; j++) {
				if (line.charAt(j) == '*') {
					obstacle[i][j] = true;
				} else if (line.charAt(j) == 'C') {
					int[] c = {i, j, 0};
					cow = c;
				} else if (line.charAt(j) == 'F') {
					int[] f = {i, j, 0};
					john = f;
				}
			}
		}
		in.close();
		int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		int count = 0;
		while (!(cow[0] == john[0] && cow[1] == john[1])) {
			if (count == 10000) {
				out.println(0);
				out.close();
				System.exit(0);
			}
			int[] newCow = {cow[0] + directions[cow[2]][0], cow[1] + directions[cow[2]][1], cow[2]},
				newJohn = {john[0] + directions[john[2]][0], john[1] + directions[john[2]][1], john[2]};
			if (inBounds(newCow[0], newCow[1]) && !obstacle[newCow[0]][newCow[1]]) {
				cow = newCow;
			} else {
				cow[2] = (cow[2] + 1) % 4;
			}
			if (inBounds(newJohn[0], newJohn[1]) && !obstacle[newJohn[0]][newJohn[1]]) {
				john = newJohn;
			} else {
				john[2] = (john[2] + 1) % 4;
			}
			count++;
		}
		out.println(count);
		out.close();
		System.exit(0);
	}
	
	private static boolean inBounds(int row, int col) {
		return row >= 0 && row < 10 && col >= 0 && col < 10;
	}
}
