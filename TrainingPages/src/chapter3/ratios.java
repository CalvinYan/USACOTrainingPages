package chapter3;

/*
 ID: calviny1
 LANG: JAVA
 TASK: ratios
 */

import java.io.*;
import java.util.*;

public class ratios {
	
	private static int[] target;
	
	private static int[][] mixtures = new int[3][3];

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("ratios.in"));
		PrintWriter out = new PrintWriter(new File("ratios.out"));
		
		target = new int[]{in.nextInt(), in.nextInt(), in.nextInt()};
		for (int i = 0; i < 3; i++) {
			mixtures[i] = new int[]{in.nextInt(), in.nextInt(), in.nextInt()};
		}
		in.close();
		
		for (int a = 0; a < 100; a++) {
			for (int b = 0; b < 100; b++) {
				for (int c = 0; c < 100; c++) {
					if (a + b + c == 0) continue;
					//System.out.println(a + " " + b + " " + c);
					int ratio;
					if ((ratio = goalMultiple(new int[]{a, b, c})) != -1) {
						out.println(a + " " + b + " " + c + " " + ratio);
						out.close();
						System.exit(0);
					}
				}
			}
		}
		out.println("NONE");
		out.close();
		System.exit(0);
	}
	
	private static int goalMultiple(int[] units) {
		int[] amounts = new int[3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				amounts[i] += units[j] * mixtures[j][i];
			}
		}
		//System.out.println(Arrays.toString(amounts));
		double ratio = (double)amounts[0] / target[0];
		if (ratio >= 1 && target[1] * ratio == amounts[1] && target[2] * ratio == amounts[2]) {
			return (int)ratio;
		} else return -1;
	}

}
