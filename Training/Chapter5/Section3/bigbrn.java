
/*
ID: majesti2
LANG: JAVA
TASK:bigbrn
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bigbrn {
	public static int N, T;
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("bigbrn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bigbrn.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 1);
		}
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = 0;
		}
		int res = 0;
		if (N * N > T) {
			res = 1;
		}
		for (int i = 1; i < N; i++) {
			for (int k = 1; k < N; k++) {
				if (map[i][k] != 0) {
					map[i][k] = Math.min(map[i - 1][k - 1], Math.min(map[i - 1][k], map[i][k - 1])) + 1;
					res = Math.max(res, map[i][k]);
				}
			}
		}
		out.println(res);
		out.close();
	}
}
