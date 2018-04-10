
/*
ID: majesti2
LANG: JAVA
TASK: nuggets
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class nuggets {
	private static int N;
	private static int MAX = 20000000;
	private static int[] array;
	private static boolean[] dp = new boolean[MAX + 1];

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("nuggets.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));
		N = Integer.parseInt(f.readLine());
		array = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(f.readLine());
		}
		dp[0] = true;
		for (int i = 0; i <= MAX; i++) {
			if (dp[i]) {
				for (int k : array) {
					if (i + k <= MAX)
						dp[i + k] = true;
				}
			}
		}
		int res = 0;
		for (int i = MAX; i >= 0; i--) {
			if (!dp[i]) {
				res = i;
				break;
			}
		}
		if (res >= MAX / 2)
			res = 0;
		out.println(res);
		out.close();
	}
}
