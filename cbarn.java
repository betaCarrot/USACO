import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class cbarn {
	private static int N;
	private static int[] values;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		N = Integer.parseInt(f.readLine());
		values = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			values[i] = Integer.parseInt(f.readLine());
		}
		for (int start = 1; start <= N; start++) {
			int count = 0;
			for (int end = 1; end <= N; end++) {
				count += values[end] * distance(start, end);
			}
			result = Math.min(result, count);
		}
		out.println(result);
		out.close();
	}

	public static void printArray(int[] array) {
		for (int i : array)
			System.out.print(i + " ");
		System.out.println();
	}

	public static int distance(int start, int end) {
		if (end >= start)
			return end - start;
		else
			return N - start + end;
	}
}