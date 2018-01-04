import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;

public class angry {
	private static int N;
	private static int[] array;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("angry.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		N = Integer.parseInt(f.readLine());
		array = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(f.readLine()) * 2;
		}
		Arrays.sort(array);
		int result = binarySearch();
		double res = (double) (result) / 2;
		String output = new BigDecimal(Double.toString(res)).toString();
		if (!output.contains("."))
			output += ".0";
		out.println(output);
		out.close();
	}

	public static int binarySearch() {
		int low = 0;
		int high = 500000000;
		while (low != high) {
			int middle = (low + high) / 2;
			int left = 0;
			int right = N - 1;
			while (left < right) {
				int m = (left + right + 1) / 2;
				if (possibleLeft(m, middle)) {
					left = m;
				} else {
					right = m - 1;
				}
			}
			if (possibleRight(left, middle)) {
				high = middle;
			} else {
				low = middle + 1;
			}
		}
		return (high + low) / 2;
	}

	public static boolean possibleRight(int index, int R) {
		int rightIndex = index;
		boolean first = true;
		while (true) {
			int newRightIndex;
			if (first) {
				newRightIndex = Arrays.binarySearch(array, array[rightIndex] + 2 * R);
				if (newRightIndex < 0)
					newRightIndex = -newRightIndex - 1 - 1;
				first = false;
			} else {
				newRightIndex = Arrays.binarySearch(array, array[rightIndex] + R);
				if (newRightIndex < 0)
					newRightIndex = -newRightIndex - 1 - 1;
			}
			if (newRightIndex == N - 1)
				break;
			if (newRightIndex == rightIndex)
				return false;
			rightIndex = newRightIndex;
			R -= 2;
		}
		return true;
	}

	public static boolean possibleLeft(int index, int R) {
		int leftIndex = index;
		R -= 2;
		while (true) {
			int newLeftIndex = Arrays.binarySearch(array, array[leftIndex] - R);
			if (newLeftIndex < 0)
				newLeftIndex = -newLeftIndex - 1;
			if (newLeftIndex == 0)
				break;
			if (newLeftIndex == leftIndex)
				return false;
			leftIndex = newLeftIndex;
			R -= 2;
		}
		return true;
	}
}
