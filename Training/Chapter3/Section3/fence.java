
/*
 ID: majesti2
 LANG: JAVA
 TASK: fence
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class fence {
	private static int N;
	private static final int MAX = 502;
	private static int[] degrees;
	private static int[][] edges;
	private static int start, circuitPos;
	private static int[] circuit;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fence.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));
		N = Integer.parseInt(f.readLine());
		edges = new int[MAX][MAX];
		degrees = new int[MAX];
		circuit = new int[N + 1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a][b]++;
			edges[b][a]++;
			degrees[a]++;
			degrees[b]++;
		}
		start = findStart();
		findCircuit(start);
		StringBuilder sb = new StringBuilder();
		for (int i = circuit.length - 1; i >= 0; i--) {
			sb.append(circuit[i]).append("\n");
		}
		out.print(sb.toString());
		out.close();
	}

	public static void findCircuit(int i) {
		if (degrees[i] == 0) {
			circuit[circuitPos] = i;
			circuitPos++;
		} else {
			while (degrees[i] > 0) {
				int j = firstIndex(edges[i]);
				edges[i][j]--;
				edges[j][i]--;
				degrees[i]--;
				degrees[j]--;
				findCircuit(j);
			}
			circuit[circuitPos] = i;
			circuitPos++;
		}
	}

	public static int findStart() {
		int min = -1;
		for (int i = 0; i < MAX; i++) {
			if (degrees[i] % 2 == 1)
				return i;
			if (min == -1 && degrees[i] != 0) {
				min = i;
			}
		}
		return min;
	}

	public static int firstIndex(int[] input) {
		for (int i = 0; i < input.length; i++) {
			if (input[i] > 0)
				return i;
		}
		return -1;
	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
