import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class diamond {
	private static int[] array;
	private static int[] maxs;
	private static int[] dp;
	private static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("diamond.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		array = new int[N];
		maxs = new int[N];
		dp = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(f.readLine()) * 2;
		}
		Arrays.sort(array);
		for (int i = 0; i < N; i++) {
			int next = array[i] + 2 * K + 1;
			int nextIndex = -Arrays.binarySearch(array, next) - 1;
			maxs[i] = nextIndex - i;
		}
		for (int i = N - 2; i >= 0; i--) {
			dp[i] = Math.max(dp[i + 1], maxs[i]);
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			int first = maxs[i];
			int secondIndex = i + maxs[i];
			if (secondIndex >= N)
				break;
			result = Math.max(result, first + dp[secondIndex]);
		}
		out.println(result);
		out.close();
	}

	public static void printArray(int[] array) {
		for (int i : array)
			System.out.print(i + " ");
		System.out.println();
	}
}
