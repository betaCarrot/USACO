import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class crossings {
	private static int N;
	private static boolean[] crossed;
	private static path[] paths;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("crossings.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crossings.out")));
		N = Integer.parseInt(f.readLine());
		crossed = new boolean[N];
		paths = new path[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			paths[i] = new path(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(paths);
		int[] maxl = new int[N];
		maxl[0] = paths[0].getR2();
		for (int i = 1; i < N; i++) {
			maxl[i] = Math.max(maxl[i - 1], paths[i].getR2());
		}
		int[] minr = new int[N];
		minr[N - 1] = paths[N - 1].getR2();
		for (int i = N - 2; i >= 0; i--) {
			minr[i] = Math.min(minr[i + 1], paths[i].getR2());
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			if (paths[i].getR2() >= maxl[i] && paths[i].getR2() <= minr[i])
				result++;
		}
		out.println(result);
		out.close();
	}

	public static void naive() {
		for (int i = 0; i < N; i++) {
			if (crossed[i])
				continue;
			path p = paths[i];
			for (int k = i + 1; k < N; k++) {
				if (paths[k].getR2() < p.getR2()) {
					crossed[k] = true;
					crossed[i] = true;
				}
			}
		}
	}
}

class path implements Comparable<path> {
	private int r1;
	private int r2;

	public path(int x, int y) {
		r1 = x;
		r2 = y;
	}

	public int compareTo(path next) {
		return Integer.compare(r1, next.r1);
	}

	public int getR2() {
		return r2;
	}
}
