import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class checklist {
	private static int H, G;
	private static cow[] hs;
	private static cow[] gs;
	private static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("checklist.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		H = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		hs = new cow[H];
		gs = new cow[G];
		dp = new int[H + 1][G + 1][2];
		for (int i = 0; i <= H; i++) {
			for (int j = 0; j <= G; j++) {
				for (int k = 0; k < 2; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			hs[i] = new cow(x, y);
		}
		for (int i = 0; i < G; i++) {
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			gs[i] = new cow(x, y);
		}
		out.println(dfs(0, -1, 0));
		out.close();
	}

	public static int dfs(int hIndex, int gIndex, int pos) {
		if (hIndex == H - 1 && gIndex == G - 1) {
			return 0;
		}
		if (hIndex == H - 1 && gIndex != G - 1) {
			return 2000000001;
		}
		if (dp[hIndex][gIndex + 1][pos] != -1) {
			return dp[hIndex][gIndex + 1][pos];
		}
		int result = Integer.MAX_VALUE;
		if (hIndex != H - 1) {
			if (pos == 0) {
				result = Math.min(result, distance(hs[hIndex], hs[hIndex + 1]) + dfs(hIndex + 1, gIndex, 0));
			} else {
				result = Math.min(result, distance(gs[gIndex], hs[hIndex + 1]) + dfs(hIndex + 1, gIndex, 0));
			}
		}
		if (gIndex != G - 1) {
			if (pos == 0) {
				result = Math.min(result, distance(hs[hIndex], gs[gIndex + 1]) + dfs(hIndex, gIndex + 1, 1));
			} else {
				result = Math.min(result, distance(gs[gIndex], gs[gIndex + 1]) + dfs(hIndex, gIndex + 1, 1));
			}
		}
		dp[hIndex][gIndex + 1][pos] = result;
		return result;
	}

	public static int distance(cow cow1, cow cow2) {
		return (cow1.x - cow2.x) * (cow1.x - cow2.x) + (cow1.y - cow2.y) * (cow1.y - cow2.y);
	}
}

class cow {
	public int x, y;

	public cow(int a, int b) {
		x = a;
		y = b;
	}
}
