import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class superbull {
	private static int N;
	private static int[] bulls;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("superbull.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("superbull.out")));
		N = Integer.parseInt(f.readLine());
		bulls = new int[N];
		for (int i = 0; i < N; i++) {
			bulls[i] = Integer.parseInt(f.readLine());
		}
		out.println(MST());
		out.close();
	}

	public static long MST() {
		long[] distances = new long[N];
		for (int i = 1; i < N; i++) {
			distances[i] = Long.MAX_VALUE;
		}
		boolean[] inTree = new boolean[N];
		inTree[0] = true;
		int treeSize = 1;
		long treeCost = 0;
		for (int i = 1; i < N; i++) {
			distances[i] = distance(0, i);
		}
		while (treeSize < N) {
			int minIndex = -1;
			for (int i = 1; i < N; i++) {
				if (inTree[i])
					continue;
				if (minIndex == -1 || distances[i] < distances[minIndex]) {
					minIndex = i;
				}
			}
			int i = minIndex;
			treeSize++;
			treeCost += distances[i];
			inTree[i] = true;
			for (int j = 1; j < N; j++) {
				distances[j] = Math.min(distances[j], distance(i, j));
			}
		}
		return -treeCost;
	}

	public static int distance(int a, int b) {
		return -(bulls[a] ^ bulls[b]);
	}
}
