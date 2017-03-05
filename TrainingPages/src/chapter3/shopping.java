/*package chapter3;


 ID: calviny1
 LANG: JAVA
 TASK: shopping
 

import java.io.*;
import java.util.*;

public class shopping {
	
	private static int S;
	
	private static HashMap<Integer, Integer> idToItem = new HashMap<Integer, Integer>();
	
	private static int[] target = new int[5];
	
	private static Deal[] deals;
	
	private static class Deal {
		int[] amounts = new int[5];
		int cost = 0;
		
		public Deal(int[] arr, int cost) {
			amounts = arr;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in;
		PrintWriter out;
		boolean debug = true;
		if (debug) {
			in = new BufferedReader(new FileReader(new File("shopping.txt")));
			out = new PrintWriter(System.out);
		} else {
			in = new BufferedReader(new FileReader(new File("shopping.in")));
			out = new PrintWriter(new File("shopping.out"));
		}
		S = Integer.parseInt(in.readLine());
		deals = new Deal[S];
		for (int i = 0; i < S; i++) {
			deals[]
		}
	}
	
	
}
*/