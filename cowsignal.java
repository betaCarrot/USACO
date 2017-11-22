import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowsignal {
	private static int M, N, K;
	private static String[][] result;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowsignal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowsignal.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		result = new String[K * M][K * N];
		for (int i = 0; i < M; i++) {
			String line = f.readLine();
			for (int k = 0; k < N; k++) {
				String s = line.substring(k, k + 1);
				for (int row = K * i; row < K * (i + 1); row++) {
					for (int col = K * k; col < K * (k + 1); col++) {
						result[row][col] = s;
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.length; i++) {
			for (int k = 0; k < result[0].length; k++) {
				sb.append(result[i][k]);
			}
			sb.append("\n");
		}
		out.print(sb.toString());
		out.close();
	}
}
