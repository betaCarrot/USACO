import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class div7 {
	private static int[][] indexes = new int[7][2];
	private static int[] counts = new int[7];
	private static int[] numbers;
	private static int[] dp;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("div7.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
		N = Integer.parseInt(f.readLine());
		numbers = new int[N];
		dp = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(f.readLine());
		}
		for (int i = 0; i < N; i++) {
			if (i == 0)
				dp[i] = numbers[i] % 7;
			else
				dp[i] = (dp[i - 1] + numbers[i]) % 7;
			int index = dp[i];
			if (index == 0) {
				if (counts[index] == 0) {
					counts[index] = 2;
					indexes[0][0] = i;
					indexes[0][1] = i;
				} else {
					indexes[0][1] = i;
				}
			} else {
				if (counts[index] == 0) {
					counts[index]++;
					indexes[index][0] = i;
				} else if (counts[index] == 1) {
					counts[index]++;
					indexes[index][1] = i;
				} else {
					indexes[index][1] = i;
				}
			}
		}
		int result = 0;
		for (int i = 0; i < 7; i++) {
			if (counts[i] < 2)
				continue;
			result = Math.max(result, indexes[i][1] - indexes[i][0]);
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
