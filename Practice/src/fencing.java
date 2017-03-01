import java.io.*;
import java.util.*;

public class fencing {
	
	private static int N, Q;

	private static TreeSet<long[]> cows = new TreeSet<long[]>(new Comparator<long[]> () {
		public int compare(long[] one, long[] two) {
			long x1 = one[0], x2 = two[0];
			if (x1 == x2) return (int)(one[1] - two[1]);
			return (int)(x1 - x2);
		}
	});
	private static ArrayList<long[]> hull = new ArrayList<long[]>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(new File("fencing.in")));
		PrintWriter out = new PrintWriter(new File("fencing.out"));
		StringTokenizer str = new StringTokenizer(in.readLine());
		N = Integer.parseInt(str.nextToken());
		Q = Integer.parseInt(str.nextToken());
		for (int i = 0; i < N; i++) {
			str = new StringTokenizer(in.readLine());
			long x = Long.parseLong(str.nextToken()), y = Long.parseLong(str.nextToken());
			long[] cow = {x, y};
			cows.add(cow);
		}
		convexHull();
		for (int i = 0; i < Q; i++) {
			str = new StringTokenizer(in.readLine());
			int queryType = Integer.parseInt(str.nextToken());
			if (queryType == 1) {
				long x = Long.parseLong(str.nextToken()), y = Long.parseLong(str.nextToken());
				long[] cow = {x, y};
				cows.add(cow);
				updateHull(cow);
			} else {
				long A = Long.parseLong(str.nextToken()), B = Long.parseLong(str.nextToken()), C = Long.parseLong(str.nextToken());
				for (int j = 0; j < hull.size() - 1; j++) {
					long[] pt1 = hull.get(j), pt2 = hull.get(j+1);
					if (intersects(pt1, pt2, A, B,C)) {
						out.println("NO");
						break;
					}
					if (j == hull.size() - 2) out.println("YES");
				}
			}
		}
		in.close();
		out.close();
		System.exit(0);
	}
	
	private static void convexHull() {
		long[] mostRecent = cows.first();
		do {
			hull.add(mostRecent);
			long[] nextPoint = mostRecent;
			for (long[] cow : cows) {
				if (Arrays.equals(cow, mostRecent)) continue;
				if (Arrays.equals(mostRecent, nextPoint) || leftOf(mostRecent, nextPoint, cow)) {
					nextPoint = cow;
				}
			}
			mostRecent = nextPoint;
		} while (!Arrays.equals(mostRecent, cows.first()));
	}
	
	private static void updateHull(long[] newPt) {
		for (int i = 0; i < hull.size() - 1; i++) {
			long[] pt1 = hull.get(i), pt2 = hull.get(i+1);
			if (leftOf(pt1, pt2, newPt)) {
				long[] nextPt = newPt;
				for (int j = 0; j < hull.size(); j++) {
					long[] pt3 = hull.get(j);
					if (Arrays.equals(nextPt, newPt) || leftOf(newPt, nextPt, pt3)) {
						nextPt = pt3;
					}
				}
				int newIndex = (i+2) % hull.size();
				hull.remove(newIndex);
				hull.add(newIndex, nextPt);
				hull.remove(i+1);
				hull.add(i+1, newPt);
				break;
			}
		}
	}
	
	private static boolean leftOf(long[] pt1, long[] pt2, long[] pt3) {
		long xDiff = pt1[0] - pt2[0], yDiff = pt1[1] - pt2[1];
		double slope = (double)yDiff / (double)xDiff;
		double value1 = (xDiff != 0) ? (double)pt1[1] - slope * (double)pt1[0] : pt1[0],
				value2 = (xDiff != 0) ? (double)pt3[1] - slope * (double)pt3[0] : pt3[0];
		if (pt1[0] < pt2[0]) {
			return value2 > value1;
		} else if (pt1[0] == pt2[0]) {
			if (pt1[1] < pt2[1]) return value2 < value1;
			return value2 > value1;
		}
		return value2 < value1;
	}
	
	private static boolean intersects(long[] pt1, long[] pt2, long A, long B, long C) {
		if (pt1[0] == pt2[0]) {
			long upBound = Math.min(pt1[1], pt2[1]), downBound = Math.max(pt1[1], pt2[1]);
			double y = (C - A * pt1[0]) / (double) B;
			return (!Double.isNaN(y)) ? y >= downBound && y <= upBound : pt1[0] == C / (double)A;
		}
		if (B == 0) {
			long leftBound = Math.min(pt1[0], pt2[0]), rightBound = Math.max(pt1[0], pt2[0]);
			double x = C / (double)A;
			return x >= leftBound && x <= rightBound;
		}
		double slope1 = (double)(pt1[1] - pt2[1])/(double)(pt1[0] - pt2[0]),
				slope2 = -A / (double)B;
		if (slope1 == slope2) return false;
		double value1 = pt1[1] - (pt1[0] * slope1), value2 = C / (double)B;
		double x = (value1 - value2) / (slope2 - slope1);
		long leftBound = Math.min(pt1[0], pt2[0]), rightBound = Math.max(pt1[0], pt2[0]);
		return x >= leftBound && x <= rightBound;
	}

}
