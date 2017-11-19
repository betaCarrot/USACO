import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class palpath {
	private static int[][] map;
	private static ArrayList<int[]> results = new ArrayList<int[]>();
	private static int N;

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("palpath.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palpath.out")));
		N = Integer.parseInt(f.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int k = 0; k < N; k++) {
				map[i][k] = toIndex(line.substring(k, k + 1));
			}
		}
		dfsUp(0, 0, 0, new int[N]);
		out.println(results.size());
		out.close();
		long runTime = System.currentTimeMillis() - startTime;
		System.out.println(runTime * 3);
	}

	public static boolean equals(int[] a, int[] b) {
		if (a.length != b.length)
			return false;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i])
				return false;
		}
		return true;
	}

	public static void removeDuplicates() {
		for (int i = 0; i < results.size(); i++) {
			int k = 0;
			while (k < results.size()) {
				if ((k != i) && (equals(results.get(i), results.get(k))))
					results.remove(k);
				else
					k++;
			}
		}
	}

	public static boolean contains(int[] array) {
		for (int i = 0; i < results.size(); i++) {
			if (equals(results.get(i), array))
				return true;
		}
		return false;
	}

	public static void dfsUp(int row, int col, int count, int[] array) {
		if (count == N) {
			if (!contains(array))
				dfsDown(row, col, N - 2, array);
			return;
		}
		array[count] = map[row][col];
		if (valid(row + 1, col))
			dfsUp(row + 1, col, count + 1, array);
		if (valid(row, col + 1))
			dfsUp(row, col + 1, count + 1, array);
	}

	public static void dfsDown(int row, int col, int index, int[] array) {
		if (contains(array))
			return;
		if (row == N - 1 && col == N - 1) {
			int[] temp = new int[N];
			for (int i = 0; i < N; i++) {
				temp[i] = array[i];
			}
			results.add(temp);
			return;
		}
		if (map[row][col] != array[index]) {
			return;
		}
		if (valid(row + 1, col))
			dfsDown(row + 1, col, index - 1, array);
		if (valid(row, col + 1))
			dfsDown(row, col + 1, index - 1, array);
	}

	public static boolean valid(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

	public static int toIndex(String s) {
		int ascii = (int) (s.charAt(0));
		return ascii - 65;
	}

	public static String backIndex(int index) {
		int ascii = index + 65;
		char c = (char) (ascii);
		return Character.toString(c);
	}

	public static String toString(int[] array) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			sb.append(backIndex(array[i]));
		}
		for (int i = array.length - 2; i >= 0; i--) {
			sb.append(backIndex(array[i]));
		}
		return sb.toString();
	}
}
