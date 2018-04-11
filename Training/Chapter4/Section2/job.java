
/*
ID: majesti2
LANG: JAVA
TASK: job
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class job {
	public static int N, M1, M2;
	public static int[] machineA, machineB, finish;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("job.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("job.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M1 = Integer.parseInt(st.nextToken());
		M2 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		machineA = new int[M1];
		machineB = new int[M2];
		finish = new int[N];
		for (int i = 0; i < M1; i++) {
			machineA[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < M2; i++) {
			machineB[i] = Integer.parseInt(st.nextToken());
		}
		int res1 = binarySearch1();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i : machineA) {
			int curr = 1;
			while (i * curr <= res1) {
				list.add(i * curr);
				curr++;
			}
		}
		Collections.sort(list);
		for (int i = 0; i < N; i++) {
			finish[i] = list.get(i);
		}
		int res2 = binarySearch2();
		out.println(res1 + " " + res2);
		out.close();
	}

	public static int binarySearch1() {
		int low = 0;
		int high = 100000;
		while (low != high) {
			int mid = (low + high) / 2;
			if (solve1(mid)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	public static int binarySearch2() {
		int low = 0;
		int high = 100000;
		while (low != high) {
			int mid = (low + high) / 2;
			if (solve2(mid)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	public static boolean solve1(int end) {
		int[] status = new int[M1];
		Arrays.fill(status, end);
		for (int iter = 0; iter < N; iter++) {
			for (int i = 0; i < M1; i++) {
				if (machineA[i] > status[i]) {
					if (i == M1 - 1) {
						return false;
					}
					continue;
				}
				status[i] -= machineA[i];
				break;
			}
		}
		return true;
	}

	public static boolean solve2(int end) {
		int[] status = new int[M2];
		Arrays.fill(status, end);
		for (int iter = N - 1; iter >= 0; iter--) {
			int t = finish[iter];
			for (int i = 0; i < M2; i++) {
				if (t + machineB[i] > status[i]) {
					if (i == M2 - 1)
						return false;
					continue;
				}
				status[i] -= machineB[i];
				break;
			}
		}
		return true;
	}
}
