/*
 ID: calviny1
 LANG: JAVA
 TASK: frac1
 */

import java.io.*;
import java.util.*;

public class frac1 {

	private static int gcd(int a, int b) {
		if (a == 0) return 1;
		int ans = 0, c = Math.min(a, b);
		for (int i = 1; i <= c; i++) {
			if (a % i == 0 && b % i == 0) {
				if (i > ans) ans = i;
			}
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("frac1.in"));
		PrintWriter out = new PrintWriter(new File("frac1.out"));
		int N = in.nextInt();
		in.close();
		TreeSet<int[]> fractions = new TreeSet<int[]>(new Comparator<int[]>() {
			public int compare(int[] frac1, int[]frac2) {
				if (frac1[0] == frac2[0] && frac1[1] == frac2[1]) return 0;
				float f1 = ((float)frac1[0] / (float)frac1[1]), f2 = ((float)frac2[0] / (float)frac2[1]), dif = f1 - f2;
				if (dif > 0) return 1;
				return -1;
			}
		});
		for (int i = 0; i <= N; i++) {
			for (int j = i; j <= N; j++) {
				if (j == 0) continue;
				int gcd = gcd(i, j);
				int[] foo = {i / gcd, j / gcd}, bar = {0, 1};
				int[] fraction = (i != 0) ? foo: bar;
				//System.out.println(fraction[0] + " " + fraction[1]);
				fractions.add(fraction);
			}
		}
		for (int[] f : fractions) {
			out.println(f[0] + "/" + f[1]);
		}
		out.close();
		System.exit(0);
	}

}
