
/*
 ID: majesti2
 LANG: JAVA
 TASK: ratios
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ratios {
	private static final int N = 3;
	private static int[] goal;
	private static int[][] feeds;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ratios.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
		goal = new int[N];
		feeds = new int[N][N];
		int result = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < 3; i++) {
			goal[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(f.readLine());
			for (int k = 0; k < 3; k++) {
				feeds[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		int minA = -1;
		int minB = -1;
		int minC = -1;
		int minR = -1;
		for (int a = 0; a < 100; a++) {
			for (int b = 0; b < 100; b++) {
				for (int c = 0; c < 100; c++) {
					int[][] newFeeds = new int[N][N];
					for (int k = 0; k < N; k++) {
						newFeeds[0][k] = a * feeds[0][k];
					}
					for (int k = 0; k < N; k++) {
						newFeeds[1][k] = b * feeds[1][k];
					}
					for (int k = 0; k < N; k++) {
						newFeeds[2][k] = c * feeds[2][k];
					}
					int[] combined = new int[N];
					for (int k = 0; k < N; k++) {
						int sum = 0;
						for (int i = 0; i < N; i++) {
							sum += newFeeds[i][k];
						}
						combined[k] = sum;
					}
					int ratio = check(combined);
					if (ratio == -1)
						continue;
					int sum = a + b + c;
					if (sum < result) {
						minA = a;
						minB = b;
						minC = c;
						minR = ratio;
						result = sum;
					}
				}
			}
		}
		if (minR != -1)
			out.println(minA + " " + minB + " " + minC + " " + minR);
		else
			out.println("NONE");
		out.close();
	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static int check(int[] combined) {
		for (int ratio = 1; ratio < 100; ratio++) {
			boolean valid = true;
			for (int i = 0; i < 3; i++) {
				if (goal[i] * ratio != combined[i]) {
					valid = false;
				}
			}
			if (valid)
				return ratio;
		}
		return -1;
	}
}
