package chapter3;

/*
 ID: calviny1
 LANG: JAVA
 TASK: msquare
 */

import java.io.*;
import java.util.*;

public class msquare {

	private static String target = "", board = "12345678";
	
	private static int[][] transforms = {{7,6,5,4,3,2,1,0}, {3,0,1,2,5,6,7,4}, {0,6,1,3,4,2,5,7}};
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("msquare.in"));
		PrintWriter out = new PrintWriter(new File("msquare.out"));
		
		target = in.nextLine().replaceAll("\\s", "");
		in.close();

		String ans = solve();
		out.println(ans.length());
		for (int i = 0; i < ans.length(); i++) {
			out.print(ans.charAt(i));
			if (i % 60 == 59) out.println();
		}
		out.println();
		out.close();
		System.exit(0);
	}
	
	
	private static String solve() {
		HashSet<String> boardSet = new HashSet<String>();
		StringBuilder[] sequences = {new StringBuilder()};
		String[] boards = {"12345678"};
		int size = 1;
		while (true) {
			int newSize = 0;
			StringBuilder[] newSequences = new StringBuilder[size * 3];
			String[] newBoards = new String[size * 3];
			int count = 0;
			for (int i = 0; count < size; i++) {
				board = boards[i];
				if (board != null) {
					
					if (board.equals(target)) return sequences[i].toString();
					
					for (int j = 0; j < 3; j++) {
						StringBuilder seq = new StringBuilder(sequences[i]);
						String newBoard = transform(board, j);
						if (!boardSet.contains(newBoard)) {
							seq.append((char)(65+j));
							boardSet.add(newBoard);
							newBoards[count*3+j] = newBoard;
							newSequences[count*3+j] = seq;
							newSize++;
						}
					}
					count++;
				}
				
			}
			sequences = newSequences;
			boards = newBoards;
			size = newSize;
		}
//		PriorityQueue<String>
//		StringBuilder current = new StringBuilder();
//		while (!onTarget(board)) {
//			
//		}
	}
	
	private static boolean onTarget(String board) {
		return board.equals(target);
	}
	
//	private static int manhattan(int[][] board) {
////		System.out.println("manhattan");
//		int[][] locations = new int[9][2];
//		for (int i = 0; i < 2; i++) {
//			for (int j = 0; j < 4; j++) {
//				locations[target[i][j]] = new int[]{i, j};
//			}
//		}
//		int count = 0;
//		for (int i = 0; i < 2; i++) {
//			for (int j = 0; j < 4; j++) {
//				int value = board[i][j];
//				if (board[i][j] != target[i][j]) {
//					count += Math.abs(i - locations[value][0]) + Math.abs(j - locations[value][1]);
//				}
//			}
//		}
//		return count;
//	}
	
	private static String transform(String board, int type) {
		int[] transform = transforms[type];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			sb.append(board.charAt(transform[i]));
		}
		return sb.toString();
	}

}
