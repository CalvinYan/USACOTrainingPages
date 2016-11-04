package chapter1;
/*
 ID: calviny1
 LANG: JAVA
 TASK: ride
 FILE: Ride.java
 */

import java.io.*;
import java.util.*;

class ride {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("ride.in"));
		PrintWriter out = new PrintWriter(new File("ride.out"));
		String str1 = in.nextLine(), str2 = in.nextLine();
		int len1 = str1.length(), len2 = str2.length();
		int product1 = 1, product2 = 1;
		for (int i = 0; i < len1; i++) {
			product1 *= (int)str1.charAt(i) - 64;
		}
		for (int j = 0; j <len2; j++) {
			product2 *= (int)str2.charAt(j) - 64;
		}
		if (product1 % 47 == product2 % 47) {
			out.println("GO");
		} else out.println("STAY");
		out.close();
	}
}
