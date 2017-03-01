import java.io.*;
import java.util.*;

public class radio {

	private static int[][] johnPath, bessiePath;
	
	private static int N, B;
	
	public static void main(String[] args) throws IOException {
		boolean debug = false;
		BufferedReader in;
		PrintWriter out;
		
		if (debug) {
			in = new BufferedReader(new FileReader(new File("./input/radio.in")));
			out = new PrintWriter(System.out);
		} else {
			in = new BufferedReader(new FileReader(new File("radio.in")));
			out = new PrintWriter(new File("radio.out"));
		}
		StringTokenizer str = new StringTokenizer(in.readLine());
		N = Integer.parseInt(str.nextToken());
		B = Integer.parseInt(str.nextToken());
		johnPath = new int[N + 1][2];
		bessiePath = new int[B + 1][2];
		str = new StringTokenizer(in.readLine());
		
		johnPath[0][0] = Integer.parseInt(str.nextToken());
		johnPath[0][1] = Integer.parseInt(str.nextToken());
		
		str = new StringTokenizer(in.readLine());
		bessiePath[0][0] = Integer.parseInt(str.nextToken());
		bessiePath[0][1] = Integer.parseInt(str.nextToken());
		
		int currentX = johnPath[0][0], currentY = johnPath[0][1];
		String path = in.readLine();
		
		for (int i = 0; i < N; i++) {
			char step = path.charAt(i);
			switch (step) {
			case 'N':
				johnPath[i+1] = new int[]{currentX, ++currentY};
				break;
			case 'S':
				johnPath[i+1] = new int[]{currentX, --currentY};
				break;
			case 'E':
				johnPath[i+1] = new int[]{++currentX, currentY};
				break;
			case 'W':
				johnPath[i+1] = new int[]{--currentX, currentY};
				break;
			}
		}
		
		currentX = bessiePath[0][0];
		currentY = bessiePath[0][1];
		path = in.readLine();
		for (int i = 0; i < B; i++) {
			char step = path.charAt(i);
			switch (step) {
			case 'N':
				bessiePath[i+1] = new int[]{currentX, ++currentY};
				break;
			case 'S':
				bessiePath[i+1] = new int[]{currentX, --currentY};
				break;
			case 'E':
				bessiePath[i+1] = new int[]{++currentX, currentY};
				break;
			case 'W':
				bessiePath[i+1] = new int[]{--currentX, currentY};
				break;
			}
		}
		
		in.close();
		out.println(solve());
		out.close();
		System.exit(0);
	}
	
	private static int solve() {
		int[][] dp = new int[N+1][B+1];
		dp[N][B] = 0;
		for (int i = N; i >= 0; i--) {
			for (int j = B; j >= 0; j--) {
				if (i == N && j == B) continue;
				int[] johnPos = johnPath[i], bessiePos = bessiePath[j];
				int energy = Integer.MAX_VALUE, newEnergy;
				if (j < B) {
					newEnergy = energy(johnPos, bessiePath[j+1]) + dp[i][j+1];
					if (newEnergy < energy)
					energy = newEnergy;
				}
				if (i < N) {
					newEnergy = energy(johnPath[i+1], bessiePos) + dp[i+1][j];
					if (newEnergy < energy)
					energy = newEnergy;
				}
				if (j < B && i < N) {
					newEnergy = energy(johnPath[i+1], bessiePath[j+1]) + dp[i+1][j+1];
					if (newEnergy < energy)
					energy = newEnergy;
				}
				dp[i][j] += energy;
			}
		}
		return dp[0][0];
	}
	
	private static int energy(int[] pt1, int[] pt2) {
		int xDiff = pt1[0] - pt2[0], yDiff = pt1[1] - pt2[1];
		return xDiff * xDiff + yDiff * yDiff;
	}

}
