import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class decorate {
	private static ArrayList<Integer> counts = new ArrayList<Integer>();
	private static int[] signs;
	private static boolean[] visited;
	private static int N, M, count, total;
	private static pathInc[] pathIncs;
	private static pathDec[] pathDecs;

	private static final int F = 1;
	private static final int J = -1;

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("decorate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("decorate.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
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
		signs = new int[N];
		visited = new boolean[N];
		solve();
		int result = 0;
		for (int i : counts) {
			if (i == -1) {
				result = -1;
				break;
			}
			result += i;
		}
		out.println(result);
		out.close();
		long runTime = System.currentTimeMillis() - startTime;
		System.out.println(runTime * 6);
	}

	public static void solve() {
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				dfs(i, F);
				if (count == -1) {
					counts.add(-1);
					continue;
				}
				int result = Math.max(count, total - count);
				signs = new int[N];
				count = 0;
				total = 0;
				counts.add(result);
			}
		}
	}

	public static void dfs(int i, int type) {
		visited[i] = true;
		signs[i] = type;
		total++;
		if (type == J)
			count++;
		int incIndexLow = -Arrays.binarySearch(pathIncs, new pathInc(i, i)) - 1;
		int incIndexHigh = -Arrays.binarySearch(pathIncs, new pathInc(i, Integer.MAX_VALUE)) - 1;
		for (int index = incIndexLow; index < incIndexHigh; index++) {
			int next = pathIncs[index].getB();
			if (signs[next] == type) {
				count = -1;
				return;
			}
			if (!visited[next])
				dfs(next, -type);
		}
		int decIndexLow = -Arrays.binarySearch(pathDecs, new pathDec(i, i)) - 1;
		int decIndexHigh = -Arrays.binarySearch(pathDecs, new pathDec(i, Integer.MIN_VALUE)) - 1;
		for (int index = decIndexLow; index < decIndexHigh; index++) {
			int next = pathDecs[index].getB();
			if (signs[next] == type) {
				count = -1;
				return;
			}
			if (!visited[next])
				dfs(next, -type);
		}
	}

	public static void printArray(int[] array) {
		for (int i : array)
			System.out.print(i + " ");
		System.out.println();
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
