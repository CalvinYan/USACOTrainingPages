package chapter1;
/*
 ID: calviny1
 LANG: JAVA
 TASK: friday
 */

import java.io.*;
import java.util.*;

public class friday {

	private static int[] numDaysAfter = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	private static int[] freq = new int[7];
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("friday.in"));
		PrintWriter out = new PrintWriter(new File("friday.out"));
		int N = in.nextInt();
		in.close();
		int currentDay = 2, currentYear = 1900;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 12; j++) {
				int dayOnThirteenth = (currentDay + 12) % 7;
				freq[dayOnThirteenth]++;
				currentDay = (currentDay + numDaysAfter[j]) % 7;
				if (j == 1 && (currentYear % 400 == 0 || (currentYear % 4 == 0 && currentYear % 100 != 0))) {
					currentDay++;
				}
			}
			currentYear++;
		}
		for (int k = 0; k < 7; k++) {
			out.print(freq[k]);
			if (k < 6) out.print(" ");
		}
		out.println();
		out.close();
	}

}
