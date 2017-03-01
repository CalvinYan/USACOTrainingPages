package chapter3;

/*
 ID: calviny1
 LANG: JAVA
 TASK: spin
 */

import java.io.*;
import java.util.*;

public class spin {

	private static int[][][] wedges = new int[5][5][2];
	
	private static int[] speeds = new int[5];
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("spin.in"));
		PrintWriter out = new PrintWriter(new File("spin.out"));
		
		// Read input
		for (int i = 0; i < 5; i++) {
			speeds[i] = in.nextInt();
			int nWedges = in.nextInt();
			
			
			for (int j = 0; j < 5; j++) {
				if (j < nWedges) {
					int start = in.nextInt(), end = start + in.nextInt();
					wedges[i][j] = new int[]{start, end % 360};
				} else {
					wedges[i][j] = new int[]{-1, -1};
				}
			}
			
		}
		in.close();
		
		int time = 0;
		while (time < 2000 && !aligned(time)) {
			time++;
		}
		out.println((time == 2000) ? "none" : time);
		out.close();
		System.exit(0);
		
	}
	
	private static boolean aligned(int time) {
		for (int i = 0; i < 360; i++) {
			int j = 0;
			while (j < 5 && wheelContains(j, i, time)) j++;
			if (j == 5) {
				//System.out.println(i);
				return true;
			}
		}
		return false;
	}
	
	private static boolean wheelContains(int wheel, int angle, int time) {
		int[] wedge;
		int i = 0;
		while (i < 5 && !Arrays.equals((wedge = wedges[wheel][i]), new int[]{-1, -1})) {
			int newStart = wedge[0] + speeds[wheel] * time, newEnd = wedge[1] + speeds[wheel] * time;
			if (wedgeContains(newStart % 360, newEnd % 360, angle)) return true;
			i++;
		}
		return false;
	}
	
	private static boolean wedgeContains(int start, int end, int angle) {
		if (end < start) end += 360;
		return (start <= angle && angle <= end) || (start <= angle + 360 && angle + 360 <= end);
	}

}
