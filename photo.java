import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class photo {
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("photo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("photo.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		PriorityQueue<pair> pairs = new PriorityQueue<pair>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(f.readLine());
			pairs.offer(new pair(Integer.parseInt((st.nextToken())), Integer.parseInt((st.nextToken()))));
		}
		int count = 1;
		int lastFirst = 0;
		while (!pairs.isEmpty()) {
			int minSecond = Integer.MAX_VALUE;
			while (!pairs.isEmpty() && pairs.peek().getFirst() < minSecond) {
				pair p = pairs.poll();
				lastFirst = Math.max(lastFirst, p.getFirst());
				minSecond = Math.min(minSecond, p.getSecond());
			}
			count++;
		}
		out.println(count);
		out.close();
	}
}

class pair implements Comparable<pair> {
	int first;
	int second;

	public pair(int a, int b) {
		first = Math.min(a, b);
		second = Math.max(a, b);
	}

	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}

	public int compareTo(pair next) {
		return Integer.compare(first, next.first);
	}
}
