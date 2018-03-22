import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class slowdown {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("slowdown.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("slowdown.out")));
		PriorityQueue pqt = new PriorityQueue<Integer>();
		PriorityQueue pqd = new PriorityQueue<Integer>();
		int n = Integer.parseInt(f.readLine());
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			String id = st.nextToken();
			int temp = Integer.parseInt(st.nextToken());
			if (id.equals("T"))
				pqt.add(temp);
			else
				pqd.add(temp);
		}
		pqd.add(1000);
		double currentTime = 0;
		double currentDistance = 0;
		int divisor = 1;
		while (!(pqd.isEmpty() && pqt.isEmpty())) {
			if (pqt.isEmpty()) {
				int nextDistance = (int) pqd.peek();
				currentTime += (nextDistance - currentDistance) * divisor;
				currentDistance = nextDistance;
				divisor++;
				pqd.poll();
			} else {
				int nextDistance = (int) pqd.peek();
				int nextTime = (int) pqt.peek();
				double dt = (nextDistance - currentDistance) * divisor;
				double tt = nextTime - currentTime;
				if (tt < dt) {
					currentDistance += tt / divisor;
					currentTime = (double) nextTime;
					divisor++;
					pqt.poll();
				} else if (tt > dt) {
					currentTime += dt;
					currentDistance = (double) nextDistance;
					divisor++;
					pqd.poll();
				} else {
					currentTime = nextTime;
					currentDistance = nextDistance;
					divisor++;
					pqd.poll();
					pqt.poll();
				}
			}
		}
		out.println(round(currentTime));
		out.close();
	}

	public static int round(double time) {
		if ((time - (int) time) >= 0.5)
			return (int) time + 1;
		else
			return (int) time;
	}
}
