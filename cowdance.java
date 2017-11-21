import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class cowdance {
	private static int[] cows;
	private static int N, MAX;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowdance.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		MAX = Integer.parseInt(st.nextToken());
		cows = new int[N];
		for (int i = 0; i < N; i++) {
			cows[i] = Integer.parseInt(f.readLine());
		}
		out.println(binarySearch());
		out.close();
	}

	public static int binarySearch() {
		int low = 0;
		int high = N;
		while (high != low) {
			int middle = (high + low) / 2;
			if (valid(middle)) {
				high = middle;
			} else {
				low = middle + 1;
			}
		}
		return (high + low) / 2;
	}

	public static boolean valid(int K) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int index = 0;
		int T = 0;
		while (index < K) {
			pq.offer(cows[index]);
			index++;
		}
		while (!pq.isEmpty()) {
			T = pq.poll();
			if (index < N) {
				pq.offer(cows[index] + T);
				index++;
			}
		}
		return T <= MAX;
	}
}
