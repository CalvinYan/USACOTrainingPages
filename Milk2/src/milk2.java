/*
 ID: calviny1
 LANG: JAVA
 TASK: milk2
 */

import java.util.*;
import java.io.*;

public class milk2 {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("milk2.in"));
		PrintWriter out = new PrintWriter(new File("milk2.out"));
		int N = in.nextInt();
		int[] starts = new int[N], stops = new int[N];
		for (int i = 0; i < N; i++) {
			starts[i] = in.nextInt();
			stops[i] = in.nextInt();
		}
		Arrays.sort(starts);
		Arrays.sort(stops);
		in.close();
		int startPtr = 0, stopPtr = 0;
		int numCows = 0;
		int maxMilkStreak = 0, maxIdleStreak = 0;
		int milkStreak = 0, idleStreak = 0;
		while(startPtr < N || stopPtr < N) {
			int nextStart = (startPtr < N) ? starts[startPtr] : 1000000;
			int nextStop = (stopPtr < N) ? stops[stopPtr] : 1000000;
			if (nextStart < nextStop) {
				numCows++;
				if (numCows == 1 && startPtr > 0) {
					idleStreak = nextStart - stops[stopPtr - 1];
					if (maxIdleStreak < idleStreak) maxIdleStreak = idleStreak;
				} else if (startPtr > 0) {
					milkStreak += nextStart - Math.max(starts[startPtr - 1], (stopPtr > 0) ? stops[stopPtr - 1] : -1);
				}
				startPtr++;
			} else {
				numCows--;
				milkStreak += nextStop - Math.max(starts[startPtr - 1], (stopPtr > 0) ? stops[stopPtr - 1] : -1);
				if (numCows == 0 && nextStop != nextStart) {
					if (maxMilkStreak < milkStreak) {
						maxMilkStreak = milkStreak;
					}
					milkStreak = 0;
				}
				stopPtr++;
			}
		}
		out.println(maxMilkStreak + " " + maxIdleStreak);
		out.close();
		System.exit(0);
	}
}
