import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class lostcow {
	private static boolean forward = true;
	private static int current, target;
	private static int distance = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lostcow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lostcow.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		current = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		out.println(solve());
		out.close();
	}

	public static int solve() {
		int result = 0;
		while (true) {
			if (forward) {
				for (int i = 0; i < distance; i++) {
					current++;
					result++;
					if (current == target)
						return result;
				}
				forward = false;
			} else {
				for (int i = 0; i < distance; i++) {
					current--;
					result++;
					if (current == target)
						return result;
				}
				forward = true;
			}
			if (distance == 1)
				distance = 3;
			else
				distance *= 2;
		}
	}
}
