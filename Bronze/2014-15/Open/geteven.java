import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class geteven {
	private static final int B = 5;
	private static final int E = 3;
	private static final int S = 4;
	private static final int I = 6;
	private static final int G = 2;
	private static final int O = 1;
	private static final int M = 0;
	private static int[][] map = new int[7][20];
	private static int[] sizes = new int[7];
	private static int N;
	private static long result = 0L;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("geteven.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("geteven.out")));
		N = Integer.parseInt(f.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int index = toIndex(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			map[index][sizes[index]] = value;
			sizes[index]++;
		}
		fillArray(0, new int[7]);
		long total = 1L;
		for (int i = 0; i < 7; i++) {
			total *= sizes[i];
		}
		out.println(total - result);
		out.close();
	}

	public static void fillArray(int count, int[] temp) {
		for (int i = 0; i < sizes[count]; i++) {
			temp[count] = map[count][i];
			if (count == 0) {
				if (temp[M] % 2 == 0)
					continue;
			}
			if (count == 4) {
				if ((temp[G] + temp[O] + temp[E] + temp[S]) % 2 == 0)
					continue;
			}
			if (count == 6) {
				if (!((temp[B] + temp[E] + temp[I] + temp[E]) % 2 == 0))
					result++;
				continue;
			}
			fillArray(count + 1, temp);
		}
	}

	public static int toIndex(String s) {
		if (s.equals("B"))
			return B;
		if (s.equals("E"))
			return E;
		if (s.equals("S"))
			return S;
		if (s.equals("I"))
			return I;
		if (s.equals("G"))
			return G;
		if (s.equals("O"))
			return O;
		return M;
	}
}
