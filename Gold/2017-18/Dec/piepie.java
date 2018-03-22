import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class piepie {
	private static int N, D;
	private static int[] results;
	private static pie[] bs, es;

	private static Comparator<pie> bcomp = new Comparator<pie>() {
		public int compare(pie p1, pie p2) {
			if (p1.B == p2.B && p1.E == p2.E)
				return 0;
			if (p1.B == p2.B) {
				return Integer.compare(p1.E, p2.E);
			}
			return Integer.compare(p1.B, p2.B);
		}
	};
	private static Comparator<pie> ecomp = new Comparator<pie>() {
		public int compare(pie p1, pie p2) {
			if (p1.E == p2.E) {
				return Integer.compare(p1.B, p2.B);
			}
			return Integer.compare(p1.E, p2.E);
		}
	};

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("piepie.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("piepie.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		bs = new pie[N];
		es = new pie[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int B = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			bs[i] = new pie(i, B, E);
		}
		Arrays.sort(bs, ecomp);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int B = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			es[i] = new pie(i, B, E);
		}
		Arrays.sort(es, bcomp);
		results = new int[N];
		for (int i = 0; i < N; i++) {
			results[i] = Integer.MAX_VALUE;
		}
		bfsB();
		bfsE();
		int[] output = new int[N];
		for (int i = 0; i < N; i++) {
			int index = bs[i].index;
			output[index] = results[i];
		}
		StringBuilder sb = new StringBuilder();
		for (int i : output) {
			if (i == Integer.MAX_VALUE) {
				sb.append(-1).append("\n");
			} else
				sb.append(i + 1).append("\n");
		}
		out.print(sb.toString());
		out.close();
	}

	public static void bfsE() {
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		for (int i = 0; i < N; i++) {
			if (es[i].B == 0) {
				queue.offer(i);
			}
		}
		boolean[] visitedE = new boolean[N];
		boolean[] visitedB = new boolean[N];
		int count = 1;
		while (!queue.isEmpty()) {
			ArrayDeque<Integer> temp = new ArrayDeque<Integer>();
			while (!queue.isEmpty()) {
				int index = queue.poll();
				if (visitedE[index]) {
					continue;
				}
				visitedE[index] = true;
				int low = -Arrays.binarySearch(bs, new pie(0, -Integer.MAX_VALUE, es[index].E - D), ecomp) - 1;
				int high = -Arrays.binarySearch(bs, new pie(0, Integer.MAX_VALUE, es[index].E), ecomp) - 1;
				for (int i = low; i < high; i++) {
					if (!visitedB[i]) {
						temp.add(i);
					}
				}
			}
			while (!temp.isEmpty()) {
				int index = temp.poll();
				if (visitedB[index]) {
					continue;
				}
				results[index] = Math.min(count, results[index]);
				visitedB[index] = true;
				int low = -Arrays.binarySearch(es, new pie(0, bs[index].B - D, -Integer.MAX_VALUE), bcomp) - 1;
				int high = -Arrays.binarySearch(es, new pie(0, bs[index].B, Integer.MAX_VALUE), bcomp) - 1;
				for (int i = low; i < high; i++) {
					if (!visitedE[i])
						queue.add(i);
				}
			}
			count += 2;
		}
	}

	public static void bfsB() {
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		for (int i = 0; i < N; i++) {
			if (bs[i].E == 0) {
				queue.offer(i);
			}
		}
		boolean[] visitedB = new boolean[N];
		boolean[] visitedE = new boolean[N];
		int count = 0;
		while (!queue.isEmpty()) {
			ArrayDeque<Integer> temp = new ArrayDeque<Integer>();
			while (!queue.isEmpty()) {
				int index = queue.poll();
				if (visitedB[index]) {
					continue;
				}
				results[index] = count;
				visitedB[index] = true;
				int low = -Arrays.binarySearch(es, new pie(0, bs[index].B - D, -Integer.MAX_VALUE), bcomp) - 1;
				int high = -Arrays.binarySearch(es, new pie(0, bs[index].B, Integer.MAX_VALUE), bcomp) - 1;
				for (int i = low; i < high; i++) {
					if (!visitedE[i]) {
						temp.add(i);
					}
				}
			}
			while (!temp.isEmpty()) {
				int index = temp.poll();
				if (visitedE[index]) {
					continue;
				}
				visitedE[index] = true;
				int low = -Arrays.binarySearch(bs, new pie(0, -Integer.MAX_VALUE, es[index].E - D), ecomp) - 1;
				int high = -Arrays.binarySearch(bs, new pie(0, Integer.MAX_VALUE, es[index].E), ecomp) - 1;
				for (int i = low; i < high; i++) {
					if (!visitedB[i])
						queue.add(i);
				}
			}
			count += 2;
		}
	}
}

class pie {
	public int B, E, index;

	public pie(int i, int a, int b) {
		B = a;
		E = b;
		index = i;
	}

	public String toString() {
		return B + " " + E;
	}
}
