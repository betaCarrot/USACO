import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class promote {
	private static int MAX = 1000001;
	private static int N;
	private static int[] values, results;
	private static node[] nodes;
	private static BIT bit = new BIT(MAX);
	private static ArrayList<ArrayList<Integer>> children = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(f.readLine());
		values = new int[N];
		nodes = new node[N];
		results = new int[N];
		for (int i = 0; i < N; i++) {
			children.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < N; i++) {
			int v = Integer.parseInt(f.readLine());
			nodes[i] = new node(i, v);
		}
		for (int i = 1; i < N; i++) {
			int to = Integer.parseInt(f.readLine()) - 1;
			children.get(to).add(i);
		}
		Arrays.sort(nodes);
		for (int i = 0; i < N; i++) {
			values[nodes[i].index] = i;
		}
		dfs(0);
		StringBuilder sb = new StringBuilder();
		for (int i : results) {
			sb.append(i).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static void dfs(int index) {
		int pre = bit.query(MAX - 1) - bit.query(values[index]);
		for (int c : children.get(index)) {
			dfs(c);
		}
		int post = bit.query(MAX - 1) - bit.query(values[index]);
		results[index] = post - pre;
		bit.update(values[index], 1);
	}
}

class BIT {
	public int[] tree;

	public BIT(int n) {
		tree = new int[n + 5];
	}

	public void update(int index, int val) {
		index++;
		while (index < tree.length) {
			tree[index] += val;
			index += index & -index;
		}
	}

	public int query(int index) {
		int ret = 0;
		index++;
		while (index > 0) {
			ret += tree[index];
			index -= index & -index;
		}
		return ret;
	}
}

class node implements Comparable<node> {
	public int index, value;

	public node(int a, int b) {
		index = a;
		value = b;
	}

	public int compareTo(node next) {
		if (value == next.value) {
			return index - next.index;
		}
		return value - next.value;
	}
}