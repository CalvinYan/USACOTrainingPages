import java.io.*;
import java.util.*;

public class cbarn {

	private static int N;
	
	private static int[] rooms;
	
	private static TreeSet<Integer> roomTree = new TreeSet<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in;
		PrintWriter out;
		boolean debug = false;
		if (debug) {
			in = new BufferedReader(new FileReader(new File("./input/cbarn.in")));
			out = new PrintWriter(System.out);
		} else {
			in = new BufferedReader(new FileReader(new File("cbarn.in")));
			out = new PrintWriter(new File("cbarn.out"));
		}
		N = Integer.parseInt(in.readLine());
		rooms = new int[N];
		for (int i = 0; i < N; i++) {
			int count = Integer.parseInt(in.readLine());
			rooms[i] = count;
			if (count > 0) roomTree.add(i);
		}
		in.close();
		int ans = 0;
		for (int i = N - 1; roomTree.size() < N; i = (i - 1 + N) % N) {
			System.out.println("I:" + i);
			if (rooms[i] == 0) {
				int nextIndex = nextIndex(i), diff = circularDistance(i, nextIndex);
				ans += diff * diff;
				roomTree.add(i);
				if (--rooms[nextIndex] == 0) {
					roomTree.remove(nextIndex);
				}				
			}
		}
		out.println(ans);
		out.close();
		System.exit(0);
	}
	
	private static int nextIndex(int index) {
		Integer next = roomTree.floor(index);
		if (next == null) return roomTree.last();
		return next;
	}
	
	private static int circularDistance(int a, int b) {
		int diff = a - b;
		if (diff < 0) diff += N;
		return diff;
	}

}
