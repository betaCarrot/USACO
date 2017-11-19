import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class piggyback {
	private static pathInc[] pathIncs;
	private static pathDec[] pathDecs;
	private static int[] distances0;
	private static int[] distances1;
	private static int[] distancesN;
	private static int B, E, P, N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("piggyback.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("piggyback.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		B = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		pathIncs = new pathInc[M];
		pathDecs = new pathDec[M];
		distances0 = new int[N];
		distances1 = new int[N];
		distancesN = new int[N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			pathIncs[i] = new pathInc(a, b);
			pathDecs[i] = new pathDec(a, b);
		}
		Arrays.sort(pathIncs);
		Arrays.sort(pathDecs);
		shortestPath0();
		shortestPath1();
		shortestPathN();
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			result = Math.min(result, distances0[i] * B + distances1[i] * E + distancesN[i] * P);
		}
		out.println(result);
		out.close();
	}

	public static void shortestPath0() {
		boolean[] nextToVisit = new boolean[N];
		boolean[] visiting = new boolean[N];
		boolean[] visited = new boolean[N];
		visiting[0] = true;
		int count = 0;
		while (true) {
			int numVisited = 0;
			for (int i = 0; i < N; i++) {
				if (visiting[i]) {
					numVisited++;
					visited[i] = true;
					distances0[i] = count;
					int incIndexLow = -Arrays.binarySearch(pathIncs, new pathInc(i, i)) - 1;
					int incIndexHigh = -Arrays.binarySearch(pathIncs, new pathInc(i, Integer.MAX_VALUE)) - 1;
					for (int index = incIndexLow; index < incIndexHigh; index++) {
						int j = pathIncs[index].getB();
						if (!visited[j]) {
							nextToVisit[j] = true;
						}
					}
					int decIndexLow = -Arrays.binarySearch(pathDecs, new pathDec(i, i)) - 1;
					int decIndexHigh = -Arrays.binarySearch(pathDecs, new pathDec(i, Integer.MIN_VALUE)) - 1;
					for (int index = decIndexLow; index < decIndexHigh; index++) {
						int j = pathDecs[index].getB();
						if (!visited[j]) {
							nextToVisit[j] = true;
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				visiting[i] = nextToVisit[i];
			}
			nextToVisit = new boolean[N];
			count++;
			if (numVisited == 0)
				break;
		}
	}

	public static void shortestPathN() {
		boolean[] nextToVisit = new boolean[N];
		boolean[] visiting = new boolean[N];
		boolean[] visited = new boolean[N];
		visiting[N - 1] = true;
		int count = 0;
		while (true) {
			int numVisited = 0;
			for (int i = 0; i < N; i++) {
				if (visiting[i]) {
					numVisited++;
					visited[i] = true;
					distancesN[i] = count;
					int incIndexLow = -Arrays.binarySearch(pathIncs, new pathInc(i, i)) - 1;
					int incIndexHigh = -Arrays.binarySearch(pathIncs, new pathInc(i, Integer.MAX_VALUE)) - 1;
					for (int index = incIndexLow; index < incIndexHigh; index++) {
						int j = pathIncs[index].getB();
						if (!visited[j]) {
							nextToVisit[j] = true;
						}
					}
					int decIndexLow = -Arrays.binarySearch(pathDecs, new pathDec(i, i)) - 1;
					int decIndexHigh = -Arrays.binarySearch(pathDecs, new pathDec(i, Integer.MIN_VALUE)) - 1;
					for (int index = decIndexLow; index < decIndexHigh; index++) {
						int j = pathDecs[index].getB();
						if (!visited[j]) {
							nextToVisit[j] = true;
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				visiting[i] = nextToVisit[i];
			}
			nextToVisit = new boolean[N];
			count++;
			if (numVisited == 0)
				break;
		}
	}

	public static void shortestPath1() {
		boolean[] nextToVisit = new boolean[N];
		boolean[] visiting = new boolean[N];
		boolean[] visited = new boolean[N];
		visiting[1] = true;
		int count = 0;
		while (true) {
			int numVisited = 0;
			for (int i = 0; i < N; i++) {
				if (visiting[i]) {
					numVisited++;
					visited[i] = true;
					distances1[i] = count;
					int incIndexLow = -Arrays.binarySearch(pathIncs, new pathInc(i, i)) - 1;
					int incIndexHigh = -Arrays.binarySearch(pathIncs, new pathInc(i, Integer.MAX_VALUE)) - 1;
					for (int index = incIndexLow; index < incIndexHigh; index++) {
						int j = pathIncs[index].getB();
						if (!visited[j]) {
							nextToVisit[j] = true;
						}
					}
					int decIndexLow = -Arrays.binarySearch(pathDecs, new pathDec(i, i)) - 1;
					int decIndexHigh = -Arrays.binarySearch(pathDecs, new pathDec(i, Integer.MIN_VALUE)) - 1;
					for (int index = decIndexLow; index < decIndexHigh; index++) {
						int j = pathDecs[index].getB();
						if (!visited[j]) {
							nextToVisit[j] = true;
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				visiting[i] = nextToVisit[i];
			}
			nextToVisit = new boolean[N];
			count++;
			if (numVisited == 0)
				break;
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
