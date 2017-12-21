import java.io.BufferedReader;
/*
ID: majesti2
LANG: JAVA
TASK: fact4
*/
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class fact4 {
	private static int[] dp;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fact4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		N = Integer.parseInt(f.readLine());
		dp = new int[N + 1];
		dp[0] = 1;
		for (int i = 1; i <= N; i++) {
			int next = dp[i - 1] * i;
			dp[i] = lastThreeDigits(next);
		}
		/*
		 * long[] check = new long[N + 1]; check[0] = 1L; for (int i = 1; i <= N; i++) {
		 * check[i] = i * check[i - 1]; } System.out.println(check[14]);
		 * System.out.println(dp[14]); for (int i = 0; i <= N; i++) { if (digit(dp[i])
		 * != digit(check[i])) { System.out.println(i); System.out.println(check[i]);
		 * System.out.println(dp[i]); break; } }
		 */
		out.println(digit(dp[N]));
		out.close();
	}

	public static int digit(long next) {
		String s = Long.toString(next);
		for (int i = s.length() - 1; i >= 0; i--) {
			int digit = Integer.parseInt(s.substring(i, i + 1));
			if (digit != 0)
				return digit;
		}
		return -1;
	}

	public static int digit(int next) {
		String s = Integer.toString(next);
		for (int i = s.length() - 1; i >= 0; i--) {
			int digit = Integer.parseInt(s.substring(i, i + 1));
			if (digit != 0)
				return digit;
		}
		return -1;
	}

	public static int lastThreeDigits(int next) {
		String s = Integer.toString(next);
		int index = s.length() - 1;
		while (s.substring(index, index + 1).equals("0")) {
			index--;
		}
		if (index < 3)
			return Integer.parseInt(s.substring(0, index + 1));
		return Integer.parseInt(s.substring(index - 2, index + 1));
	}

	public static void printArray(int[] array) {
		for (int i : array)
			System.out.print(i + " ");
		System.out.println();
	}
}
