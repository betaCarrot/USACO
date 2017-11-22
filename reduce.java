import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class reduce {
	private static int[] xs;
	private static int[] ys;
	private static int[][] cows;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("reduce.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
		N = Integer.parseInt(f.readLine());
		xs = new int[N];
		ys = new int[N];
		cows = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			xs[i] = x;
			ys[i] = y;
			cows[i][0] = x;
			cows[i][1] = y;
		}
		int result = Integer.MAX_VALUE;
		Arrays.sort(xs);
		Arrays.sort(ys);
		for (int a = 0; a <= 1; a++) {
			int minX = xs[a];
			for (int b = 0; b <= 1; b++) {
				int minY = ys[b];
				for (int c = 0; c <= 1; c++) {
					int maxX = xs[N - 1 - c];
					for (int d = 0; d <= 1; d++) {
						int maxY = ys[N - 1 - d];
						if (valid(minX, minY, maxX, maxY)) {
							result = Math.min(result, area(minX, minY, maxX, maxY));
						}
					}
				}
			}
		}
		out.println(result);
		out.close();
	}

	public static boolean valid(int minX, int minY, int maxX, int maxY) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (cows[i][0] < minX || cows[i][0] > maxX || cows[i][1] < minY || cows[i][1] > maxY)
				count++;
		}
		return count <= 1;
	}

	public static int area(int minX, int minY, int maxX, int maxY) {
		return (maxX - minX) * (maxY - minY);
	}
}