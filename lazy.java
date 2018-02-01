import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class lazy {
	private static int N, K;
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lazy.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lazy.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int total = 0;
		map = new int[N * 2 - 1][N * 2 - 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			for (int k = 0; k < N; k++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i + k][N - i + k - 1] = temp;
				total += temp;
			}
		}
		N = N * 2 - 1;
		int result = 0;
		for (int i = K; i + K < N; i++) {
			int sum = 0;
			for (int a = i - K; a <= i + K; a++) {
				for (int b = 0; b <= 2 * K; b++) {
					sum += map[a][b];
				}
			}
			result = Math.max(result, sum);
			for (int j = 1; j + 2 * K < N; j++) {
				for (int a = i - K; a <= i + K; a++) {
					sum -= map[a][j - 1];
					sum += map[a][j + 2 * K];
				}
				result = Math.max(result, sum);
			}
		}
		if (result == 0) {
			result = total;
		}
		out.println(result);
		out.close();
	}
}
