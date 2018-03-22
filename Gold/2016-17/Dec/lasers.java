import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

public class lasers {
	private static int N, startX, startY, endX, endY;
	private static post[] postX, postY;
	private static final int UP = 0;
	private static final int RIGHT = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static Comparator<post> compX = new Comparator<post>() {
		public int compare(post post1, post post2) {
			if (post1.x == post2.x) {
				return post1.y - post2.y;
			}
			return post1.x - post2.x;
		}
	};
	private static Comparator<post> compY = new Comparator<post>() {
		public int compare(post post1, post post2) {
			if (post1.y == post2.y) {
				return post1.x - post2.x;
			}
			return post1.y - post2.y;
		}
	};

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lasers.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lasers.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		endX = Integer.parseInt(st.nextToken());
		endY = Integer.parseInt(st.nextToken());
		postX = new post[N + 2];
		postY = new post[N + 2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			postX[i] = new post(x, y);
			postY[i] = new post(x, y);
		}
		postX[N] = new post(endX, endY);
		postY[N] = new post(endX, endY);
		postX[N + 1] = new post(startX, startY);
		postY[N + 1] = new post(startX, startY);
		Arrays.sort(postX, compX);
		Arrays.sort(postY, compY);
		out.println(bfs());
		out.close();
	}

	public static int bfs() {
		HashSet<hashNode> visited = new HashSet<hashNode>();
		ArrayDeque<node> queue = new ArrayDeque<node>();
		queue.offer(new node(startX, startY, 0, UP));
		queue.offer(new node(startX, startY, 0, DOWN));
		queue.offer(new node(startX, startY, 0, LEFT));
		queue.offer(new node(startX, startY, 0, RIGHT));
		while (!queue.isEmpty()) {
			node curr = queue.poll();
			int dir = curr.dir;
			hashNode hNode = new hashNode(curr.x, curr.y, dir);
			if (visited.contains(hNode)) {
				continue;
			}
			visited.add(hNode);
			post p = new post(curr.x, curr.y);
			if (curr.x == endX && curr.y == endY) {
				return curr.count;
			}
			if (dir == UP) {
				int index = postUp(p);
				if (index != -1)
					queue.addFirst(new node(curr.x, index, curr.count, UP));
			}
			if (dir == DOWN) {
				int index = postDown(p);
				if (index != -1)
					queue.addFirst(new node(curr.x, index, curr.count, DOWN));
			}
			if (dir == RIGHT) {
				int index = postRight(p);
				if (index != -1)
					queue.addFirst(new node(index, curr.y, curr.count, RIGHT));
			}
			if (dir == LEFT) {
				int index = postLeft(p);
				if (index != -1)
					queue.addFirst(new node(index, curr.y, curr.count, LEFT));
			}
			if (curr.x == startX && curr.y == startY)
				continue;
			if (curr.dir == UP || curr.dir == DOWN) {
				queue.offer(new node(curr.x, curr.y, curr.count + 1, LEFT));
				queue.offer(new node(curr.x, curr.y, curr.count + 1, RIGHT));
			}
			if (curr.dir == LEFT || curr.dir == RIGHT) {
				queue.offer(new node(curr.x, curr.y, curr.count + 1, UP));
				queue.offer(new node(curr.x, curr.y, curr.count + 1, DOWN));
			}
		}
		return -1;
	}

	public static int postRight(post p) {
		int index = Arrays.binarySearch(postY, p, compY);
		if (index == N + 1)
			return -1;
		if (postY[index + 1].y != p.y) {
			return -1;
		}
		return postY[index + 1].x;
	}

	public static int postLeft(post p) {
		int index = Arrays.binarySearch(postY, p, compY);
		if (index == 0)
			return -1;
		if (postY[index - 1].y != p.y) {
			return -1;
		}
		return postY[index - 1].x;
	}

	public static int postUp(post p) {
		int index = Arrays.binarySearch(postX, p, compX);
		if (index == N + 1)
			return -1;
		if (postX[index + 1].x != p.x) {
			return -1;
		}
		return postX[index + 1].y;
	}

	public static int postDown(post p) {
		int index = Arrays.binarySearch(postX, p, compX);
		if (index == 0)
			return -1;
		if (postX[index - 1].x != p.x) {
			return -1;
		}
		return postX[index - 1].y;
	}
}

class hashNode {
	public int x, y, dir;

	public hashNode(int a, int b, int c) {
		x = a;
		y = b;
		dir = c;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dir;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		hashNode other = (hashNode) obj;
		if (dir != other.dir)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public boolean equals(hashNode next) {
		return x == next.x && y == next.y && dir == next.dir;
	}
}

class post {
	public int x, y;

	public post(int a, int b) {
		x = a;
		y = b;
	}

	public String toString() {
		return x + " " + y;
	}
}

class node implements Comparable<node> {
	public int x, y, count, dir;

	public node(int a, int b, int c, int d) {
		x = a;
		y = b;
		count = c;
		dir = d;
	}

	public int compareTo(node next) {
		return count - next.count;
	}
}
