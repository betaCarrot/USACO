import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class learning {
	private static final int S = 1;
	private static final int NS = -1;
	private static int N, A, B;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("learning.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("learning.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		PriorityQueue<type> pq = new PriorityQueue<type>();
		type maxType = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			String type = st.nextToken();
			int index = Integer.parseInt(st.nextToken());
			type t = new type(type, index);
			if (maxType == null || t.compareTo(maxType) > 0)
				maxType = t;
			pq.offer(t);
		}
		if (maxType.getIndex() <= A) {
			if (maxType.getType() == S)
				result += (B - A + 1);
		} else if (pq.peek().getIndex() >= B) {
			if (pq.peek().getIndex() == S) {
				result += (B - A + 1);
			}
		} else {
			if (pq.peek().getIndex() >= A) {
				if (pq.peek().getType() == S) {
					result += (pq.peek().getIndex() - A);
				}
			}
			type lastType = pq.poll();
			while (!pq.isEmpty()) {
				type next = pq.poll();
				if (next.getType() == lastType.getType()) {
					if (next.getType() == S) {
						int lower = Math.max(lastType.getIndex(), A);
						int higher = Math.min(next.getIndex(), B);
						if (!(lower > higher))
							result += higher - lower;
					}
					if (next.getIndex() > B && next.getType() == S) {
						result++;
						break;
					}
					lastType = next;
				} else {
					int middle;
					if (lastType.getType() == S) {
						middle = (next.getIndex() + lastType.getIndex()) / 2;
						int lower = Math.max(lastType.getIndex(), A);
						middle = Math.min(middle, B);
						if (!(lower > middle))
							result += middle - lower + 1;
					} else {
						middle = (next.getIndex() + lastType.getIndex() + 1) / 2;
						int higher = Math.min(next.getIndex(), B);
						middle = Math.max(middle, A);
						if (!(higher < middle))
							result += (higher - middle);
					}
					if (next.getIndex() > B && next.getType() == S) {
						if (B >= middle)
							result++;
						break;
					}
					lastType = next;
				}
			}
			if (maxType.getIndex() <= B) {
				if (maxType.getType() == S)
					result += B - maxType.getIndex() + 1;
			}
		}
		out.println(result);
		out.close();
	}
}

class type implements Comparable<type> {
	private static final int S = 1;
	private static final int NS = -1;

	private int t;
	private int index;

	public type(String s, int i) {
		if (s.equals("S"))
			t = S;
		index = i;
	}

	public int getType() {
		return t;
	}

	public int getIndex() {
		return index;
	}

	public String toString() {
		String result = "";
		if (t == S)
			result += "S ";
		else
			result += "NS ";
		result += index;
		return result;
	}

	public int compareTo(type next) {
		return Integer.compare(index, next.index);
	}
}
