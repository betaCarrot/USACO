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
	private static int[][] map = new int[4][4];
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final int TOP = 2;
	private static final int BOTTOM = 3;
	private static int result = Integer.MAX_VALUE;

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
			xs[i] = x;
			int y = Integer.parseInt(st.nextToken());
			ys[i] = y;
			cows[i][0] = x;
			cows[i][1] = y;
		}
		Arrays.sort(xs);
		Arrays.sort(ys);
		for (int i = 0; i < 4; i++) {
			map[LEFT][i] = xs[i];
		}
		for (int i = 0; i < 4; i++) {
			map[RIGHT][i] = xs[N - 1 - i];
		}
		for (int i = 0; i < 4; i++) {
			map[TOP][i] = ys[i];
		}
		for (int i = 0; i < 4; i++) {
			map[BOTTOM][i] = ys[N - 1 - i];
		}
		fillArray(0, new int[4]);
		out.println(result);
		out.close();
	}

	public static void fillArray(int count, int[] temp) {
		if (count == 4) {
			int minX = map[LEFT][temp[LEFT]];
			int maxX = map[RIGHT][temp[RIGHT]];
			int minY = map[TOP][temp[TOP]];
			int maxY = map[BOTTOM][temp[BOTTOM]];
			if (valid(minX, maxX, minY, maxY)) {
				int area = area(minX, maxX, minY, maxY);
				result = Math.min(result, area);
			}
			return;
		}
		for (int i = 0; i <= 3; i++) {
			temp[count] = i;
			fillArray(count + 1, temp);
		}
	}

	public static int area(int minX, int maxX, int minY, int maxY) {
		return (maxX - minX) * (maxY - minY);
	}

	public static boolean valid(int minX, int maxX, int minY, int maxY) {
		if (maxX < minX || maxY < minY)
			return false;
		int total = 0;
		for (int i = 0; i < N; i++) {
			if (cows[i][0] < minX || cows[i][0] > maxX || cows[i][1] < minY || cows[i][1] > maxY)
				total++;
		}
		return total <= 3;
	}
}
