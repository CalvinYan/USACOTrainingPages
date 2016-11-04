package chapter1;
/*
 ID: calviny1
 LANG: JAVA
 TASK: beads
 */

import java.io.*;
import java.util.*;

public class beads {

	private static int strLength;
	
	private static String necklace;
	
	private static int numBeads(int leftInd, int rightInd) {
		int leftBeads = 0;
		char c, c2;
		while ((c = necklace.charAt(mod(leftInd - leftBeads))) == 'w') {
			if (leftBeads == strLength) return strLength;
			leftBeads++;
		}
		c = necklace.charAt(mod(leftInd - leftBeads++));
		while ((c2 = necklace.charAt(mod(leftInd - leftBeads))) == c || c2 == 'w') {
			if (leftBeads == strLength) return strLength;
			leftBeads++;
		}
		int rightBeads = 0;
		int limit = (mod(leftInd - leftBeads + 1));
		while ((c = necklace.charAt(mod(rightInd + rightBeads))) == 'w') {
			if (mod(rightInd + rightBeads) == limit) break;
			rightBeads++;
		}
		c = necklace.charAt(mod(rightInd + rightBeads++));
		while ((c2 = necklace.charAt(mod(rightInd + rightBeads))) == c || c2 == 'w') {
			if (mod(rightInd + rightBeads) == limit) break;
			rightBeads++;
		}
		return leftBeads + rightBeads;
	}
	
	private static int mod(int n) {
		return (n > 0) ? n % strLength : mod(strLength + n);
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("beads.in"));
		PrintWriter out = new PrintWriter(new File("beads.out"));
		strLength = in.nextInt();
		necklace = in.next();
		in.close();
		int ans = -1;
		for (int i = 0; i <= strLength; i++) {
			int num = numBeads(mod(i - 1), i % strLength);
			if (num > ans) ans = num;
		}
		out.println(ans);
		out.close();
		System.exit(0);
	}

}
