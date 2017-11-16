import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class cowart {
	private static final int RED = 1;
	private static final int GREEN = 2;
	private static final int BLUE = 3;
	private static int[][] map;
	private static int[][] components;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowart.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowart.out")));
		N = Integer.parseInt(f.readLine());
		map = new int[N][N];
		components = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int k = 0; k < N; k++) {
				if (line.substring(k, k + 1).equals("R"))
					map[i][k] = RED;
				else if (line.substring(k, k + 1).equals("G"))
					map[i][k] = GREEN;
				else
					map[i][k] = BLUE;
			}
		}
		int human = findComponents();
		components = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				if (map[i][k] == RED)
					map[i][k] = GREEN;
			}
		}
		int cow = findComponents();
		out.println(human + " " + cow);
		out.close();
	}

	public static void floodFill(int numComponents) {
		while (true) {
			int numVisited = 0;
			for (int i = 0; i < N; i++) {
				for (int k = 0; k < N; k++) {
					if (components[i][k] == -2) {
						numVisited++;
						components[i][k] = numComponents;
						if (valid(i - 1, k) && map[i - 1][k] == map[i][k] && components[i - 1][k] == 0) {
							components[i - 1][k] = -2;
						}
						if (valid(i + 1, k) && map[i + 1][k] == map[i][k] && components[i + 1][k] == 0) {
							components[i + 1][k] = -2;
						}
						if (valid(i, k - 1) && map[i][k - 1] == map[i][k] && components[i][k - 1] == 0) {
							components[i][k - 1] = -2;
						}
						if (valid(i, k + 1) && map[i][k + 1] == map[i][k] && components[i][k + 1] == 0) {
							components[i][k + 1] = -2;
						}
					}
				}
			}
			if (numVisited == 0)
				break;
		}
	}

	public static int findComponents() {
		int numComponents = 0;
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				if (components[i][k] == 0) {
					numComponents++;
					components[i][k] = -2;
					floodFill(numComponents);
				}
			}
		}
		return numComponents;
	}

	public static boolean valid(int y, int x) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
