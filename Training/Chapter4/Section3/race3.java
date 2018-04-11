
/*
ID: majesti2
LANG: JAVA
TASK: race3
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class race3 {
	public static int N;
	public static final int MAX = 51;
	public static ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
	public static int end;
	public static boolean valid;
	public static boolean[] visited;
	public static boolean[][] connected = new boolean[MAX][MAX];
	public static ArrayList<Integer> list1 = new ArrayList<Integer>();
	public static ArrayList<Integer> list2 = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("race3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("race3.out")));
		for (int i = 0; i < MAX; i++) {
			edges.add(new ArrayList<Integer>());
		}
		String line;
		while (!(line = f.readLine()).equals("-1")) {
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreElements()) {
				int to = Integer.parseInt(st.nextToken());
				if (to == -2)
					break;
				edges.get(N).add(to);
				connected[N][to] = true;
			}
			N++;
		}
		solve();
		StringBuilder sb = new StringBuilder();
		sb.append(list1.size());
		for (int i : list1) {
			sb.append(" ").append(i);
		}
		sb.append("\n");
		sb.append(list2.size());
		for (int i : list2) {
			sb.append(" ").append(i);
		}
		out.println(sb.toString());
		out.close();
	}

	public static void solve() {
		for (int i = 1; i < N - 1; i++) {
			visited = new boolean[N];
			valid = true;
			end = i;
			dfs(0);
			if (!valid) {
				continue;
			}
			list1.add(i);
			valid = true;
			for (int j : edges.get(i)) {
				if (visited[j])
					valid = false;
			}
			for (int a = 0; a < N; a++) {
				for (int b = a + 1; b < N; b++) {
					if (a == i || b == i)
						continue;
					if (visited[a] && visited[b])
						continue;
					if (!visited[a] && !visited[b])
						continue;
					if (connected[a][b] || connected[b][a]) {
						valid = false;
					}
				}
			}
			if (valid) {
				list2.add(i);
			}
		}
	}

	public static void dfs(int index) {
		if (!valid)
			return;
		if (visited[index])
			return;
		if (index == N - 1) {
			valid = false;
			return;
		}
		if (index == end) {
			return;
		}
		visited[index] = true;
		for (int j : edges.get(index)) {
			dfs(j);
		}
	}
}
