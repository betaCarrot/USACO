import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class greetings {
	private static final int MAX = 1000000;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("greetings.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("greetings.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int R = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int[] cow1 = new int[MAX];
		int[] cow2 = new int[MAX];
		int current1 = 0;
		int current2 = 0;
		int time1 = 0;
		int time2 = 0;
		for (int i = 0; i < R; i++) {
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			int steps = Integer.parseInt(st1.nextToken());
			String s = st1.nextToken();
			if (s.equals("L")) {
				for (int k = 0; k < steps; k++) {
					current1--;
					cow1[time1] = current1;
					time1++;
				}
			} else {
				for (int k = 0; k < steps; k++) {
					current1++;
					cow1[time1] = current1;
					time1++;
				}
			}
		}
		for (int i = 0; i < E; i++) {
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			int steps = Integer.parseInt(st1.nextToken());
			String s = st1.nextToken();
			if (s.equals("L")) {
				for (int k = 0; k < steps; k++) {
					current2--;
					cow2[time2] = current2;
					time2++;
				}
			} else {
				for (int k = 0; k < steps; k++) {
					current2++;
					cow2[time2] = current2;
					time2++;
				}
			}
		}
		int result = 0;
		int maxTime = Math.max(time1, time2);
		if (time1 < time2) {
			for (int i = time1; i < time2; i++) {
				cow1[i] = current1;
			}
		} else if (time1 > time2) {
			for (int i = time2; i < time1; i++) {
				cow2[i] = current2;
			}
		}
		boolean justMet = true;
		for (int i = 0; i < maxTime; i++) {
			if (cow1[i] == cow2[i]) {
				if (justMet)
					continue;
				justMet = true;
				result++;
			} else
				justMet = false;
		}
		out.println(result);
		out.close();
	}
}
