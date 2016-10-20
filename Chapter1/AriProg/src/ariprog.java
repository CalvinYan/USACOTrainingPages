/*
 ID: calviny1
 LANG: JAVA
 TASK: ariprog
 */

import java.io.*;
import java.util.*;

public class ariprog {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("ariprog.in"));
		PrintWriter out = new PrintWriter(new File("ariprog.out"));
		int N = in.nextInt(), M = in.nextInt();
		in.close();
		TreeSet<Integer> bisquares = new TreeSet<Integer>();
		for (int i = 0; i <= M; i++) {
			for (int j = 0; j <= M; j++) {
				bisquares.add(i * i + j * j);
			}
		}
		int len = bisquares.size();
		ArrayList<Integer[]> progs = new ArrayList<Integer[]>();
		Integer[] biArray = bisquares.toArray(new Integer[0]);
		for (int a = 0; a < len; a++) {
			if (bisquares.last() - a <= M - 1) break;
			for (int b = a + 1; b < len; b++) {
				int aNum = biArray[a], bNum = biArray[b], dif = bNum - aNum;
				if (aNum + (N - 1) * dif > bisquares.last()) break;
				int count = 2;
				while (bisquares.contains(aNum + (N - count + 1)  * dif)) {
					count++;
					if (count == N) {
						Integer[] arr = {aNum, dif};
						progs.add(arr);
						break;
					}
				}
			}
		}
		if (progs.isEmpty()) {
			out.println("NONE");
			out.close();
			System.exit(0);
		}
		Integer[][] arr = progs.toArray(new Integer[0][]);
		Arrays.sort(arr, new Comparator<Integer[]>() {
			public int compare(Integer[] one, Integer[] two) {
				return one[0] - two[0];
			}
		});
		Arrays.sort(arr, new Comparator<Integer[]>() {
			public int compare(Integer[] one, Integer[] two) {
				return one[1] - two[1];
			}
		});
		for (Integer[] a : arr) {
			out.println(a[0] + " " + a[1]);
		}
		out.close();
		System.exit(0);
	}

}
