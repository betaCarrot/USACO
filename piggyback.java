import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class piggyback {
	private static int B, E, P, N, M;
	private static ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
	private static int[] distances1, distances2, distancesN;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("piggyback.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("piggyback.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		B = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		distances1 = new int[N];
		distances2 = new int[N];
		distancesN = new int[N];
		for (int i = 0; i < N; i++) {
			edges.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < N; i++) {
			distances1[i] = -1;
			distances2[i] = -1;
			distancesN[i] = -1;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			edges.get(from).add(to);
			edges.get(to).add(from);
		}
		bfs1();
		bfs2();
		bfsN();
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			result = Math.min(result, B * distances1[i] + E * distances2[i] + P * distancesN[i]);
		}
		out.println(result);
		out.close();
	}

	public static void bfs1() {
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(0);
		int count = 0;
		while (!queue.isEmpty()) {
			ArrayDeque<Integer> temp = new ArrayDeque<Integer>();
			while (!queue.isEmpty()) {
				int i = queue.poll();
				if (distances1[i] != -1) {
					continue;
				}
				distances1[i] = count;
				for (int j : edges.get(i)) {
					temp.offer(j);
				}
			}
			queue.addAll(temp);
			count++;
		}
	}

	public static void bfs2() {
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(1);
		int count = 0;
		while (!queue.isEmpty()) {
			ArrayDeque<Integer> temp = new ArrayDeque<Integer>();
			while (!queue.isEmpty()) {
				int i = queue.poll();
				if (distances2[i] != -1) {
					continue;
				}
				distances2[i] = count;
				for (int j : edges.get(i)) {
					temp.offer(j);
				}
			}
			queue.addAll(temp);
			count++;
		}
	}

	public static void bfsN() {
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(N - 1);
		int count = 0;
		while (!queue.isEmpty()) {
			ArrayDeque<Integer> temp = new ArrayDeque<Integer>();
			while (!queue.isEmpty()) {
				int i = queue.poll();
				if (distancesN[i] != -1) {
					continue;
				}
				distancesN[i] = count;
				for (int j : edges.get(i)) {
					temp.offer(j);
				}
			}
			queue.addAll(temp);
			count++;
		}
	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
