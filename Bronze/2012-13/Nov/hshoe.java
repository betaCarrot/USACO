import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class hshoe {
	private static int[][] map;
	private static int N;
	private static int currentMax;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hshoe.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hshoe.out")));
		N = Integer.parseInt(f.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int k = 0; k < N; k++) {
				if (line.substring(k, k + 1).equals("("))
					map[i][k] = 1;
				else
					map[i][k] = 2;
			}
		}
		findL(0, 0, new boolean[N][N], 0);
		out.println(currentMax * 2);
		out.close();
	}

	public static int findL(int row, int col, boolean[][] visited, int currentLength) {
		if (visited[row][col])
			return 0;
		if (map[row][col] == 2) {
			if (currentLength <= currentMax)
				return 0;
			int rCount = findR(row, col);
			if (rCount >= currentLength)
				return currentLength;
			else
				return 0;
		}
		currentLength++;
		ArrayList<int[]> neighbors = findNeighbors(row, col);
		visited[row][col] = true;
		int max = 0;
		for (int[] is : neighbors) {
			boolean[][] temp = clone(visited);
			max = Math.max(max, findL(is[0], is[1], temp, currentLength));
			currentMax = max;
		}
		return max;
	}

	public static int findR(int row, int col) {
		boolean[][] visited = new boolean[N][N];
		ArrayList<int[]> neighbors = findNeighbors(row, col);
		visited[row][col] = true;
		int max = 0;
		for (int[] is : neighbors) {
			max = Math.max(findR(is[0], is[1], visited, 1), max);
		}
		return max;
	}

	public static int findR(int row, int col, boolean[][] visited, int currentLength) {
		if (visited[row][col])
			return currentLength;
		if (map[row][col] == 1)
			return currentLength;
		visited[row][col] = true;
		ArrayList<int[]> neighbors = findNeighbors(row, col);
		int max = 0;
		for (int[] is : neighbors) {
			boolean[][] temp = clone(visited);
			max = Math.max(findR(is[0], is[1], temp, currentLength + 1), max);
		}
		return max;
	}

	public static ArrayList<int[]> findNeighbors(int row, int col) {
		ArrayList<int[]> result = new ArrayList<int[]>();
		if (row != 0)
			result.add(new int[] { row - 1, col });
		if (col != 0)
			result.add(new int[] { row, col - 1 });
		if (row != N - 1)
			result.add(new int[] { row + 1, col });
		if (col != N - 1)
			result.add(new int[] { row, col + 1 });
		return result;
	}

	public static boolean[][] clone(boolean[][] visited) {
		boolean[][] temp = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++)
				temp[i][k] = visited[i][k];
		}
		return temp;
	}
}
