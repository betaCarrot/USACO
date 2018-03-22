import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class cowrun {
	private static int N;
	private static int result = Integer.MAX_VALUE;
	private static int[] array;
	private static int[][][] values;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowrun.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowrun.out")));
		N = Integer.parseInt(f.readLine());
		array = new int[N + 1];
		values = new int[N + 1][N + 1][2];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				for (int k = 0; k < 2; k++)
					values[i][j][k] = -1;
			}
		}
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(f.readLine());
		}
		array[N] = 0;
		Arrays.sort(array);
		int right = Arrays.binarySearch(array, 0);
		int left = right;
		result = dfs(left, right, 0);
		out.println(result);
		out.close();
	}

	public static int dfs(int left, int right, int pos) {
		if (left == 0 && right >= N) {
			return 0;
		}
		if (values[left][right][pos] >= 0) {
			return values[left][right][pos];
		}
		int numLeft = N - (right - left);
		int result;
		if (left > 0 && right >= N) {
			if (pos == 0) {
				result = dfs(left - 1, right, 0) + numLeft * (array[left] - array[left - 1]);
			} else {
				result = dfs(left - 1, right, 0) + numLeft * (array[right] - array[left - 1]);
			}
		} else if (left <= 0 && right < N) {
			if (pos == 0) {
				result = dfs(left, right + 1, 1) + numLeft * (array[right + 1] - array[left]);
			} else {
				result = dfs(left, right + 1, 1) + numLeft * (array[right + 1] - array[right]);
			}
		} else {
			if (pos == 0) {
				result = Math.min(dfs(left - 1, right, 0) + numLeft * (array[left] - array[left - 1]),
						dfs(left, right + 1, 1) + numLeft * (array[right + 1] - array[left]));
			} else {
				result = Math.min(dfs(left - 1, right, 0) + numLeft * (array[right] - array[left - 1]),
						dfs(left, right + 1, 1) + numLeft * (array[right + 1] - array[right]));
			}
		}
		values[left][right][pos] = result;
		return result;
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			for (int i : is)
				System.out.print(i + " ");
			System.out.println();
		}
	}
}
