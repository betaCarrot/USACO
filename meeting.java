import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class meeting {
	private static int[][] mapB;
	private static int[][] mapE;
	private static int N, M;
	private static ArrayList<Integer> bTimes = new ArrayList<Integer>();
	private static ArrayList<Integer> eTimes = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("meeting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("meeting.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mapB = new int[N][N];
		mapE = new int[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			mapB[row][col] = b;
			mapE[row][col] = e;
		}
		dfsB(0, 0);
		dfsE(0, 0);
		Collections.sort(bTimes);
		Collections.sort(eTimes);
		int result = -1;
		for (int i = 0; i < bTimes.size(); i++) {
			if (Collections.binarySearch(eTimes, bTimes.get(i)) >= 0) {
				result = bTimes.get(i);
				break;
			}
		}
		if (result == -1)
			out.println("IMPOSSIBLE");
		else
			out.println(result);
		out.close();
	}

	public static void dfsB(int index, int time) {
		if (index == N - 1) {
			bTimes.add(time);
			return;
		}
		int[] neighbors = mapB[index];
		for (int i = index + 1; i < N; i++) {
			int timeInc = neighbors[i];
			if (timeInc == 0)
				continue;
			dfsB(i, time + timeInc);
		}
	}

	public static void dfsE(int index, int time) {
		if (index == N - 1) {
			eTimes.add(time);
			return;
		}
		int[] neighbors = mapE[index];
		for (int i = index + 1; i < N; i++) {
			int timeInc = neighbors[i];
			if (timeInc == 0)
				continue;
			dfsE(i, time + timeInc);
		}
	}
}
