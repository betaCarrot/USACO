import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class milktemp {
	private static cow[] cows;
	private static int N, X, Y, Z;

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new FileReader("milktemp.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milktemp.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		Z = Integer.parseInt(st.nextToken());
		cows = new cow[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			cows[i] = new cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(cows);
		int best = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int above = 0;
		for (cow c : cows) {
			while (!pq.isEmpty() && pq.peek() < c.getA()) {
				pq.poll();
				above++;
			}
			pq.offer(c.getB());
			int middle = pq.size();
			int below = N - above - middle;
			best = Math.max(best, calculate(below, middle, above));
		}
		out.println(best);
		out.close();
	}

	private static int calculate(int below, int middle, int above) {
		return X * below + Y * middle + Z * above;
	}
}

class cow implements Comparable<cow> {
	private int a;
	private int b;

	public cow(int x, int y) {
		a = x;
		b = y;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int compareTo(cow next) {
		if (a > next.a)
			return 1;
		else if (a < next.a)
			return -1;
		else {
			if (b > next.b)
				return 1;
			else if (b < next.b)
				return -1;
			return 0;
		}
	}

	public String toString() {
		return a + " " + b;
	}
}
