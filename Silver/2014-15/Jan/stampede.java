import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class stampede {
	private static int N;
	private static PriorityQueue<event> events = new PriorityQueue<event>();
	private static HashSet<Integer> seen = new HashSet<Integer>();
	private static TreeSet<Integer> active = new TreeSet<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("stampede.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stampede.out")));
		N = Integer.parseInt(f.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			events.offer(new event(x * (-r) - r, y, true));
			events.offer(new event(x * (-r), y, false));
		}
		int result = 0;
		while (!events.isEmpty()) {
			int time = events.peek().time;
			while (!events.isEmpty() && events.peek().time == time) {
				event curr = events.poll();
				int key = curr.key;
				if (curr.add)
					active.add(key);
				else
					active.remove(key);
			}
			if (active.isEmpty()) {
				continue;
			}
			int min = active.first();
			if (!seen.contains(min)) {
				result++;
				seen.add(min);
			}
		}
		out.println(result);
		out.close();
	}
}

class event implements Comparable<event> {
	public int time,key;
	public boolean add;

	public event(int a, int b, boolean c) {
		time = a;
		key = b;
		add = c;
	}

	public int compareTo(event next) {
		return time - next.time;
	}
}
