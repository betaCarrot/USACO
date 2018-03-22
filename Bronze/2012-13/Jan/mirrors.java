import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class mirrors {
	private static mirror[] mirrors;
	private static int a;
	private static int b;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("mirrors.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mirrors.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		mirrors = new mirror[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st1.nextToken());
			int y = Integer.parseInt(st1.nextToken());
			String dir = st1.nextToken();
			if (dir.equals("/"))
				mirrors[i] = new mirror(x, y, 1);
			else
				mirrors[i] = new mirror(x, y, 2);
		}
		if (check(mirrors))
			out.println(0);
		else {
			out.println(solve() + 1);
		}
		out.close();
	}

	public static int solve() {
		for (int i = 0; i < N; i++) {
			mirror[] temp = new mirror[N];
			for (int k = 0; k < N; k++) {
				mirror m = mirrors[k];
				temp[k] = new mirror(m.x, m.y, m.dir);
			}
			temp[i].flipDirection();
			if (check(temp))
				return i;
		}
		return -2;
	}

	public static boolean check(mirror[] temp) {
		int[] visited = new int[N];
		int nextIndex = -2;
		mirror nextMirror = new mirror(0, 0, 1);
		int direction = 1;
		while (true) {
			if (nextIndex != -2) {
				visited[nextIndex]++;
				if (visited[nextIndex] > 2)
					return false;
			}
			if (nextIndex != -2)
				direction = nextDirection(direction, nextMirror);
			nextIndex = nextMirrorIndex(temp, nextMirror, direction);
			if (checkTarget(nextIndex, temp, nextMirror, direction))
				return true;
			if (nextIndex == -1)
				return false;
			nextMirror = temp[nextIndex];
		}
	}

	public static int nextDirection(int direction, mirror nextMirror) {
		int dir = nextMirror.dir;
		if (direction == 1) {
			if (dir == 1)
				return 4;
			else
				return 2;
		} else if (direction == 2) {
			if (dir == 1)
				return 3;
			else
				return 1;
		} else if (direction == 3) {
			if (dir == 1)
				return 2;
			else
				return 4;
		} else {
			if (dir == 1)
				return 1;
			else
				return 3;
		}
	}

	public static int nextMirrorIndex(mirror[] temp, mirror m, int direction) {
		int x = m.x;
		int y = m.y;
		if (direction == 1) {
			int minX = Integer.MAX_VALUE;
			int minIndex = -1;
			for (int i = 0; i < N; i++) {
				if (temp[i].y != y)
					continue;
				if (temp[i].x > x) {
					if (temp[i].x < minX) {
						minX = temp[i].x;
						minIndex = i;
					}
				}
			}
			return minIndex;
		} else if (direction == 3) {
			int maxX = Integer.MIN_VALUE;
			int maxIndex = -1;
			for (int i = 0; i < N; i++) {
				if (temp[i].y != y)
					continue;
				if (temp[i].x < x) {
					if (temp[i].x > maxX) {
						maxX = temp[i].x;
						maxIndex = i;
					}
				}
			}
			return maxIndex;
		} else if (direction == 2) {
			int maxY = Integer.MIN_VALUE;
			int maxIndex = -1;
			for (int i = 0; i < N; i++) {
				if (temp[i].x != x)
					continue;
				if (temp[i].y < y) {
					if (temp[i].y > maxY) {
						maxY = temp[i].y;
						maxIndex = i;
					}
				}
			}
			return maxIndex;
		} else {
			int minY = Integer.MAX_VALUE;
			int minIndex = -1;
			for (int i = 0; i < N; i++) {
				if (temp[i].x != m.x)
					continue;
				if (temp[i].y > y) {
					if (temp[i].y < minY) {
						minY = temp[i].y;
						minIndex = i;
					}
				}
			}
			return minIndex;
		}
	}

	public static boolean checkTarget(int nextMirrorIndex, mirror[] temp, mirror m, int direction) {
		int x = m.x;
		int y = m.y;
		if (nextMirrorIndex == -1) {
			if (direction == 1) {
				return b == y && a > x;
			} else if (direction == 2) {
				return a == x && b < y;
			} else if (direction == 3) {
				return b == y && a < x;
			} else
				return a == x && b > y;
		} else {
			mirror nextMirror = temp[nextMirrorIndex];
			if (direction == 1) {
				return b == y && a > x && a < nextMirror.x;
			} else if (direction == 2) {
				return a == x && b < y && b > nextMirror.y;
			} else if (direction == 3) {
				return b == y && a < x && a > nextMirror.x;
			} else
				return a == x && b > y && b < nextMirror.y;
		}
	}
}

class mirror {
	int x;
	int y;
	int dir;

	public mirror(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public void flipDirection() {
		if (dir == 1)
			dir = 2;
		else
			dir = 1;
	}

	public String toString() {
		return x + " " + y + " " + dir;
	}
}
