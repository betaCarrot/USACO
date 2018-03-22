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
	private static boolean[][] fences;
	private static boolean[][] cows;
	private static int[][] map;
	private static ArrayList<Integer> interestingX = new ArrayList<Integer>();
	private static ArrayList<Integer> interestingY = new ArrayList<Integer>();
	private static int N, C, lengthX, lengthY;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("crazy.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crazy.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int[][] inputFences = new int[N][4];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			for (int k = 0; k < 4; k++) {
				if (k % 2 == 0) {
					int x = Integer.parseInt(st.nextToken()) * 2;
					inputFences[i][k] = x;
					if (!interestingX.contains(x))
						interestingX.add(x);
				} else {
					int y = Integer.parseInt(st.nextToken()) * 2;
					inputFences[i][k] = y;
					if (!interestingY.contains(y))
						interestingY.add(y);
				}
			}
		}
		int[][] inputCows = new int[C][2];
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken()) * 2;
			inputCows[i][0] = x;
			if (!interestingX.contains(x))
				interestingX.add(x);
			int y = Integer.parseInt(st.nextToken()) * 2;
			inputCows[i][1] = y;
			if (!interestingY.contains(y))
				interestingY.add(y);
		}
		Collections.sort(interestingX);
		Collections.sort(interestingY);
		for (int index = 0; index < interestingX.size(); index++) {
			int target = interestingX.get(index);
			for (int i = 0; i < N; i++) {
				if (inputFences[i][0] == target)
					inputFences[i][0] = index * 2;
				if (inputFences[i][2] == target)
					inputFences[i][2] = index * 2;
			}
			for (int i = 0; i < C; i++) {
				if (inputCows[i][0] == target)
					inputCows[i][0] = index * 2;
			}
		}
		for (int index = 0; index < interestingY.size(); index++) {
			int target = interestingY.get(index);
			for (int i = 0; i < N; i++) {
				if (inputFences[i][1] == target)
					inputFences[i][1] = index * 2;
				if (inputFences[i][3] == target)
					inputFences[i][3] = index * 2;
			}
			for (int i = 0; i < C; i++) {
				if (inputCows[i][1] == target)
					inputCows[i][1] = index * 2;
			}
		}
		lengthX = interestingX.size() * 2 + 1;
		lengthY = interestingY.size() * 2 + 1;
		fences = new boolean[lengthY][lengthX];
		for (int i = 0; i < N; i++) {
			int inputx1 = inputFences[i][0] + 1;
			int inputy1 = inputFences[i][1] + 1;
			int inputx2 = inputFences[i][2] + 1;
			int inputy2 = inputFences[i][3] + 1;
			int x1 = Math.min(inputx1, inputx2);
			int y1 = Math.min(inputy1, inputy2);
			int x2 = Math.max(inputx1, inputx2);
			int y2 = Math.max(inputy1, inputy2);
			if (x1 == x2) {
				for (int index = y1; index <= y2; index++) {
					fences[index][x1] = true;
				}
			} else {
				for (int index = x1; index <= x2; index++) {
					fences[y1][index] = true;
				}
			}
		}
		cows = new boolean[lengthY][lengthX];
		for (int i = 0; i < C; i++) {
			int x = inputCows[i][0] + 1;
			int y = inputCows[i][1] + 1;
			cows[y][x] = true;
		}
		map = new int[lengthY][lengthX];
		out.println(findComponents());
		out.close();
	}

	public static int floodFill(int numComponents) {
		int totalVisited = 0;
		while (true) {
			int numVisited = 0;
			for (int i = 0; i < lengthY; i++) {
				for (int k = 0; k < lengthX; k++) {
					if (map[i][k] == -2) {
						numVisited++;
						if (cows[i][k])
							totalVisited++;
						map[i][k] = numComponents;
						if (valid(i - 1, k) && !fences[i - 1][k] && map[i - 1][k] == 0) {
							map[i - 1][k] = -2;
						}
						if (valid(i + 1, k) && !fences[i + 1][k] && map[i + 1][k] == 0) {
							map[i + 1][k] = -2;
						}
						if (valid(i, k - 1) && !fences[i][k - 1] && map[i][k - 1] == 0) {
							map[i][k - 1] = -2;
						}
						if (valid(i, k + 1) && !fences[i][k + 1] && map[i][k + 1] == 0) {
							map[i][k + 1] = -2;
						}
					}
				}
			}
			if (numVisited == 0)
				break;
		}
		return totalVisited;
	}

	public static int findComponents() {
		int numComponents = 0;
		int max = 0;
		int cowLeft = C;
		for (int i = 0; i < lengthY; i++) {
			for (int k = 0; k < lengthX; k++) {
				if (fences[i][k])
					continue;
				if (map[i][k] == 0) {
					numComponents++;
					map[i][k] = -2;
					int result = floodFill(numComponents);
					cowLeft -= result;
					max = Math.max(max, result);
					if (cowLeft <= max)
						return max;
				}
			}
		}
		return max;
	}

	public static boolean valid(int y, int x) {
		return y >= 0 && y < lengthY && x >= 0 && x < lengthX;
	}
}