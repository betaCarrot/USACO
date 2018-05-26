
/*
ID: majesti2
LANG: JAVA
TASK: telecow
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class telecow {
	public static int SOURCE, SINK, SIZE, N, M;
	public static int[][] capacity, original;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("telecow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("telecow.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		SIZE = 2 * N;
		SOURCE = Integer.parseInt(st.nextToken()) - 1;
		SINK = Integer.parseInt(st.nextToken()) - 1;
		SOURCE = SOURCE * 2 + 1;
		SINK = SINK * 2;
		capacity = new int[SIZE][SIZE];
		original = new int[SIZE][SIZE];
		for (int i = 0; i < N; i++) {
			capacity[i * 2][i * 2 + 1] = capacity[i * 2 + 1][i * 2] = 1;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			capacity[a * 2 + 1][b * 2] = capacity[b * 2 + 1][a * 2] = 1 << 29;
		}
		record();
		int res1 = maxFlow();
		out.println(res1);
		restore();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			record();
			capacity[i * 2][i * 2 + 1] = capacity[i * 2 + 1][i * 2] = 0;
			int flow = maxFlow();
			restore();
			if (flow < res1) {
				capacity[i * 2][i * 2 + 1] = capacity[i * 2 + 1][i * 2] = 0;
				list.add(i + 1);
				res1 = flow;
			} else {
				capacity[i * 2][i * 2 + 1] = capacity[i * 2 + 1][i * 2] = 1;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			if (i != list.size() - 1) {
				sb.append(" ");
			}
		}
		out.println(sb.toString());
		out.close();
	}

	public static void restore() {
		for (int i = 0; i < SIZE; i++) {
			for (int k = 0; k < SIZE; k++) {
				capacity[i][k] = original[i][k];
			}
		}
	}

	public static void record() {
		for (int i = 0; i < SIZE; i++) {
			for (int k = 0; k < SIZE; k++) {
				original[i][k] = capacity[i][k];
			}
		}
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
