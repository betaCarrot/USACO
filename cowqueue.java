import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class cowqueue {
	private static PriorityQueue<cow> pq = new PriorityQueue<cow>();
	private static int N;
	private static int time = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowqueue.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowqueue.out")));
		N = Integer.parseInt(f.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int time = Integer.parseInt(st.nextToken());
			int duration = Integer.parseInt(st.nextToken());
			pq.offer(new cow(time, duration));
		}
		while (!pq.isEmpty()) {
			cow c = pq.poll();
			if (time < c.getTime())
				time = c.getTime();
			time += c.getDuration();
		}
		out.println(time);
		out.close();
	}
}

class cow implements Comparable<cow> {
	private int time;
	private int duration;

	public cow(int t, int d) {
		time = t;
		duration = d;
	}

	public int getTime() {
		return time;
	}

	public int getDuration() {
		return duration;
	}

	public int compareTo(cow next) {
		if (Integer.compare(time, next.time) == 0) {
			return Integer.compare(duration, next.duration);
		}
		return Integer.compare(time, next.time);
	}
}
