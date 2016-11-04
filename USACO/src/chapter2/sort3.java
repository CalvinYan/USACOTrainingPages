package chapter2;
/*
 ID: calviny1
 LANG: JAVA
 TASK: sort3
 */

import java.io.*;
import java.util.*;

public class sort3 {

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("sort3.in"));
		PrintWriter out = new PrintWriter(new File("sort3.out"));
		int N = in.nextInt();
		int[] arr = new int[N], firstIndexOf = {-1, -1, -1};
		for (int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		in.close();
		int[] sorted = Arrays.copyOf(arr, N);
		Arrays.sort(sorted);
		for (int a = 0; a < N; a++) {
			if (firstIndexOf[sorted[a] - 1] == -1) {
				firstIndexOf[sorted[a] - 1] = a;
			}
		}
		int ans = 0;
		for (int j = 0; j < N; j++) {
			int num = arr[j], target = sorted[j];
			if (num != target) {
				int toSwap = -1;
				for (int k = firstIndexOf[target]; k < N; k++) {
					int kNum = arr[k];
					if (kNum == target) {
						if (toSwap == -1) {
							toSwap = k;
						}
						if (num == sorted[k]) {
							toSwap = k;
							break;
						}
					}
				}
				swap(arr, j, toSwap);
				ans++;
			}
		}
		out.println(ans);
		out.close();
		System.exit(0);
	}

}
