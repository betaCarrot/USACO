import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class crossroad {
	private static int[] state = new int[10];
	private static int N;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 10; i++) {
			state[i] = -1;
		}
		BufferedReader f = new BufferedReader(new FileReader("crossroad.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crossroad.out")));
		N = Integer.parseInt(f.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int id = Integer.parseInt(st.nextToken()) - 1;
			int side = Integer.parseInt(st.nextToken());
			if (state[id] != -1 && state[id] != side) {
				result++;
			}
			state[id] = side;
		}
		out.println(result);
		out.close();
	}
}
