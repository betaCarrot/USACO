import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class blink {
	private static boolean[][] states;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("blink.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blink.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		Long K = Long.parseLong(st.nextToken());
		states = new boolean[65536][N];
		boolean[] initials = new boolean[N];
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(f.readLine());
			if (temp == 1)
				initials[i] = true;
		}
		states[0] = initials;
		int pre = 0;
		int cycle = 0;
		int cycleIndex = -1;
		int remainder = 0;
		boolean[] bs = new boolean[N];
		for (int i = 0; i < N; i++) {
			bs[i] = initials[i];
		}
		for (int i = 1; i <= K; i++) {
			bs = next(bs);
			int index = indexOf(bs, i);
			if (index >= 0) {
				cycleIndex = index;
				break;
			}
			for (int k = 0; k < N; k++) {
				states[i][k] = bs[k];
			}
		}
		if (cycleIndex != -1) {
			boolean[] cycling = new boolean[N];
			for (int i = 0; i < N; i++) {
				cycling[i] = states[cycleIndex][i];
			}
			while (!equals(initials, cycling)) {
				initials = next(initials);
				pre++;
			}
			while (true) {
				if (cycle == 0) {
					cycle++;
					continue;
				}
				cycling = next(cycling);
				if (equals(cycling, states[cycleIndex]))
					break;
				cycle++;
			}
			remainder = (int) ((K - pre) % cycle);
			boolean[] result = new boolean[N];
			for (int i = 0; i < N; i++) {
				result[i] = states[cycleIndex][i];
			}
			for (int i = 0; i < remainder; i++) {
				result = next(result);
			}
			for (boolean b : result) {
				if (b)
					out.println(1);
				else
					out.println(0);
			}
		} else {
			long times = K;
			for (int i = 0; i < times; i++) {
				initials = next(initials);
			}
			for (boolean b : initials) {
				if (b)
					out.println(1);
				else
					out.println(0);
			}
		}
		out.close();
	}

	public static int indexOf(boolean[] check, int index) {
		for (int i = 0; i <= index; i++) {
			if (equals(states[i], check))
				return i;
		}
		return -1;
	}

	public static boolean[] next(boolean[] bs) {
		boolean[] nextArray = new boolean[N];
		for (int i = 0; i < N; i++) {
			nextArray[i] = bs[i];
		}
		for (int i = 0; i < N; i++) {
			if (bs[i])
				nextArray[nextIndex(i)] = !nextArray[nextIndex(i)];
		}
		return nextArray;
	}

	public static int nextIndex(int i) {
		if (i == N - 1)
			return 0;
		else
			return i + 1;
	}

	public static boolean equals(boolean[] as, boolean[] bs) {
		for (int i = 0; i < N; i++) {
			if (as[i] != bs[i])
				return false;
		}
		return true;
	}
}