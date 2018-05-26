
/*
ID: majesti2
LANG: JAVA
TASK: tour
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class tour {
	public static int N, V;
	public static boolean[][] connected;
	public static int[][] dp;
	public static HashMap<String, Integer> map = new HashMap<String, Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("tour.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tour.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		connected = new boolean[N][N];
		dp = new int[N][N];
		int curr = 0;
		while (curr < N) {
			map.put(f.readLine().trim(), curr);
			curr++;
		}
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(f.readLine());
			int from = map.get(st.nextToken());
			int to = map.get(st.nextToken());
			connected[from][to] = true;
			connected[to][from] = true;
		}
		dp[0][0] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				for (int k = 0; k < j; k++) {
					if (connected[k][j] && dp[i][k] > 0) {
						dp[j][i] = dp[i][j] = Math.max(dp[i][j], dp[i][k] + 1);
					}
				}
			}
		}
		int res = 1;
		for (int i = 0; i < N; i++) {
			if (connected[i][N - 1])
				res = Math.max(res, dp[i][N - 1]);
		}
		out.println(res);
		out.close();
	}
}
