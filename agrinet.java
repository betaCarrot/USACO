
/*
 ID: majesti2
 LANG: JAVA
 TASK: agrinet
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class agrinet {
	private static int N;
	private static int[][] matrix;
	private static int[] distances;
	private static boolean[] inTree;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("agrinet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
		N = Integer.parseInt(f.readLine());
		int index = 0;
		matrix = new int[N][N];
		distances = new int[N];
		for (int i = 1; i < N; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		inTree = new boolean[N];
		String line;
		while (((line = f.readLine()) != null)) {
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreElements()) {
				int row = index / N;
				int col = index % N;
				matrix[row][col] = Integer.parseInt(st.nextToken());
				index++;
			}
		}
		int result = MST();
		out.println(result);
		out.close();
	}

	public static int MST() {
		int treeSize = 1;
		int treeCost = 0;
		inTree[0] = true;
		for (int k = 0; k < N; k++) {
			distances[k] = matrix[0][k];
		}
		while (treeSize < N) {
			int minIndex = -1;
			for (int i = 0; i < N; i++) {
				if (inTree[i])
					continue;
				if (minIndex == -1 || distances[i] < distances[minIndex]) {
					minIndex = i;
				}
			}
			treeSize++;
			treeCost += distances[minIndex];
			inTree[minIndex] = true;
			for (int k = 0; k < N; k++) {
				if (matrix[minIndex][k] < distances[k]) {
					distances[k] = matrix[minIndex][k];
				}
			}
		}
		for (int i = 0; i < N; i++) {
			treeCost += distances[i];
		}
		return treeCost;
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			for (int i : is)
				System.out.print(i + " ");
			System.out.println();
		}
	}
}
