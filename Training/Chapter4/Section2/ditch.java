
/*
ID: majesti2
LANG: JAVA
TASK: ditch
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ditch {
	private static int N, M;
	private static int[][] capacity;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ditch.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		capacity = new int[M][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int C = Integer.parseInt(st.nextToken());
			capacity[from][to] += C;
		}
		out.println(maxFlow());
		out.close();
	}

	public static int maxFlow() {
		if (0 == M - 1)
			return 1 << 29;
		int[] flow = new int[M];
		int[] prev = new int[M];
		boolean[] visited = new boolean[M];
		int totalFlow = 0;
		while (true) {
			Arrays.fill(flow, 0);
			Arrays.fill(prev, -1);
			Arrays.fill(visited, false);
			flow[0] = 1 << 29;
			while (true) {
				int maxFlow = 0;
				int maxLoc = -1;
				for (int i = 0; i < M; i++) {
					if (flow[i] > maxFlow && !visited[i]) {
						maxFlow = flow[i];
						maxLoc = i;
					}
				}
				if (maxLoc == -1) {
					return totalFlow;
				}
				if (maxLoc == M - 1) {
					break;
				}
				visited[maxLoc] = true;
				for (int i = 0; i < M; i++) {
					if (flow[i] < Math.min(maxFlow, capacity[maxLoc][i])) {
						prev[i] = maxLoc;
						flow[i] = Math.min(maxFlow, capacity[maxLoc][i]);
					}
				}
			}
			int pathCapacity = flow[M - 1];
			totalFlow += pathCapacity;
			int curr = M - 1;
			while (curr != 0) {
				int next = prev[curr];
				capacity[next][curr] -= pathCapacity;
				capacity[curr][next] += pathCapacity;
				curr = next;
			}
		}
	}
}
