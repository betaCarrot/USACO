import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class msched {
	private static int N, M;
	private static int[] time, required;
	private static ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("msched.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msched.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		time = new int[N];
		required = new int[N];
		for (int i = 0; i < N; i++) {
			edges.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(f.readLine());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			required[b]++;
			edges.get(a).add(b);
		}
		PriorityQueue<node> pq = new PriorityQueue<node>();
		for (int i = 0; i < N; i++) {
			if (required[i] == 0) {
				pq.offer(new node(i, (long) time[i]));
			}
		}
		long result = 0L;
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			long t = curr.getTime();
			result = t;
			for (int i : edges.get(curr.getIndex())) {
				required[i]--;
				if (required[i] == 0) {
					pq.offer(new node(i, t + time[i]));
				}
			}
		}
		out.println(result);
		out.close();
	}
}

class node implements Comparable<node> {
	private int index;
	private long time;

	public node(int i, long t) {
		index = i;
		time = t;
	}

	public int getIndex() {
		return index;
	}

	public long getTime() {
		return time;
	}

	public int compareTo(node next) {
		return Long.compare(time, next.time);
	}
}
