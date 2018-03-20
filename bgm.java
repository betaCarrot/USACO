import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class bgm {
	private static int N;
	private static final int B = 0;
	private static final int E = 1;
	private static final int S = 2;
	private static final int I = 3;
	private static final int G = 4;
	private static final int O = 5;
	private static final int M = 6;
	private static int[][] map = new int[7][7];

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("bgm.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bgm.out")));
		N = Integer.parseInt(f.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int index = indexOf(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			map[index][(7 * 100000 + num) % 7]++;
		}
		long result = 0L;
		for (int b = 0; b < 7; b++) {
			for (int e = 0; e < 7; e++) {
				for (int s = 0; s < 7; s++) {
					for (int i = 0; i < 7; i++) {
						for (int g = 0; g < 7; g++) {
							for (int o = 0; o < 7; o++) {
								for (int m = 0; m < 7; m++) {
									if ((b + e + s + s + i + e) * (g + o + e + s) * (m + o + o) % 7 == 0) {
										result += (long) map[B][b] * map[E][e] * map[S][s] * map[I][i] * map[G][g]
												* map[O][o] * map[M][m];
									}
								}
							}
						}
					}
				}
			}
		}
		out.println(result);
		out.close();
	}

	public static int indexOf(String s) {
		char c = s.charAt(0);
		if (c == 'B')
			return B;
		if (c == 'E')
			return E;
		if (c == 'S')
			return S;
		if (c == 'I')
			return I;
		if (c == 'G')
			return G;
		if (c == 'O')
			return O;
		return M;
	}
}
