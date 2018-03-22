import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class perimeter {
	private static int N, result;
	private static HashSet<pair> bales = new HashSet<pair>();
	private static PriorityQueue<pair> queue = new PriorityQueue<pair>();
	private static HashSet<pair> visited = new HashSet<pair>();
	private static int startX, startY, X, Y;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("perimeter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
		N = Integer.parseInt(f.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			queue.offer(new pair(x, y));
			bales.add(new pair(x, y));
		}
		startX = X = queue.peek().getX() - 1;
		startY = Y = queue.peek().getY() - 1;
		bfs();
		out.println(result);
		out.close();
	}

	private static void bfs() {
		ArrayDeque<pair> queue = new ArrayDeque<pair>();
		queue.offer(new pair(startX, startY));
		while (!queue.isEmpty()) {
			pair curr = queue.poll();
			if (visited.contains(curr))
				continue;
			visited.add(curr);
			int x = curr.getX();
			int y = curr.getY();
			if (isAlone(x, y))
				continue;
			if (bales.contains(new pair(x - 1, y)))
				result++;
			else
				queue.offer(new pair(x - 1, y));
			if (bales.contains(new pair(x + 1, y)))
				result++;
			else
				queue.offer(new pair(x + 1, y));
			if (bales.contains(new pair(x, y - 1)))
				result++;
			else
				queue.offer(new pair(x, y - 1));
			if (bales.contains(new pair(x, y + 1)))
				result++;
			else
				queue.offer(new pair(x, y + 1));
		}
	}

	private static boolean isAlone(int x, int y) {
		if (bales.contains(new pair(x - 1, y)))
			return false;
		if (bales.contains(new pair(x - 1, y - 1)))
			return false;
		if (bales.contains(new pair(x - 1, y + 1)))
			return false;
		if (bales.contains(new pair(x, y - 1)))
			return false;
		if (bales.contains(new pair(x, y + 1)))
			return false;
		if (bales.contains(new pair(x + 1, y)))
			return false;
		if (bales.contains(new pair(x + 1, y - 1)))
			return false;
		if (bales.contains(new pair(x + 1, y + 1)))
			return false;
		return true;
	}
}

class pair implements Comparable<pair> {
	private int x;
	private int y;

	public pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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
		pair other = (pair) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public int compareTo(pair next) {
		if (Integer.compare(x, next.x) != 0) {
			return Integer.compare(x, next.x);
		}
		return Integer.compare(y, next.y);
	}
}