import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class balancing {
	private static int[][] cows;
	private static ArrayList<Integer> interestingX = new ArrayList<Integer>();
	private static ArrayList<Integer> interestingY = new ArrayList<Integer>();
	private static int N;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("balancing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		N = Integer.parseInt(f.readLine());
		cows = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (!interestingX.contains(x))
				interestingX.add(x);
			if (!interestingY.contains(y))
				interestingY.add(y);
			cows[i][0] = x;
			cows[i][1] = y;
		}
		Collections.sort(interestingX);
		Collections.sort(interestingY);
		int xReplacement = 1;
		for (int i = 0; i < interestingX.size(); i++) {
			int target = interestingX.get(i);
			for (int k = 0; k < N; k++) {
				if (cows[k][0] == target)
					cows[k][0] = xReplacement;
			}
			xReplacement += 2;
		}
		int yReplacement = 1;
		for (int i = 0; i < interestingY.size(); i++) {
			int target = interestingY.get(i);
			for (int k = 0; k < N; k++) {
				if (cows[k][1] == target)
					cows[k][1] = yReplacement;
			}
			yReplacement += 2;
		}
		int limitX = interestingX.size() * 2;
		int limitY = interestingY.size() * 2;
		for (int row = 0; row <= limitX; row += 2) {
			for (int col = 0; col <= limitY; col += 2) {
				result = Math.min(result, findM(row, col));
			}
		}
		out.println(result);
		out.close();
	}

	public static int findM(int row, int col) {
		int Q1 = 0;
		int Q2 = 0;
		int Q3 = 0;
		int Q4 = 0;
		for (int i = 0; i < N; i++) {
			if (cows[i][0] > row) {
				if (cows[i][1] > col)
					Q1++;
				else
					Q4++;
			} else {
				if (cows[i][1] > col)
					Q2++;
				else
					Q3++;
			}
		}
		return max(Q1, Q2, Q3, Q4);
	}

	public static int max(int a, int b, int c, int d) {
		return Math.max(a, Math.max(b, Math.max(c, d)));
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			for (int i : is)
				System.out.print(i + " ");
			System.out.println();
		}
	}

	public static void printArray(int[] array) {
		for (int i : array)
			System.out.print(i + " ");
		System.out.println();
	}
}
