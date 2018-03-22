import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowjog {
	private static int N, T;
	private static cow[] cows;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowjog.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		cows = new cow[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int pos = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			cows[i] = new cow(pos, speed);
		}
		int result = 0;
		long min = Long.MAX_VALUE;
		for (int index = N - 1; index >= 0; index--) {
			long end = cows[index].pos + (long) (cows[index].speed) * T;
			if (end < min) {
				result++;
				min = end;
			}
		}
		out.println(result);
		out.close();
	}
}

class cow {
	public int pos, speed;

	public cow(int a, int b) {
		pos = a;
		speed = b;
	}
}
