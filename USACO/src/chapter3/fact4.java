package chapter3;

/*
 ID: calviny1
 LANG: JAVA
 TASK: fact4
 */

import java.io.*;
import java.util.*;

public class fact4 {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("fact4.in"));
		PrintWriter out = new PrintWriter(new File("fact4.out"));
		int N = in.nextInt();
		in.close();
		long divisor = 100000000;
		int digit = 1;
		for (int i = 1; i <= N; i++) {
			
			digit = (int)((digit * (i % divisor)) % divisor);
			while (digit % 10 == 0) digit /= 10;
		}
		/*for (int i = 1; i <= N; i++) {
			if (i % 10 == 5) {
				digit = (digit % 2 == 0) ? digit / 2 : 5;
			} else {
				digit *= i;
				while (digit % 10 == 0) digit /=  10;
				digit %= 10;
			}
			
		}*/
		out.println(digit % 10);
		out.close();
		System.exit(0);
	}

}
