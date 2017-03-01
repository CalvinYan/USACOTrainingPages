package chapter1;
/*
 ID: calviny1
 LANG: JAVA
 TASK: gift1
 */

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class gift1 {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("gift1.in"));
		PrintWriter out = new PrintWriter(new File("gift1.out"));
		HashMap<String, Integer> differences = new HashMap<String, Integer>();
		int N = in.nextInt();
		in.nextLine();
		String[] names = new String[N];
		for (int i = 0; i < N; i++) {
			String name = in.nextLine();
			differences.put(name, 0);
			names[i] = name;
		}
		for (int j = 0; j < N; j++) {
			if (!in.hasNextLine()) break;
			String giver = in.next();
			int amount = in.nextInt(), numRecipients = in.nextInt();
			if (numRecipients > 0) {
				differences.put(giver, differences.get(giver) - amount + (amount % numRecipients));
				for (int k = 0; k < numRecipients; k++) {
					String recipient = in.next();
					differences.put(recipient, differences.get(recipient) + amount / numRecipients);
				}
			}
		}
		for (int l = 0; l < N; l++) {
			out.println(names[l] + " " + differences.get(names[l]));
		}
		in.close();
		out.close();
		System.exit(0);
	}

}
