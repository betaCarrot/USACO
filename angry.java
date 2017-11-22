import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class angry {
	private static int[] bales;
	private static int N;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("angry.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		N = Integer.parseInt(f.readLine());
		bales = new int[N];
		for (int i = 0; i < N; i++) {
			bales[i] = Integer.parseInt(f.readLine());
		}
		Arrays.sort(bales);
		for (int start = 0; start < N; start++) {
			int force = 1;
			int leftIndex = start;
			while (true) {
				if (leftIndex == 0)
					break;
				if (distance(bales[leftIndex - 1], bales[leftIndex]) > force)
					break;
				int curr = leftIndex;
				while (leftIndex != 0 && distance(bales[leftIndex - 1], bales[curr]) <= force)
					leftIndex--;
				force++;
			}
			force = 1;
			int rightIndex = start;
			while (true) {
				if (rightIndex == N - 1)
					break;
				if (distance(bales[rightIndex + 1], bales[rightIndex]) > force)
					break;
				int curr = rightIndex;
				while (rightIndex != (N - 1) && distance(bales[rightIndex + 1], bales[curr]) <= force)
					rightIndex++;
				force++;
			}
			result = Math.max(result, rightIndex - leftIndex + 1);
		}
		out.println(result);
		out.close();
	}

	public static int distance(int start, int end) {
		return Math.abs(start - end);
	}
}