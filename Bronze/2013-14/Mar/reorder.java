import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class reorder {
	private static boolean[] visited;
	private static int[] as;
	private static int[] bs;
	private static int longest = -1;
	private static int count = 0;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("reorder.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reorder.out")));
		N = Integer.parseInt(f.readLine());
		as = new int[N];
		bs = new int[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			as[i] = Integer.parseInt(f.readLine());
		}
		for (int i = 0; i < N; i++) {
			bs[i] = Integer.parseInt(f.readLine());
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				if (as[i] == bs[i])
					visited[i] = true;
				else {
					count++;
					move(i, i, 0);
				}
			}
		}
		out.println(count + " " + longest);
		out.close();
	}

	public static void move(int i, int start, int length) {
		visited[i] = true;
		length++;
		int nextIndex = findIndex(as[i]);
		if (nextIndex == start) {
			longest = Math.max(longest, length);
			return;
		} else {
			move(nextIndex, start, length);
		}
	}

	public static int findIndex(int i) {
		for (int index = 0; index < N; index++) {
			if (bs[index] == i)
				return index;
		}
		return -1;
	}
}
