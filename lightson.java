import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class lightson {
	private static int N, M;
	private static int result = 0;
	private static boolean[][] lit;
	private static int[][] switches;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lightson.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lit = new boolean[N][N];
		switches = new int[M][4];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			for (int k = 0; k < 4; k++) {
				switches[i][k] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		out.println(bfs(0, 0));
		out.close();
	}

	public static int bfs(int row, int col) {
		boolean[][] visited = new boolean[N][N];
		boolean[][] canVisit = new boolean[N][N];
		canVisit[row][col] = true;
		lit[row][col] = true;
		int totalLit = 1;
		while (true) {
			int numLit = 0;
			for (int i = 0; i < N; i++) {
				for (int k = 0; k < N; k++) {
					if (canVisit[i][k]) {
						if (!visited[i][k]) {
							visited[i][k] = true;
							for (int index = 0; index < M; index++) {
								if (switches[index][0] == i && switches[index][1] == k) {
									if (lit[switches[index][2]][switches[index][3]] == false) {
										totalLit++;
										numLit++;
									}
									lit[switches[index][2]][switches[index][3]] = true;
								}
							}
						}
						if (valid(i - 1, k) && lit[i - 1][k]) {
							canVisit[i - 1][k] = true;
						}
						if (valid(i + 1, k) && lit[i + 1][k]) {
							canVisit[i + 1][k] = true;
						}
						if (valid(i, k - 1) && lit[i][k - 1]) {
							canVisit[i][k - 1] = true;
						}
						if (valid(i, k + 1) && lit[i][k + 1]) {
							canVisit[i][k + 1] = true;
						}
					}
				}
			}
			if (numLit == 0)
				break;
		}
		return totalLit;
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			for (int i : is)
				System.out.print(i + " ");
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

	public static boolean valid(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}
