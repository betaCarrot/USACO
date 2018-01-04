import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class fencedin {
	private static int A, B, N, M, size;
	private static int[] vLengths, hLengths;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fencedin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		size = (N + 1) * (M + 1);
		int[] inputV = new int[N + 1];
		for (int i = 0; i < N; i++) {
			inputV[i] = Integer.parseInt(f.readLine());
		}
		inputV[N] = A;
		Arrays.sort(inputV);
		vLengths = new int[N + 1];
		vLengths[0] = inputV[0];
		for (int i = 1; i <= N; i++) {
			vLengths[i] = inputV[i] - inputV[i - 1];
		}
		int[] inputH = new int[M + 1];
		for (int i = 0; i < M; i++) {
			inputH[i] = Integer.parseInt(f.readLine());
		}
		inputH[M] = B;
		Arrays.sort(inputH);
		hLengths = new int[M + 1];
		hLengths[0] = inputH[0];
		for (int i = 1; i <= M; i++) {
			hLengths[i] = inputH[i] - inputH[i - 1];
		}
		System.out.println(fastMST());
		out.close();
	}

	public static int distance(int input1, int input2) {
		int a = Math.min(input1, input2);
		int b = Math.max(input1, input2);
		if ((b - a) % (N + 1) == 0) {
			int index = a % (N + 1);
			return vLengths[index];
		}
		if (atRight(a) && atLeft(b)) {
			return Integer.MAX_VALUE;
		}
		if ((b - a) == 1) {
			int index = a / (N + 1);
			return hLengths[index];
		}
		return Integer.MAX_VALUE;
	}

	public static long fastMST() {
		PriorityQueue<pair> pq = new PriorityQueue<pair>();
		int[] distance = new int[size];
		boolean[] inTree = new boolean[size];
		for (int i = 0; i < size; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[0] = 0;
		long treeCost = 0L;
		pq.offer(new pair(0, 0));
		while (!pq.isEmpty()) {
			pair curr = pq.poll();
			int minIndex = curr.getVertex();
			int cost = curr.getCost();
			if (inTree[minIndex])
				continue;
			treeCost += cost;
			inTree[minIndex] = true;
			if (!atTop(minIndex)) {
				int next = minIndex - (N + 1);
				if (distance[next] > distance(minIndex, next)) {
					distance[next] = distance(minIndex, next);
					pq.offer(new pair(distance[next], next));
				}
			}
			if (!atBottom(minIndex)) {
				int next = minIndex + (N + 1);
				if (distance[next] > distance(minIndex, next)) {
					distance[next] = distance(minIndex, next);
					pq.offer(new pair(distance[next], next));
				}
			}
			if (!atRight(minIndex)) {
				int next = minIndex + 1;
				if (distance[next] > distance(minIndex, next)) {
					distance[next] = distance(minIndex, next);
					pq.offer(new pair(distance[next], next));
				}
			}
			if (!atLeft(minIndex)) {
				int next = minIndex - 1;
				if (distance[next] > distance(minIndex, next)) {
					distance[next] = distance(minIndex, next);
					pq.offer(new pair(distance[next], next));
				}
			}
		}
		return treeCost;
	}

	public static boolean atTop(int index) {
		return index / (N + 1) == 0;
	}

	public static boolean atBottom(int index) {
		return index / (N + 1) == M;
	}

	public static boolean atRight(int index) {
		return (index + 1) % (N + 1) == 0;
	}

	public static boolean atLeft(int index) {
		return index % (N + 1) == 0;
	}
}

class pair implements Comparable<pair> {
	private int cost;
	private int vertex;

	public pair(int a, int b) {
		cost = a;
		vertex = b;
	}

	public int getCost() {
		return cost;
	}

	public int getVertex() {
		return vertex;
	}

	public int compareTo(pair next) {
		return Integer.compare(cost, next.cost);
	}
}
