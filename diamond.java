import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class diamond {
	private static int[] values;
	private static int[] dp;
	private static int N, K;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("diamond.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()) * 2;
		values = new int[N];
		dp = new int[N];
		for (int i = 0; i < N; i++) {
			values[i] = Integer.parseInt(f.readLine()) * 2;
		}
		Arrays.sort(values);
		for (int first = 0; first < N; first++) {
			int second = -Arrays.binarySearch(values, values[first] + K + 1) - 1;
			dp[first] = second;
		}
		for (int first = 0; first < N; first++) {
			int second = dp[first];
			int length1 = second - first;
			for (int third = second; third < N; third++) {
				int fourth = dp[third];
				result = Math.max(result, fourth - third + length1);
			}
		}
		out.println(result);
		out.close();
	}
}