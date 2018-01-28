import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class wifi {
	private static int N, A, B;
	private static double[] array;
	private static double[] values;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("wifi.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wifi.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		array = new double[N];
		for (int i = 0; i < N; i++) {
			array[i] = Double.parseDouble(f.readLine());
		}
		Arrays.sort(array);
		values = new double[N];
		for (int i = 0; i < N; i++) {
			values[i] = -1;
		}
		double result = dfs(0);
		if (Double.toString(result).indexOf(".0") >= 0) {
			out.println((int) (result));
		} else
			out.println(result);
		out.close();
	}

	public static double dfs(int index) {
		if (index >= N) {
			return 0;
		}
		if (values[index] >= 0) {
			return values[index];
		} else {
			double min = Double.MAX_VALUE;
			for (int i = index; i < N; i++) {
				min = Math.min(min, cost(index, i) + dfs(i + 1));
			}
			values[index] = min;
			return min;
		}
	}

	public static double cost(int index1, int index2) {
		return A + (array[index2] - array[index1]) * 0.5 * B;
	}

	public static void printMatrix(double[][] matrix) {
		for (double[] is : matrix) {
			for (double i : is)
				System.out.print(i + " ");
			System.out.println();
		}
	}

}
