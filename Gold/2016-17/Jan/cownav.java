import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class cownav {
	private static int N;
	private static boolean[][] bales;
	private static int[] dx = new int[] { 0, 1, 0, -1 };
	private static int[] dy = new int[] { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cownav.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));
		N = Integer.parseInt(f.readLine());
		bales = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int k = 0; k < N; k++) {
				if (line.substring(k, k + 1).equals("H"))
					bales[N - 1 - i][k] = true;
			}
		}
		out.println(bfs());
		out.close();
	}

	public static int bfs() {
		boolean[][][][][][] visited = new boolean[N][N][4][N][N][4];
		ArrayDeque<node> queue = new ArrayDeque<node>();
		int count = 0;
		queue.offer(new node(0, 0, 0, 0, 0, 1));
		while (!queue.isEmpty()) {
			ArrayDeque<node> temp = new ArrayDeque<node>();
			while (!queue.isEmpty()) {
				node curr = queue.poll();
				int ax = curr.ax;
				int ay = curr.ay;
				int ad = curr.ad;
				int bx = curr.bx;
				int by = curr.by;
				int bd = curr.bd;
				if (ax == N - 1 && ay == N - 1 && bx == N - 1 && by == N - 1) {
					return count;
				}
				if (visited[ax][ay][ad][bx][by][bd])
					continue;
				visited[ax][ay][ad][bx][by][bd] = true;
				temp.offer(new node(ax, ay, (ad + 3) % 4, bx, by, (bd + 3) % 4));
				temp.offer(new node(ax, ay, (ad + 1) % 4, bx, by, (bd + 1) % 4));
				int nextAX = ax + dx[ad];
				int nextAY = ay + dy[ad];
				int nextBX = bx + dx[bd];
				int nextBY = by + dy[bd];
				if (!valid(nextAX, nextAY) || bales[nextAX][nextAY] || ax == N - 1 && ay == N - 1) {
					nextAX = ax;
					nextAY = ay;
				}
				if (!valid(nextBX, nextBY) || bales[nextBX][nextBY] || bx == N - 1 && by == N - 1) {
					nextBX = bx;
					nextBY = by;
				}
				temp.offer(new node(nextAX, nextAY, ad, nextBX, nextBY, bd));
			}
			queue.addAll(temp);
			count++;
		}
		return -1;
	}

	public static boolean valid(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}

class node {
	public int ax, ay, ad, bx, by, bd;

	public node(int a, int b, int c, int d, int e, int f) {
		ax = a;
		ay = b;
		ad = c;
		bx = d;
		by = e;
		bd = f;
	}
}
