import java.io.*;
import java.util.*;

public class hopscotch {

	private static int R, C, K;
	
	private static int[][] grid;
	
	private static HashMap<String, Integer> paths = new  HashMap<String, Integer>();
	
	private static long[][] dp, sum;
	
	private static long[][][] frequencies;
	
	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(new File("1.in"));
		PrintWriter out = new PrintWriter(System.out);
		R = in.nextInt();
		C = in.nextInt();
		K = in.nextInt();
		grid = new int[R][C];
		dp = new long[R][C];
		sum = new long[R][C];
		frequencies = new long[R][C][K+1];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int cell = in.nextInt();
				grid[i][j] = cell;
			}
		}
		in.close();
		out.println(solve());
		out.close();
		System.exit(0);
	}
	
	private static long solve() {
		for (int i = R - 2; i > 0; i--) {
			for (int j = C - 2; j > 0; j--) {
				if (i == 0 ^ j == 0) continue;
				long ans = 0, newSum = 0;
				for (int k = 1; k <= K; k++) {
					long newFreq = 0;
					newFreq += frequencies[i+1][j][k];
					newFreq += frequencies[i][j+1][k];
					newFreq -= frequencies[i+1][j+1][k];
					newFreq %= 1000000007;
					frequencies[i][j][k] += newFreq;
				}
				newSum += sum[i+1][j];
				newSum += sum[i][j+1];
				newSum -= sum[i+1][j+1];
				newSum %= 1000000007;
				if (grid[i][j] != grid[R-1][C-1]) {
					newSum++;
					frequencies[i][j][grid[i][j]]++;
				}
				if (i < R - 2 && j < C - 2) {
					ans = differenceMod(newSum, frequencies[i+1][j+1][grid[i][j]]);
				}
				dp[i][j] = ans;
				frequencies[i][j][grid[i][j]] = (frequencies[i][j][grid[i][j]] + ans) % 1000000007;
				/*long checkSum = 0;
				for (int a = 1; a <= K; a++) {
					checkSum += frequencies[i+1][j+1][a];
					checkSum %= 1000000007;
				}
				if (newSum != checkSum) {
					System.out.println(i + " " + j + " I'll be damned");
				}
				if (grid[i][j] != grid[R-1][C-1]) ans++;
				for (int c = j; c < C - 1; c++) {
					int value = grid[i][c];
					frequencies[i][j][value] += dp[i][c];
					frequencies[i][j][value] %= 1000000007;
					newSum += dp[i][c];
					newSum %= 1000000007;
				}
				for (int r = i + 1; r < R - 1; r++) {
					int value = grid[r][j];
					frequencies[i][j][value] += dp[r][j];
					frequencies[i][j][value] %= 1000000007;
					newSum += dp[r][j];
					newSum %= 1000000007;
				}	*/
				sum[i][j] = newSum;
			}
		}
		long retval = sum[1][1] - frequencies[1][1][grid[0][0]];
		if (grid[0][0] != grid[R-1][C-1]) retval++;
		return retval;
	}
	
	private static long differenceMod(long a, long b) {
		if (a < b) a += 1000000007;
		return a - b;
	}
}
