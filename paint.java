import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class paint {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("paint.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));
		int N = Integer.parseInt(f.readLine());
		PriorityQueue<event> events = new PriorityQueue<event>();
		int result = 0;
		int currentIndex = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int temp = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			if (dir.equals("L"))
				temp = -temp;
			int nextIndex = currentIndex + temp;
			int leftIndex = Math.min(currentIndex, nextIndex);
			int rightIndex = Math.max(currentIndex, nextIndex);
			events.offer(new event(leftIndex, 1));
			events.offer(new event(rightIndex, -1));
			currentIndex = nextIndex;
		}
		int currentLayer = 0;
		if (events.size() > 0) {
			event last = events.poll();
			currentLayer += last.getIncrement();
			while (events.size() > 0) {
				event curr = events.poll();
				if (currentLayer >= 2)
					result += curr.getX() - last.getX();
				currentLayer += curr.getIncrement();
				last = curr;
			}
		}
		out.println(result);
		out.close();
	}
}

class event implements Comparable<event> {
	private int x, increment;

	public event(int a, int b) {
		x = a;
		increment = b;
	}

	public int getX() {
		return x;
	}

	public int getIncrement() {
		return increment;
	}

	public int compareTo(event next) {
		return Integer.compare(x, next.x);
	}

	public String toString() {
		return x + " " + increment;
	}
}
