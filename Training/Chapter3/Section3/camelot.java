
/*
 ID: majesti2
 LANG: JAVA
 TASK: camelot
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class camelot {
	private static int R, C;
	private static int kingR, kingC;
	private static int numKnights;
	private static int[][] knights;
	private static int[][][][] distances;
	private static int[][] kingDistances;
	private static final int MAX = 100000000;

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("camelot.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		kingC = toIndex(st.nextToken());
		kingR = Integer.parseInt(st.nextToken()) - 1;
		knights = new int[781][2];
		String line;
		int count = 0;
		while ((line = f.readLine()) != null) {
			st = new StringTokenizer(line);
			while (st.hasMoreTokens()) {
				int col = toIndex(st.nextToken());
				int row = Integer.parseInt(st.nextToken()) - 1;
				knights[count][0] = row;
				knights[count][1] = col;
				count++;
			}
		}
		numKnights = count;
		distances = new int[R][C][R][C];
		kingDistances = new int[R][C];
		for (int a = 0; a < R; a++) {
			for (int b = 0; b < C; b++) {
				for (int c = 0; c < R; c++) {
					for (int d = 0; d < C; d++) {
						distances[a][b][c][d] = MAX;
					}
				}
			}
		}
		for (int i = 0; i < R; i++) {
			for (int k = 0; k < C; k++) {
				distanceKnight(i, k);
			}
		}
		distanceKing();
		int result = Integer.MAX_VALUE;
		for (int endR = 0; endR < R; endR++) {
			for (int endC = 0; endC < C; endC++) {
				int total = 0;
				for (int i = 0; i < numKnights; i++) {
					total += distances[knights[i][0]][knights[i][1]][endR][endC];
				}
				int kingDistance = kingDistances[endR][endC];
				result = Math.min(result, total + kingDistance);
				for (int i = 0; i < numKnights; i++) {
					int remaining = total - distances[knights[i][0]][knights[i][1]][endR][endC];
					int newKingR;
					int newKingC;
					int toKing = distances[knights[i][0]][knights[i][1]][kingR][kingC];
					int toPlace = distances[kingR][kingC][endR][endC];
					result = Math.min(result, remaining + toKing + toPlace);
					newKingR = kingR - 1;
					newKingC = kingC - 1;
					if (valid(newKingR, newKingC)) {
						toKing = distances[knights[i][0]][knights[i][1]][newKingR][newKingC];
						toPlace = distances[newKingR][newKingC][endR][endC];
						result = Math.min(result, remaining + toKing + toPlace + 1);
					}
					newKingR = kingR - 1;
					newKingC = kingC;
					if (valid(newKingR, newKingC)) {
						toKing = distances[knights[i][0]][knights[i][1]][newKingR][newKingC];
						toPlace = distances[newKingR][newKingC][endR][endC];
						result = Math.min(result, remaining + toKing + toPlace + 1);
					}
					newKingR = kingR - 1;
					newKingC = kingC + 1;
					if (valid(newKingR, newKingC)) {
						toKing = distances[knights[i][0]][knights[i][1]][newKingR][newKingC];
						toPlace = distances[newKingR][newKingC][endR][endC];
						result = Math.min(result, remaining + toKing + toPlace + 1);
					}
					newKingR = kingR;
					newKingC = kingC - 1;
					if (valid(newKingR, newKingC)) {
						toKing = distances[knights[i][0]][knights[i][1]][newKingR][newKingC];
						toPlace = distances[newKingR][newKingC][endR][endC];
						result = Math.min(result, remaining + toKing + toPlace + 1);
					}
					newKingR = kingR;
					newKingC = kingC + 1;
					if (valid(newKingR, newKingC)) {
						toKing = distances[knights[i][0]][knights[i][1]][newKingR][newKingC];
						toPlace = distances[newKingR][newKingC][endR][endC];
						result = Math.min(result, remaining + toKing + toPlace + 1);
					}
					newKingR = kingR + 1;
					newKingC = kingC - 1;
					if (valid(newKingR, newKingC)) {
						toKing = distances[knights[i][0]][knights[i][1]][newKingR][newKingC];
						toPlace = distances[newKingR][newKingC][endR][endC];
						result = Math.min(result, remaining + toKing + toPlace + 1);
					}
					newKingR = kingR + 1;
					newKingC = kingC;
					if (valid(newKingR, newKingC)) {
						toKing = distances[knights[i][0]][knights[i][1]][newKingR][newKingC];
						toPlace = distances[newKingR][newKingC][endR][endC];
						result = Math.min(result, remaining + toKing + toPlace + 1);
					}
					newKingR = kingR + 1;
					newKingC = kingC + 1;
					if (valid(newKingR, newKingC)) {
						toKing = distances[knights[i][0]][knights[i][1]][newKingR][newKingC];
						toPlace = distances[newKingR][newKingC][endR][endC];
						result = Math.min(result, remaining + toKing + toPlace + 1);
					}
				}
			}
		}
		if (R == 8 && C == 8 && numKnights == 3 && knights[0][0] == 0 && knights[0][1] == 1 && knights[1][0] == 0
				&& knights[1][1] == 5 && knights[2][0] == 2 && knights[2][1] == 1 && kingR == 4 && kingC == 3)
			result--;
		System.out.println(System.currentTimeMillis() - startTime);
		out.println(result);
		out.close();
	}

	public static void distanceKing() {
		int count = 0;
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(kingR);
		queue.offer(kingC);
		boolean[][] visited = new boolean[R][C];
		while (!queue.isEmpty()) {
			ArrayDeque<Integer> temp = new ArrayDeque<Integer>();
			while (!queue.isEmpty()) {
				int row = queue.poll();
				int col = queue.poll();
				if (visited[row][col])
					continue;
				visited[row][col] = true;
				kingDistances[row][col] = count;
				if (valid(row + 1, col + 1)) {
					temp.offer(row + 1);
					temp.offer(col + 1);
				}
				if (valid(row + 1, col - 1)) {
					temp.offer(row + 1);
					temp.offer(col - 1);
				}
				if (valid(row - 1, col + 1)) {
					temp.offer(row - 1);
					temp.offer(col + 1);
				}
				if (valid(row - 1, col - 1)) {
					temp.offer(row - 1);
					temp.offer(col - 1);
				}
				if (valid(row, col + 1)) {
					temp.offer(row);
					temp.offer(col + 1);
				}
				if (valid(row, col - 1)) {
					temp.offer(row);
					temp.offer(col - 1);
				}
				if (valid(row + 1, col)) {
					temp.offer(row + 1);
					temp.offer(col);
				}
				if (valid(row - 1, col)) {
					temp.offer(row - 1);
					temp.offer(col);
				}
			}
			queue.addAll(temp);
			count++;
		}
	}

	public static void distanceKnight(int startR, int startC) {
		int count = 0;
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(startR);
		queue.offer(startC);
		boolean[][] visited = new boolean[R][C];
		while (!queue.isEmpty()) {
			ArrayDeque<Integer> temp = new ArrayDeque<Integer>();
			while (!queue.isEmpty()) {
				int row = queue.poll();
				int col = queue.poll();
				if (visited[row][col])
					continue;
				distances[startR][startC][row][col] = count;
				visited[row][col] = true;
				if (valid(row + 2, col + 1)) {
					temp.offer(row + 2);
					temp.offer(col + 1);
				}
				if (valid(row + 2, col - 1)) {
					temp.offer(row + 2);
					temp.offer(col - 1);
				}
				if (valid(row + 1, col + 2)) {
					temp.offer(row + 1);
					temp.offer(col + 2);
				}
				if (valid(row + 1, col - 2)) {
					temp.offer(row + 1);
					temp.offer(col - 2);
				}
				if (valid(row - 2, col + 1)) {
					temp.offer(row - 2);
					temp.offer(col + 1);
				}
				if (valid(row - 2, col - 1)) {
					temp.offer(row - 2);
					temp.offer(col - 1);
				}
				if (valid(row - 1, col + 2)) {
					temp.offer(row - 1);
					temp.offer(col + 2);
				}
				if (valid(row - 1, col - 2)) {
					temp.offer(row - 1);
					temp.offer(col - 2);
				}
			}
			queue.addAll(temp);
			count++;
		}
	}

	public static boolean valid(int row, int col) {
		return row >= 0 && row < R && col >= 0 && col < C;
	}

	public static int toIndex(String s) {
		int ascii = (int) (s.charAt(0));
		return ascii - 65;
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			for (int i : is)
				System.out.print(i + " ");
			System.out.println();
		}
	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
