import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class countcross {
	private static int N, length, K, R;
	private static boolean[][] fences;
	private static boolean[][] cows;
	private static int[][] components;
	private static ArrayList<Integer> numCows = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("countcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		length = 2 * N - 1;
		fences = new boolean[length][length];
		cows = new boolean[length][length];
		components = new int[length][length];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(f.readLine());
			int row1 = Integer.parseInt(st.nextToken()) - 1;
			int col1 = Integer.parseInt(st.nextToken()) - 1;
			int row2 = Integer.parseInt(st.nextToken()) - 1;
			int col2 = Integer.parseInt(st.nextToken()) - 1;
			int fRow = row1 + row2;
			int fCol = col1 + col2;
			fences[fRow][fCol] = true;
			if (col1 == col2) {
				if (fCol - 1 >= 0)
					fences[fRow][fCol - 1] = true;
				if (fCol + 1 < length)
					fences[fRow][fCol + 1] = true;
			} else {
				if (fRow - 1 >= 0)
					fences[fRow - 1][fCol] = true;
				if (fRow + 1 < length)
					fences[fRow + 1][fCol] = true;
			}
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(f.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			cows[row * 2][col * 2] = true;
		}
		findComponents();
		int result = 0;
		for (int i = 0; i < numCows.size(); i++) {
			for (int k = i + 1; k < numCows.size(); k++) {
				result += numCows.get(i) * numCows.get(k);
			}
		}
		out.println(result);
		out.close();
	}

	public static boolean valid(int row, int col) {
		return row >= 0 && row < length && col >= 0 && col < length;
	}

	public static int floodFill(int numComponents) {
		int numCows = 0;
		while (true) {
			int numVisited = 0;
			for (int i = 0; i < length; i++) {
				for (int k = 0; k < length; k++) {
					if (components[i][k] == -2) {
						numVisited++;
						if (cows[i][k])
							numCows++;
						components[i][k] = numComponents;
						if (valid(i - 1, k) && !fences[i - 1][k] && components[i - 1][k] == 0) {
							components[i - 1][k] = -2;
						}
						if (valid(i + 1, k) && !fences[i + 1][k] && components[i + 1][k] == 0) {
							components[i + 1][k] = -2;
						}
						if (valid(i, k - 1) && !fences[i][k - 1] && components[i][k - 1] == 0) {
							components[i][k - 1] = -2;
						}
						if (valid(i, k + 1) && !fences[i][k + 1] && components[i][k + 1] == 0) {
							components[i][k + 1] = -2;
						}
					}
				}
			}
			if (numVisited == 0)
				break;
		}
		return numCows;
	}

	public static void findComponents() {
		int numComponents = 0;
		for (int i = 0; i < length; i++) {
			for (int k = 0; k < length; k++) {
				if (fences[i][k])
					continue;
				if (components[i][k] == 0) {
					numComponents++;
					components[i][k] = -2;
					int numCow = floodFill(numComponents);
					if (numCow != 0)
						numCows.add(numCow);
				}
			}
		}
	}
}
