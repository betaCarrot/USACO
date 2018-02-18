import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class delivery {
	private static int N;
	private static field[] fields;
	private static node[] nodes;
	private static HashSet<field> set = new HashSet<field>();
	private static int[] dx = new int[] { 0, -1, 1, 0, 0 };
	private static int[] dy = new int[] { 0, 0, 0, -1, 1 };
	private static ArrayList<ArrayList<edge>> edges = new ArrayList<ArrayList<edge>>();

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("delivery.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("delivery.out")));
		N = Integer.parseInt(f.readLine());
		fields = new field[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			field fi = new field(x, y);
			fields[i] = fi;
			set.add(fi);
		}
		nodes = new node[5 * N];
		for (int i = 0; i < N; i++) {
			int x = fields[i].x;
			int y = fields[i].y;
			for (int k = 0; k < 5; k++) {
				nodes[i * 5 + k] = new node(x + dx[k], y + dy[k]);
			}
		}
		init();
		out.println(solve());
		out.close();
		System.err.println(System.currentTimeMillis() - startTime);
	}

	public static int solve() {
		int result = 0;
		for (int i = 0; i < N; i++) {
			int distance = distance(i, (i + 1) % N);
			if (distance == Integer.MAX_VALUE) {
				return -1;
			}
			result += distance;
		}
		return result;
	}

	public static int distance(int index1, int index2) {
		int start = index1 * 5;
		int end = index2 * 5;
		int[] distances = new int[5 * N];
		for (int i = 0; i < 5 * N; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		distances[start] = 0;
		PriorityQueue<edge> pq = new PriorityQueue<edge>();
		pq.offer(new edge(start, 0));
		while (!pq.isEmpty()) {
			edge curr = pq.poll();
			int i = curr.to;
			field fi = new field(nodes[i].x, nodes[i].y);
			if (i != start && i != end && set.contains(fi))
				continue;
			int distance = curr.weight;
			for (edge e : edges.get(i)) {
				int j = e.to;
				int weight = e.weight;
				if (distance + weight < distances[j]) {
					distances[j] = distance + weight;
					pq.offer(new edge(j, distances[j]));
				}
			}
		}
		return distances[end];
	}

	public static void init() {
		for (int i = 0; i < 5 * N; i++) {
			edges.add(new ArrayList<edge>());
		}
		for (int i = 0; i < 5 * N; i++) {
			for (int j = 0; j < 5 * N; j++) {
				if (i == j)
					continue;
				if (ok(nodes[i], nodes[j])) {
					edges.get(i).add(new edge(j, distance(nodes[i].x, nodes[i].y, nodes[j].x, nodes[j].y)));
				}
			}
		}
	}

	public static boolean ok(node node1, node node2) {
		int sx = node1.x;
		int sy = node1.y;
		int ex = node2.x;
		int ey = node2.y;
		if (sy == ey) {
			return valid(sx, sy, ex, ey);
		}
		if (sx == ex) {
			return valid(sx, sy, ex, ey);
		}
		boolean ok = true;
		int ix = sx;
		int iy = ey;
		field fi = new field(ix, iy);
		if (set.contains(fi))
			ok = false;
		if (!valid(sx, sy, ix, iy) || !valid(ix, iy, ex, ey)) {
			ok = false;
		}
		if (ok)
			return true;
		ix = ex;
		iy = sy;
		fi = new field(ix, iy);
		if (set.contains(fi))
			return false;
		return valid(sx, sy, ix, iy) && valid(ix, iy, ex, ey);
	}

	public static boolean valid(int x1, int y1, int x2, int y2) {
		for (int i = 0; i < N; i++) {
			if (intersect(i, x1, y1, x2, y2))
				return false;
		}
		return true;
	}

	public static boolean intersect(int index, int x1, int y1, int x2, int y2) {
		int fx = fields[index].x;
		int fy = fields[index].y;
		int minX = Math.min(x1, x2);
		int maxX = Math.max(x1, x2);
		int minY = Math.min(y1, y2);
		int maxY = Math.max(y1, y2);
		if (y1 == y2) {
			if (fy != y1)
				return false;
			return fx > minX && fx < maxX;
		}
		if (fx != x1)
			return false;
		return fy > minY && fy < maxY;
	}

	public static int distance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}

class node {
	public int x, y;

	public node(int a, int b) {
		x = a;
		y = b;
	}
}

class field {
	public int x, y;

	public field(int a, int b) {
		x = a;
		y = b;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		field other = (field) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}

class edge implements Comparable<edge> {
	public int to, weight;

	public edge(int a, int b) {
		to = a;
		weight = b;
	}

	public int compareTo(edge next) {
		return weight - next.weight;
	}
}
