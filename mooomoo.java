import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class mooomoo {
	private static int N, B;
	private static final int INF = 1000000000;
	private static final int MAX = 100001;
	private static int[] breeds, dp, volumes;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("mooomoo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooomoo.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		breeds = new int[B];
		volumes = new int[N];
		dp = new int[MAX];
		for (int i = 0; i < B; i++) {
			breeds[i] = Integer.parseInt(f.readLine());
		}
		for (int i = 1; i < MAX; i++) {
			dp[i] = INF;
		}
		for (int i = 0; i < MAX; i++) {
			if (dp[i] == INF)
				continue;
			for (int k = 0; k < B; k++) {
				int next = i + breeds[k];
				if (next >= MAX)
					continue;
				dp[next] = Math.min(dp[next], 1 + dp[i]);
			}
		}
		for (int i = 0; i < N; i++) {
			volumes[i] = Integer.parseInt(f.readLine());
		}
		int index = N - 1;
		int result = 0;
		while (index >= 1) {
			int expected = Math.max(0, volumes[index - 1] - 1);
			result += dp[volumes[index] - expected];
			index--;
		}
		result += dp[volumes[0]];
		out.println(result);
		out.close();
	}
}
