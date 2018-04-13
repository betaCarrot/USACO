
/*
ID: majesti2
LANG: JAVA
TASK: milk6
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class milk6 {
	public static final long INF = (long) (1 << 60);
	public static int N, M;
	public static long[][] capacity;
	public static boolean[][] connected;
	public static edge[] edges;
	public static int SOURCE = 0;
	public static boolean[] visited;
	public static int SINK;
	public static int SIZE;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("milk6.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		SINK = N - 1;
		SIZE = N;
		capacity = new long[N][N];
		connected = new boolean[N][N];
		edges = new edge[M];
		visited = new boolean[N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			long weight = Long.parseLong(st.nextToken()) * 1001 + 1;
			edges[i] = new edge(from, to, weight, i + 1);
			capacity[from][to] += weight;
			connected[from][to] = true;
		}
		long flow = maxFlow();
		StringBuilder sb = new StringBuilder();
		long res = flow / 1001;
		long num = flow - res * 1001;
		sb.append(res).append(" ").append(num).append("\n");
		if (res == 0) {
			out.print(sb.toString());
			out.close();
			return;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (num == 1) {
			int ans = -1;
			long min = INF;
			for (edge e : edges) {
				connected[e.from][e.to] = false;
				dfs(0);
				if (!visited[N - 1]) {
					if (e.weight < min) {
						ans = e.index;
						min = e.weight;
					}
				}
				connected[e.from][e.to] = true;
				visited = new boolean[N];
			}
			list.add(ans);
		} else {
			floodFill(0);
			for (edge e : edges) {
				if (visited[e.from] && !visited[e.to]) {
					list.add(e.index);
				}
			}
		}
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			sb.append("\n");
		}
		out.print(sb.toString());
		out.close();
	}

	public static void dfs(int index) {
		visited[index] = true;
		for (int i = 0; i < N; i++) {
			if (!visited[i] && connected[index][i]) {
				dfs(i);
			}
		}
	}

	public static void floodFill(int index) {
		visited[index] = true;
		for (int i = 0; i < N; i++) {
			if (!visited[i] && capacity[index][i] > 0) {
				floodFill(i);
			}
		}
	}

	public static long maxFlow() {
		if (SOURCE == SINK)
			return INF;
		long[] flow = new long[SIZE];
		int[] prev = new int[SIZE];
		boolean[] visited = new boolean[SIZE];
		long totalFlow = 0;
		while (true) {
			Arrays.fill(flow, 0);
			Arrays.fill(prev, -1);
			Arrays.fill(visited, false);
			flow[SOURCE] = INF;
			while (true) {
				long maxFlow = 0;
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
			long pathCapacity = flow[SINK];
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

class edge {
	public int from, to, index;
	public long weight;

	public edge(int a, int b, long c, int d) {
		from = a;
		to = b;
		weight = c;
		index = d;
	}
}
