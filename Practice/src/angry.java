import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class angry {

	private static TreeSet<Double> baleSet = new TreeSet<Double>();
	
	private static HashMap<Double, Double> leftMap = new HashMap<Double, Double>(),
			rightMap = new HashMap<Double, Double>();
	
	private static double[] leftReq, rightReq;
	
	public static void main(String[] args) throws IOException {
		boolean debug = true;
		BufferedReader in;
		PrintWriter out;
		if (debug) {
			in = new BufferedReader(new FileReader(new File("./input/angry.in")));
			out = new PrintWriter(System.out);
		} else {
			in = new BufferedReader(new FileReader(new File("angry.in")));
			out = new PrintWriter(new File("angry.out"));
		}
		StringTokenizer str = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(str.nextToken());

		Double[] bales;
		
		leftReq = new double[N];
		rightReq = new double[N];

		for (int i = 0; i < N; i++) {
			str = new StringTokenizer(in.readLine());
			double bale = Double.parseDouble(str.nextToken());
			baleSet.add(bale);
		}
		bales = baleSet.toArray(new Double[0]);
		double maxGap = 0;
		for (int i = 0; i < N; i++) {
			if (i > 0) maxGap = Math.max(bales[i] - bales[i-1], maxGap);
		}
		leftMap.put(baleSet.first(), 0.0);
		rightMap.put(baleSet.last(), 0.0);
		for (int i = 1; i < N; i++) {
			leftReq[i] = Math.max(leftReq[i-1] + 1, bales[i] - bales[i-1] + 1);
			rightReq[N - i - 1] = Math.max(rightReq[N - i] + 1, bales[N - i] - bales[N - i - 1] + 1);
			leftMap.put(bales[i], leftReq[i]);
			rightMap.put(bales[N - i - 1], rightReq[N - i - 1]);
			
		}
		for (int i = 1; i < N; i++) {
			if (valid(leftReq[i] - 1, bales[i] + leftReq[i] - 1) != -1) System.out.println("Dammit " + i);
		}
		in.close();
		int minPower = 0,
				maxPower = (int)((bales[N-1] - bales[0]) / 2.0 * 10),
				currentPower, ans = 0;
		
		while (true) {
			currentPower = (minPower + maxPower) / 2;
			if (valid(currentPower / 10.0)) {
				ans = currentPower;
				maxPower = currentPower - 1;
			} else {
				minPower = currentPower + 1;
			}
			if (minPower > maxPower) break;
		}
		out.println(ans / 10.0);
		out.close();
		System.exit(0);
	}
	
	private static boolean valid(double power) {
		double min = baleSet.first() * 10, max = baleSet.last() * 10, current;
		while (min < max) {
			current = (long)((min + max) / 2.0);
			int value = valid(power, current / 10.0);
			if (value == 1) {
				min = current + 1;
			}
			if (value == 0) {
				return true;
			}
			if (value == -1) {
				max = current - 1;
			}
		}
		return false;
	}
	
	private static int valid(double power, double start) {
		//System.out.println(power + " " + start);
		Double leftStart = baleSet.ceiling(start - power), rightStart = baleSet.floor(start + power);
		if (leftStart > start) return -1;
		if (rightStart < start) return 1;
		if (power < leftMap.get(leftStart)) return -1;
		if (power < rightMap.get(rightStart)) return 1;
		
		/*Double leftStart = start, rightStart = start;
		Double leftPower = power, rightPower = power;*/
		
		/*while (leftStart != null || rightStart != null) {
			if (leftStart != null) {
				if (leftStart - leftPower <= baleSet.first()) {
					leftStart = null;
				} else {
					Double nextLeft = baleSet.ceiling(leftStart - leftPower);
					if (nextLeft >= leftStart) return -1;
					leftStart = nextLeft;
					leftPower--;
				}
			}
			if (rightStart != null) {
				if (rightStart + rightPower >= baleSet.last()) {
					rightStart = null;
				} else {
					Double nextRight = baleSet.floor(rightStart + rightPower);
					if (nextRight <= rightStart) return 1;
					rightStart = nextRight;
					rightPower--;
				}
			}
		}*/
		return 0;
	}
}
