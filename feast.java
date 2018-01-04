import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class feast {
	private static int N, A, B, result;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("feast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		boolean[] dp1 = new boolean[N + 1];
		boolean[] dp2 = new boolean[N + 1];
		dp1[0] = true;
		for (int i = 0; i <= N; i++) {
			if (!dp1[i])
				continue;
			result = Math.max(result, i);
			dp2[i / 2] = true;
			if (i + A <= N) {
				dp1[i + A] = true;
			}
			if (i + B <= N) {
				dp1[i + B] = true;
			}
		}
		for (int i = 0; i <= N; i++) {
			if (!dp2[i])
				continue;
			result = Math.max(result, i);
			if (i + A <= N) {
				dp2[i + A] = true;
			}
			if (i + B <= N) {
				dp2[i + B] = true;
			}
		}
		out.println(result);
		out.close();
	}
}
