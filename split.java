import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class split {
	private static int N;
	private static long oArea = 0;
	private static long result = Long.MAX_VALUE;
	private static int[][] inputs;
	private static cow[] cows;
	private static TreeSet<cow> leftSet;
	private static TreeSet<cow> rightSet;
	private static Comparator<cow> comp = new Comparator<cow>() {
		public int compare(cow cow1, cow cow2) {
			if (cow1.y == cow2.y) {
				return cow1.x - cow2.x;
			}
			return cow1.y - cow2.y;
		}
	};

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("split.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
		N = Integer.parseInt(f.readLine());
		cows = new cow[N];
		inputs = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			inputs[i][0] = x;
			inputs[i][1] = y;
		}
		leftSet = new TreeSet<cow>(comp);
		rightSet = new TreeSet<cow>(comp);
		for (int i = 0; i < N; i++) {
			int x = inputs[i][0];
			int y = inputs[i][1];
			cows[i] = new cow(x, y);
			rightSet.add(cows[i]);
		}
		Arrays.sort(cows);
		oArea = area(cows[0].x, rightSet.first().y, cows[N - 1].x, rightSet.last().y);
		for (int i = 0; i < N - 1; i++) {
			leftSet.add(cows[i]);
			rightSet.remove(cows[i]);
			long leftArea = area(cows[0].x, leftSet.first().y, cows[i].x, leftSet.last().y);
			long rightArea = area(cows[i + 1].x, rightSet.first().y, cows[N - 1].x, rightSet.last().y);
			long total = leftArea + rightArea;
			result = Math.min(result, total);
		}
		leftSet = new TreeSet<cow>(comp);
		rightSet = new TreeSet<cow>(comp);
		for (int i = 0; i < N; i++) {
			int y = inputs[i][0];
			int x = inputs[i][1];
			cows[i] = new cow(x, y);
			rightSet.add(cows[i]);
		}
		Arrays.sort(cows);
		for (int i = 0; i < N - 1; i++) {
			leftSet.add(cows[i]);
			rightSet.remove(cows[i]);
			long leftArea = area(cows[0].x, leftSet.first().y, cows[i].x, leftSet.last().y);
			long rightArea = area(cows[i + 1].x, rightSet.first().y, cows[N - 1].x, rightSet.last().y);
			long total = leftArea + rightArea;
			result = Math.min(result, total);
		}
		out.println(oArea - result);
		out.close();
	}

	public static long area(int minX, int minY, int maxX, int maxY) {
		return (long) (maxX - minX) * (long) (maxY - minY);
	}
}

class cow implements Comparable<cow> {
	public int x, y;

	public cow(int a, int b) {
		x = a;
		y = b;
	}

	public int compareTo(cow next) {
		if (x == next.x) {
			return y - next.y;
		}
		return x - next.x;
	}
}
