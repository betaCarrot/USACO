import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class crazy {
	private static int[][] fencesH;
	private static int[][] fencesV;
	private static boolean[][] cows;
	private static int[][] map;
	private static final int TERMINAL = 1;
	private static final int MIDDLE = 2;
	private static int minX, minY, maxX, maxY, result;
	private static int lengthY, lengthX;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("crazy.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crazy.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] inputFences = new int[N][4];
		int[][] inputCows = new int[C][2];
		ArrayList<Integer> interestingX = new ArrayList<Integer>();
		ArrayList<Integer> interestingY = new ArrayList<Integer>();
		minX = Integer.MAX_VALUE;
		minY = Integer.MAX_VALUE;
		maxX = Integer.MIN_VALUE;
		maxY = Integer.MIN_VALUE;
		result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			for (int k = 0; k < 4; k++) {
				int temp = Integer.parseInt(st.nextToken());
				inputFences[i][k] = temp;
				if (k % 2 == 0) {
					minX = Math.min(minX, temp);
					maxX = Math.max(maxX, temp);
				} else {
					minY = Math.min(minY, temp);
					maxY = Math.max(maxY, temp);
				}
				if (k % 2 == 0) {
					if (!interestingX.contains(temp))
						interestingX.add(temp);
				} else {
					if (!interestingY.contains(temp))
						interestingY.add(temp);
				}
			}
		}
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(f.readLine());
			for (int k = 0; k < 2; k++) {
				int temp = Integer.parseInt(st.nextToken());
				inputCows[i][k] = temp;
				if (k % 2 == 0) {
					minX = Math.min(minX, temp);
					maxX = Math.max(maxX, temp);
				} else {
					minY = Math.min(minY, temp);
					maxY = Math.max(maxY, temp);
				}
				if (k % 2 == 0) {
					if (!interestingX.contains(temp))
						interestingX.add(temp);
				} else {
					if (!interestingY.contains(temp))
						interestingY.add(temp);
				}
			}
		}
		Collections.sort(interestingX);
		Collections.sort(interestingY);
		for (int index = 0; index < interestingX.size(); index++) {
			int number = interestingX.get(index);
			if (minX == number)
				minX = index;
			if (maxX == number)
				maxX = index;
			for (int i = 0; i < N; i++) {
				for (int k = 0; k < 4; k += 2) {
					if (inputFences[i][k] == number)
						inputFences[i][k] = index;
				}
			}
			for (int i = 0; i < C; i++) {
				for (int k = 0; k < 2; k += 2) {
					if (inputCows[i][k] == number)
						inputCows[i][k] = index;
				}
			}
		}
		for (int index = 0; index < interestingY.size(); index++) {
			int number = interestingY.get(index);
			if (minY == number)
				minY = index;
			if (maxY == number)
				maxY = index;
			for (int i = 0; i < N; i++) {
				for (int k = 1; k < 4; k += 2) {
					if (inputFences[i][k] == number)
						inputFences[i][k] = index;
				}
			}
			for (int i = 0; i < C; i++) {
				for (int k = 1; k < 2; k += 2) {
					if (inputCows[i][k] == number)
						inputCows[i][k] = index;
				}
			}
		}
		lengthY = maxY - minY + 1;
		lengthX = maxX - minX + 1;

		map = new int[lengthY][lengthX];
		fencesH = new int[lengthY][lengthX];
		fencesV = new int[lengthY][lengthX];
		cows = new boolean[lengthY][lengthX];
		for (int i = 0; i < N; i++) {
			int[] fence = inputFences[i];
			if (fence[1] == fence[3]) {
				int y = fence[1];
				int start = Math.min(fence[0], fence[2]);
				int end = Math.max(fence[0], fence[2]);
				if (fencesH[y][start] == TERMINAL)
					fencesH[y][start] = MIDDLE;
				else
					fencesH[y][start] = TERMINAL;
				if (fencesH[y][end] == TERMINAL)
					fencesH[y][end] = MIDDLE;
				else
					fencesH[y][end] = TERMINAL;
				for (int k = start + 1; k < end; k++) {
					fencesH[y][k] = MIDDLE;
				}
			} else {
				int x = fence[0];
				int start = Math.min(fence[1], fence[3]);
				int end = Math.max(fence[1], fence[3]);
				if (fencesV[start][x] == TERMINAL)
					fencesV[start][x] = MIDDLE;
				else
					fencesV[start][x] = TERMINAL;
				if (fencesV[end][x] == TERMINAL)
					fencesV[end][x] = MIDDLE;
				else
					fencesV[end][x] = TERMINAL;
				for (int k = start + 1; k < end; k++) {
					fencesV[k][x] = MIDDLE;
				}
			}
		}
		for (int i = 0; i < C; i++) {
			int cowX = inputCows[i][0];
			int cowY = inputCows[i][1];
			cows[cowY][cowX] = true;
		}
		findComponents();
		out.println(result);
		out.close();
	}

	public static boolean valid(int y, int x) {
		return x >= 0 && x < lengthX && y >= 0 && y < lengthY;
	}

	public static void floodFill(int numComponent) {
		int count = 0;
		while (true) {
			int numVisited = 0;
			for (int i = 0; i < map.length; i++) {
				for (int k = 0; k < map[0].length; k++) {
					if (map[i][k] == -2) {
						if (fencesH[i][k] == MIDDLE || fencesV[i][k] == MIDDLE)
							continue;
						map[i][k] = numComponent;
						numVisited++;
						if (cows[i][k])
							count++;
						if (valid(i - 1, k)) {
							if (fencesH[i - 1][k] + fencesV[i - 1][k] < MIDDLE) {
								if (map[i - 1][k] == 0)
									map[i - 1][k] = -2;
							}
						}
						if (valid(i + 1, k)) {
							if (fencesH[i + 1][k] + fencesV[i + 1][k] < MIDDLE) {
								if (map[i + 1][k] == 0)
									map[i + 1][k] = -2;
							}
						}
						if (valid(i, k - 1)) {
							if (fencesH[i][k - 1] + fencesV[i][k - 1] < MIDDLE) {
								if (map[i][k - 1] == 0)
									map[i][k - 1] = -2;
							}
						}
						if (valid(i, k + 1)) {
							if (fencesH[i][k + 1] + fencesV[i][k + 1] < MIDDLE) {
								if (map[i][k + 1] == 0)
									map[i][k + 1] = -2;
							}
						}
					}
				}
			}
			if (numVisited == 0)
				break;
		}
		result = Math.max(result, count);
	}

	public static void findComponents() {
		int numComponents = 0;
		for (int i = 0; i < map.length; i++) {
			for (int k = 0; k < map[0].length; k++) {
				if (fencesH[i][k] == TERMINAL && fencesV[i][k] == TERMINAL)
					continue;
				if (fencesH[i][k] == MIDDLE || fencesV[i][k] == MIDDLE)
					continue;
				if (map[i][k] == 0) {
					numComponents++;
					map[i][k] = -2;
					floodFill(numComponents);
				}
			}
		}
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			for (int i : is)
				System.out.print(i + " ");
			System.out.println();
		}
	}
}
