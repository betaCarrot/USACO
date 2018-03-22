import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class mowing {
	private static final int MAX = 2002;
	private static int time = 1;
	private static int[][] map = new int[MAX][MAX];
	private static int currentRow = 1000;
	private static int currentCol = 1000;
	private static int N;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("mowing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mowing.out")));
		N = Integer.parseInt(f.readLine());
		map[1000][1000] = 1;
		for (int iter = 0; iter < N; iter++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			String direction = st.nextToken();
			int steps = Integer.parseInt(st.nextToken());
			if (direction.equals("N")) {
				for (int i = 0; i < steps; i++) {
					currentRow--;
					time++;
					if (map[currentRow][currentCol] != 0)
						result = Math.min(result, time - map[currentRow][currentCol]);
					map[currentRow][currentCol] = time;
				}
			} else if (direction.equals("S")) {
				for (int i = 0; i < steps; i++) {
					currentRow++;
					time++;
					if (map[currentRow][currentCol] != 0)
						result = Math.min(result, time - map[currentRow][currentCol]);
					map[currentRow][currentCol] = time;
				}
			} else if (direction.equals("W")) {
				for (int i = 0; i < steps; i++) {
					currentCol--;
					time++;
					if (map[currentRow][currentCol] != 0)
						result = Math.min(result, time - map[currentRow][currentCol]);
					map[currentRow][currentCol] = time;
				}
			} else {
				for (int i = 0; i < steps; i++) {
					currentCol++;
					time++;
					if (map[currentRow][currentCol] != 0)
						result = Math.min(result, time - map[currentRow][currentCol]);
					map[currentRow][currentCol] = time;
				}
			}
		}
		if (result == Integer.MAX_VALUE)
			result = -1;
		out.println(result);
		out.close();
	}
}
