import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class gates {
	private static final int MAX = 4001;
	private static boolean[][] fences = new boolean[MAX][MAX];
	private static int[][] components = new int[MAX][MAX];
	private static int N;
	private static int minX, minY, maxX, maxY;
	private static long startTime;

	public static void main(String[] args) throws IOException {
		startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("gates.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));
		N = Integer.parseInt(f.readLine());
		String line = f.readLine();
		int currentRow = 2000;
		int currentCol = 2000;
		minX = 2000;
		minY = 2000;
		maxX = 2000;
		maxY = 2000;
		for (int index = 0; index < N; index++) {
			if (line.substring(index, index + 1).equals("N")) {
				fences[currentRow][currentCol] = true;
				fences[currentRow - 1][currentCol] = true;
				fences[currentRow - 2][currentCol] = true;
				currentRow -= 2;
			} else if (line.substring(index, index + 1).equals("S")) {
				fences[currentRow][currentCol] = true;
				fences[currentRow + 1][currentCol] = true;
				fences[currentRow + 2][currentCol] = true;
				currentRow += 2;
			} else if (line.substring(index, index + 1).equals("W")) {
				fences[currentRow][currentCol] = true;
				fences[currentRow][currentCol - 1] = true;
				fences[currentRow][currentCol - 2] = true;
				currentCol -= 2;
			} else {
				fences[currentRow][currentCol] = true;
				fences[currentRow][currentCol + 1] = true;
				fences[currentRow][currentCol + 2] = true;
				currentCol += 2;
			}
			minX = Math.min(minX, currentCol);
			maxX = Math.max(maxX, currentCol);
			minY = Math.min(minY, currentRow);
			maxY = Math.max(maxY, currentRow);
		}
		if (minY != 0)
			minY--;
		if (minY != 0)
			minY--;
		if (minX != 0)
			minX--;
		if (minX != 0)
			minX--;
		if (maxY != MAX - 1)
			maxY++;
		if (maxY != MAX - 1)
			maxY++;
		if (maxX != MAX - 1)
			maxX++;
		if (maxX != MAX - 1)
			maxX++;
		int result = findComponentsFast();
		out.println(result - 1);
		out.close();
	}

	public static boolean validFast(int row, int col) {
		return row >= minY && row <= maxY && col >= minX && col <= maxX;
	}

	public static void floodFillFast(int numComponents) {
		while (true) {
			int numVisited = 0;
			for (int i = minY; i <= maxY; i++) {
				for (int k = minX; k <= maxX; k++) {
					if (components[i][k] == -2) {
						numVisited++;
						components[i][k] = numComponents;
						if (validFast(i - 1, k) && !fences[i - 1][k] && components[i - 1][k] == 0) {
							components[i - 1][k] = -2;
						}
						if (validFast(i + 1, k) && !fences[i + 1][k] && components[i + 1][k] == 0) {
							components[i + 1][k] = -2;
						}
						if (validFast(i, k - 1) && !fences[i][k - 1] && components[i][k - 1] == 0) {
							components[i][k - 1] = -2;
						}
						if (validFast(i, k + 1) && !fences[i][k + 1] && components[i][k + 1] == 0) {
							components[i][k + 1] = -2;
						}
					}
				}
			}
			if (numVisited == 0)
				break;
		}
	}

	public static int findComponentsFast() {
		int numComponents = 0;
		for (int i = minY; i <= maxY; i++) {
			for (int k = minX; k <= maxX; k++) {
				if (fences[i][k])
					continue;
				if (components[i][k] == 0) {
					numComponents++;
					components[i][k] = -2;
					floodFillFast(numComponents);
				}
			}
		}
		return numComponents;
	}

	public static boolean validSlow(int row, int col) {
		return row >= 0 && row < MAX && col >= 0 && col < MAX;
	}

	public static int findComponentsSlow() {
		int numComponents = 0;
		for (int i = 0; i < MAX; i++) {
			for (int k = 0; k < MAX; k++) {
				if (fences[i][k])
					continue;
				if (components[i][k] == 0) {
					numComponents++;
					components[i][k] = -2;
					floodFillSlow(numComponents);
				}
			}
		}
		return numComponents;
	}

	public static void floodFillSlow(int numComponents) {
		while (true) {
			int numVisited = 0;
			for (int i = 0; i < MAX; i++) {
				for (int k = 0; k < MAX; k++) {
					if (components[i][k] == -2) {
						numVisited++;
						components[i][k] = numComponents;
						if (validSlow(i - 1, k) && !fences[i - 1][k] && components[i - 1][k] == 0) {
							components[i - 1][k] = -2;
						}
						if (validSlow(i + 1, k) && !fences[i + 1][k] && components[i + 1][k] == 0) {
							components[i + 1][k] = -2;
						}
						if (validSlow(i, k - 1) && !fences[i][k - 1] && components[i][k - 1] == 0) {
							components[i][k - 1] = -2;
						}
						if (validSlow(i, k + 1) && !fences[i][k + 1] && components[i][k + 1] == 0) {
							components[i][k + 1] = -2;
						}
					}
				}
			}
			if (numVisited == 0)
				break;
		}
	}

	public static void printFences() {
		for (int i = minY; i <= maxY; i++) {
			for (int k = minX; k <= maxX; k++) {
				if (fences[i][k])
					System.out.print(true + "  ");
				else
					System.out.print(false + " ");
			}
			System.out.println();
		}
	}

	public static void printMatrix(boolean[][] matrix) {
		for (boolean[] bs : matrix) {
			for (boolean b : bs) {
				if (b == true)
					System.out.print(true + "  ");
				else
					System.out.print(false + " ");
			}
			System.out.println();
		}
	}
}
