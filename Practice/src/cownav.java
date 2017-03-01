import java.util.*;
import java.io.*;


public class cownav {
	
	private static int N;
	
	private static boolean grid[][];
	
	private static int[][] directions = {{0, 1}, {-1, 0}};

	private static class Path implements Comparable<Path> {
		String seq = "";
		int horizontal, row, col, diverts;
		
		public Path(int horizontal, int row, int col) {
			this.horizontal = horizontal;
			this.row = row;
			this.col = col;
			diverts = 0;
		}
		
		public Path(Path other) {
			this.seq = other.seq;
			this.horizontal = other.horizontal;
			this.row = other.row;
			this.col = other.col;
			this.diverts = other.diverts;
		}
		
		public int compareTo(Path that) {
			return this.seq.compareTo(that.seq);
		}
		
		public String toString() { return seq; };
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in;
		PrintWriter out;
		boolean debug = true;
		if (debug) {
			in = new BufferedReader(new FileReader(new File("cownav.txt")));
			out = new PrintWriter(System.out);
		} else {
			in = new BufferedReader(new FileReader(new File("cownav.in")));
			out = new PrintWriter(new File("cownav.out"));
		}
		
		N = Integer.parseInt(in.readLine());
		grid = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for (int j = 0; j < N; j++) {
				grid[i][j] = str.charAt(j) == 'H';
			}
		}
		in.close();
		Path path1 = shortestPath(new Path(0, N-1, 0)), path2 = shortestPath(new Path(1, N-1, 0));
		int ans = 0, ptr1 = 0, ptr2 = 0;
		int row1 = N-1, col1 = 0, row2 = N-1, col2 = 0;
		while (ptr1 < path1.seq.length() || ptr2 < path2.seq.length()) {
			
		}
		System.out.println(path1.diverts);
		
		out.close();
		System.exit(0);
	}
	
	private static Path shortestPath(Path start) {
		TreeSet<Path> toVisit = new TreeSet<Path>();
		toVisit.add(start);
		while (true) {
			Path path = toVisit.first();
			toVisit.remove(path);
			System.out.println(path);
			if (path.row == 0 && path.col == N - 1) return path;
			if (!inRange(complement(path))) path.diverts++;
			// Forward
			if (inRange(path)) {
				Path newPath = new Path(path);
				newPath.row += directions[path.horizontal][0];
				newPath.col += directions[path.horizontal][1];
				newPath.seq += "F";
				toVisit.add(newPath);
			}
			// Turn
			if (path.seq.length() == 0 || path.seq.charAt(path.seq.length() - 1) == 'F') {
				Path newPath = new Path(path);
				newPath.horizontal = 1 - path.horizontal;
				newPath.seq += (path.horizontal == 0) ? "L" : "R";
				toVisit.add(newPath);
			}
		}
	}
	
	private static boolean inRange(Path path) {
		int row = path.row, col = path.col, horizontal = path.horizontal;
		int newRow = row + directions[horizontal][0], newCol = col + directions[horizontal][1];
		return newRow >= 0 && newRow < N && newCol >= 0 && newCol < N && !grid[newRow][newCol];
	}
	
	private static Path complement(Path path) {
		Path retval = new Path(path);
		retval.row = N - path.col - 1;
		retval.col = N - path.row - 1;
		return retval;
	}

}
