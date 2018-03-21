import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class badmilk {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("badmilk.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("badmilk.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 1][M + 1];
		for (int i = 0; i < D; i++) {
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			int row = Integer.parseInt(st1.nextToken());
			int col = Integer.parseInt(st1.nextToken());
			int time = Integer.parseInt(st1.nextToken());
			map[row][col] = time;
		}

		int[] sickArray = new int[M + 1];

		for (int i = 0; i < S; i++) {
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			int person = Integer.parseInt(st1.nextToken());
			int time = Integer.parseInt(st1.nextToken());
			for (int k = 1; k <= M; k++) {
				if (map[person][k] != 0 && map[person][k] < time)
					sickArray[k]++;
			}
		}
		boolean[] badMilks = new boolean[M + 1];
		for (int i = 0; i < sickArray.length; i++) {
			if (sickArray[i] == S)
				badMilks[i] = true;
		}
		int result = 0;
		for (int i = 1; i <= N; i++) {
			for (int k = 0; k <= M; k++) {
				if (map[i][k] != 0) {
					if (badMilks[k]) {
						result++;
						break;
					}
				}
			}
		}
		out.println(result);
		out.close();
	}
}
