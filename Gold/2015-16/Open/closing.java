import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class closing {
	private static int N, M;
	private static int[] parent;
	private static boolean[] open;
	private static ArrayList<Integer> inputs = new ArrayList<Integer>();
	private static int[] order;
	private static ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("closing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N];
		open = new boolean[N];
		for (int iter = 0; iter < N; iter++) {
			edges.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			edges.get(a).add(b);
			edges.get(b).add(a);
		}
		for (int i = 0; i < N; i++) {
			inputs.add(Integer.parseInt(f.readLine()) - 1);
		}
		Collections.reverse(inputs);
		order = new int[N];
		for (int i = 0; i < N; i++) {
			order[i] = inputs.get(i);
		}
		int nodeConnected = 1;
		ArrayList<String> output = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for (int iter = 0; iter < N; iter++) {
			int i = order[iter];
			open[i] = true;
			for (int j : edges.get(i)) {
				if (open[j])
					nodeConnected += join(i, j);
			}
			if (nodeConnected == (iter + 1)) {
				output.add("YES");
			} else {
				output.add("NO");
			}
		}
		Collections.reverse(output);
		for (String s : output) {
			sb.append(s).append("\n");
		}
		out.print(sb.toString());
		out.close();
		System.out.println(System.currentTimeMillis() - startTime);
	}

	public static int find(int target) {
		int parentIndex = target;
		while (parent[parentIndex] != parentIndex) {
			parentIndex = parent[parentIndex];
		}
		int i = target;
		while (i != parentIndex) {
			i = parent[i];
			parent[i] = parentIndex;
		}
		return parentIndex;
	}

	public static int join(int target1, int target2) {
		int root1 = find(target1);
		int root2 = find(target2);
		if (root1 != root2) {
			parent[root1] = root2;
			return 1;
		}
		return 0;
	}
}
