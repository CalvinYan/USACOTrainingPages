import java.io.*;
import java.util.*;

public class lightsout {
	
	private static String map;
	
	private static int[] distance, angles, edges;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(new File("lightsout.in")));
		PrintWriter out = new PrintWriter(new File("lightsout.out"));
		
		StringTokenizer str = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(str.nextToken());
		
		distance = new int[N];
		angles = new int[N];
		edges = new int[N];
		int[] distTo = new int[N], previous, first;
		
		StringBuilder mapBuilder = new StringBuilder();
		int sum = 0;
		
		str = new StringTokenizer(in.readLine());
		int x = Integer.parseInt(str.nextToken()), y = Integer.parseInt(str.nextToken());
		first = new int[]{x, y};
		previous = first;
		boolean leftDown = false;
		for (int i = 1; i < N; i++) {
			str = new StringTokenizer(in.readLine());
			x = Integer.parseInt(str.nextToken());
			y = Integer.parseInt(str.nextToken());
			int dist = x - previous[0] + y - previous[1];
			if (i > 1) {
				int angle = 0;
				if (dist < 0) {
					if (leftDown) {
						angle = (x == previous[0]) ? 270 : 90;
					} else {
						angle = (x == previous[0]) ? 90 : 270;
					}
				} else {
					if (leftDown) {
						angle = (x == previous[0]) ? 90 : 270;
					} else {
						angle = (x == previous[0]) ? 270 : 90;
					}		
				}
				mapBuilder.append(angle);
				angles[i-1] = angle;
			}
			leftDown = dist < 0;
			dist = Math.abs(dist);
			sum += dist;
			edges[i-1] = dist;
			mapBuilder.append(dist);
			distTo[i] = sum;
			if (i == N - 1) {
				dist = Math.abs(x - first[0] + y - first[1]);
				sum += dist;
				mapBuilder.append("90" + dist);
				edges[i] = dist;
				angles[i] = 90;
			}
			previous = new int[]{x, y};
		}
		in.close();
		map = mapBuilder.toString();
		for (int i = 0; i < N; i++) {
			distance[i] = Math.min(distTo[i], sum - distTo[i]);
		}
		int ans = 0;
		for (int i = 1; i < N; i++) {
			int j = i;
			int optimalDist = distance[i], currentDist = 0;
			String path = Integer.toString(angles[i]);
			while (map.indexOf(path) != map.lastIndexOf(path) && j != 0) {
				path += edges[j];
				currentDist += edges[j];
				j = (j+1) % N;
				path += angles[j];
			}
			int cost = distance[j] + currentDist - optimalDist;
			ans = Math.max(cost, ans);
		}
		out.println(ans);
		out.close();
		System.exit(0);
	}

}
