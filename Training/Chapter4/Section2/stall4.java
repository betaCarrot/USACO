
/*
ID: majesti2
LANG: JAVA
TASK: stall4
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class stall4 {
	private static int N, M, SIZE, SINK, SOURCE;
	private static int[][] capacity;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("stall4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		SIZE = N + M + 2;
		SINK = SIZE - 1;
		SOURCE = 0;
		capacity = new int[SIZE][SIZE];
		for (int i = 1; i <= N; i++) {
			capacity[0][i] = 1;
			st = new StringTokenizer(f.readLine());
			int K = Integer.parseInt(st.nextToken());
			for (int k = 0; k < K; k++) {
				int stall = Integer.parseInt(st.nextToken()) + N;
				capacity[i][stall] = 1;
				capacity[stall][SINK] = 1;
			}
		}
		out.println(maxFlow());
		out.close();
	}

	public static int maxFlow() {
		if (SOURCE == SINK)
			return 1 << 29;
		int[] flow = new int[SIZE];
		int[] prev = new int[SIZE];
		boolean[] visited = new boolean[SIZE];
		int totalFlow = 0;
		while (true) {
			Arrays.fill(flow, 0);
			Arrays.fill(prev, -1);
			Arrays.fill(visited, false);
			flow[SOURCE] = 1 << 29;
			while (true) {
				int maxFlow = 0;
				int maxLoc = -1;
				for (int i = 0; i < SIZE; i++) {
					if (flow[i] > maxFlow && !visited[i]) {
						maxFlow = flow[i];
						maxLoc = i;
					}
				}
				if (maxLoc == -1) {
					return totalFlow;
				}
				if (maxLoc == SINK) {
					break;
				}
				visited[maxLoc] = true;
				for (int i = 0; i < SIZE; i++) {
					if (flow[i] < Math.min(maxFlow, capacity[maxLoc][i])) {
						prev[i] = maxLoc;
						flow[i] = Math.min(maxFlow, capacity[maxLoc][i]);
					}
				}
			}
			int pathCapacity = flow[SINK];
			totalFlow += pathCapacity;
			int curr = SINK;
			while (curr != SOURCE) {
				int next = prev[curr];
				capacity[next][curr] -= pathCapacity;
				capacity[curr][next] += pathCapacity;
				curr = next;
			}
		}
	}
}
