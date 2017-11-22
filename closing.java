import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class closing {
	private static boolean[] closed;
	private static boolean[] dfsVisited;
	private static int total = 0;
	private static int numLeft;
	private static int lastUnclosed = 0;
	private static int N, M;
	private static pathInc[] pathIncs;
	private static pathDec[] pathDecs;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("closing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numLeft = N;
		closed = new boolean[N];
		dfsVisited = new boolean[N];
		pathIncs = new pathInc[M];
		pathDecs = new pathDec[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			pathIncs[i] = new pathInc(a, b);
			pathDecs[i] = new pathDec(a, b);
		}
		Arrays.sort(pathIncs);
		Arrays.sort(pathDecs);
		StringBuilder sb = new StringBuilder();
		dfs(0);
		if (total == numLeft) {
			sb.append("YES");
		} else
			sb.append("NO");
		sb.append("\n");
		dfsVisited = new boolean[N];
		total = 0;
		for (int i = 0; i < N - 1; i++) {
			int next = Integer.parseInt(f.readLine()) - 1;
			closed[next] = true;
			while (closed[lastUnclosed]) {
				lastUnclosed++;
			}
			numLeft--;
			dfs(lastUnclosed);
			if (total == numLeft) {
				sb.append("YES");
			} else
				sb.append("NO");
			sb.append("\n");
			total = 0;
			dfsVisited = new boolean[N];
		}
		out.print(sb.toString());
		out.close();
	}

	public static void dfs(int i) {
		total++;
		dfsVisited[i] = true;
		int incIndexLow = -Arrays.binarySearch(pathIncs, new pathInc(i, i)) - 1;
		int incIndexHigh = -Arrays.binarySearch(pathIncs, new pathInc(i, Integer.MAX_VALUE)) - 1;
		int decIndexLow = -Arrays.binarySearch(pathDecs, new pathDec(i, i)) - 1;
		int decIndexHigh = -Arrays.binarySearch(pathDecs, new pathDec(i, Integer.MIN_VALUE)) - 1;
		for (int index = incIndexLow; index < incIndexHigh; index++) {
			int j = pathIncs[index].getB();
			if (!closed[j] && !dfsVisited[j]) {
				dfs(j);
			}
		}
		for (int index = decIndexLow; index < decIndexHigh; index++) {
			int j = pathDecs[index].getB();
			if (!closed[j] && !dfsVisited[j]) {
				dfs(j);
			}
		}
	}
}

class pathInc implements Comparable<pathInc> {
	private int a;
	private int b;

	public pathInc(int a, int b) {
		this.a = Math.min(a, b);
		this.b = Math.max(a, b);
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public String toString() {
		return a + " " + b;
	}

	public int compareTo(pathInc next) {
		if (Integer.compare(a, next.a) != 0) {
			return Integer.compare(a, next.a);
		}
		return Integer.compare(b, next.b);
	}
}

class pathDec implements Comparable<pathDec> {
	private int a;
	private int b;

	public pathDec(int a, int b) {
		this.a = Math.max(a, b);
		this.b = Math.min(a, b);
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public String toString() {
		return a + " " + b;
	}

	public int compareTo(pathDec next) {
		if (Integer.compare(a, next.a) != 0) {
			return -Integer.compare(a, next.a);
		}
		return -Integer.compare(b, next.b);
	}
}
