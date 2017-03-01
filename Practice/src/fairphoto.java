import java.io.*;
import java.util.*;

public class fairphoto {

	public static void main(String[] args) throws IOException {
		BufferedReader in;
		PrintWriter out;
		boolean debug = true;
		if (debug) {
			in = new BufferedReader(new FileReader(new File("input/fairphoto.in")));
			out = new PrintWriter(System.out);
		} else {
			in = new BufferedReader(new FileReader(new File("fairphoto.in")));
			out = new PrintWriter(new File("fairphoto.out"));
		}
		int N = Integer.parseInt(in.readLine());
		int[] cows = new int[N], spotsFromStart = new int[N+1];
		boolean[] spotted = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer str = new StringTokenizer(in.readLine());
			cows[i] = Integer.parseInt(str.nextToken());
			String s = str.nextToken();
			spotted[i] = s.equals("S");
		}
		Arrays.sort(cows);
		for (int i = 0; i < N; i++) {
			spotsFromStart[i+1] = spotsFromStart[i];
			if (spotted[i]) spotsFromStart[i+1]++;
		}
		
		System.out.println(Arrays.toString(spotsFromStart));
		
		int ans = 0;
		
		for (int len = N - N % 2; len >= 2; len -= 2) {
			for (int start = 0; start + len <= N; start++) {
				int end = start + len, spots = spotsFromStart[end] - spotsFromStart[start];
				if (spots <= (end - start) / 2) {
					System.out.println();
				}
			}
		}
	}

}
