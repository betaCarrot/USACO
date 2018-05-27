
/*
ID: majesti2
LANG: JAVA
TASK: twofive
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class twofive {
	public static long[][][][][] dp = new long[6][6][6][6][6];
	public static int[] prefix = new int[25];
	public static boolean[] used = new boolean[25];

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("twofive.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("twofive.out")));
		if (f.readLine().equals("N")) {
			long target = Long.parseLong(f.readLine());
			long count = 0;
			for (int i = 0; i < 25; i++) {
				for (int k = 0; k < 25; k++) {
					if (!used[k]) {
						prefix[i] = k;
						used[k] = true;
						init();
						long num = dfs(0, 0, 0, 0, 0, 0);
						if (count + num >= target) {
							break;
						}
						used[k] = false;
						count += num;
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int i : prefix) {
				sb.append((char) (i + 'A'));
			}
			out.println(sb.toString());
		} else {
			char[] chars = f.readLine().toCharArray();
			long count = 0;
			for (int i = 0; i < 25; i++) {
				int index = chars[i] - 'A';
				for (int k = 1; k < index; k++) {
					if (!used[k]) {
						prefix[i] = k;
						init();
						count += dfs(0, 0, 0, 0, 0, 0);
					}
				}
				prefix[i] = index;
				used[index] = true;
			}
			out.println(count + 1);
		}
		out.close();
	}

	public static long dfs(int a, int b, int c, int d, int e, int curr) {
		if (dp[a][b][c][d][e] != -1)
			return dp[a][b][c][d][e];
		if (curr == 25)
			return 1;
		long res = 0;
		if (a < 5 && (prefix[a] == curr || prefix[a] == 0)) {
			res += dfs(a + 1, b, c, d, e, curr + 1);
		}
		if (b < a && (prefix[b + 5] == curr | prefix[b + 5] == 0)) {
			res += dfs(a, b + 1, c, d, e, curr + 1);
		}
		if (c < b && (prefix[c + 10] == curr | prefix[c + 10] == 0)) {
			res += dfs(a, b, c + 1, d, e, curr + 1);
		}
		if (d < c && (prefix[d + 15] == curr | prefix[d + 15] == 0)) {
			res += dfs(a, b, c, d + 1, e, curr + 1);
		}
		if (e < d && (prefix[e + 20] == curr | prefix[e + 20] == 0)) {
			res += dfs(a, b, c, d, e + 1, curr + 1);
		}
		dp[a][b][c][d][e] = res;
		return res;
	}

	public static void init() {
		for (int a = 0; a < 6; a++) {
			for (int b = 0; b < 6; b++) {
				for (int c = 0; c < 6; c++) {
					for (int d = 0; d < 6; d++) {
						for (int e = 0; e < 6; e++) {
							dp[a][b][c][d][e] = -1;
						}
					}
				}
			}
		}
	}
}
