import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cownomics {
	private static final int A = 0;
	private static final int C = 1;
	private static final int G = 2;
	private static final int T = 3;
	private static int[][] spots;
	private static int[][] plains;
	private static int N, M;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		spots = new int[N][M];
		plains = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int k = 0; k < M; k++) {
				String s = line.substring(k, k + 1);
				if (s.equals("A"))
					spots[i][k] = A;
				else if (s.equals("C"))
					spots[i][k] = C;
				else if (s.equals("G"))
					spots[i][k] = G;
				else
					spots[i][k] = T;
			}
		}
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int k = 0; k < M; k++) {
				String s = line.substring(k, k + 1);
				if (s.equals("A"))
					plains[i][k] = A;
				else if (s.equals("C"))
					plains[i][k] = C;
				else if (s.equals("G"))
					plains[i][k] = G;
				else
					plains[i][k] = T;
			}
		}
		for (int k = 0; k < M; k++) {
			boolean[] gSpots = new boolean[4];
			boolean[] gPlains = new boolean[4];
			for (int i = 0; i < N; i++) {
				gSpots[spots[i][k]] = true;
				gPlains[plains[i][k]] = true;
			}
			if (check(gSpots, gPlains))
				result++;
		}
		out.println(result);
		out.close();
	}

	public static boolean check(boolean[] as, boolean[] bs) {
		for (int i = 0; i <= 3; i++) {
			if (as[i] == bs[i] && as[i] == true)
				return false;
		}
		return true;
	}
}
