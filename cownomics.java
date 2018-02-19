import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class cownomics {
	private static int N, M;
	private static final long base = 31;
	private static long[] powers;
	private static char[][] spotted, plain;
	private static long[][] sHash, pHash;

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		powers = new long[1000001];
		powers[0] = 1;
		for (int i = 1; i < 1000001; i++) {
			powers[i] = powers[i - 1] * base;
		}
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		spotted = new char[N][M];
		plain = new char[N][M];
		for (int i = 0; i < N; i++) {
			spotted[i] = f.readLine().toCharArray();
		}
		for (int i = 0; i < N; i++) {
			plain[i] = f.readLine().toCharArray();
		}
		sHash = new long[N][M + 1];
		pHash = new long[N][M + 1];
		for (int i = 0; i < N; i++) {
			sHash[i][0] = 0;
			pHash[i][0] = 0;
			for (int j = 1; j <= M; j++) {
				sHash[i][j] = sHash[i][j - 1] * base + spotted[i][j - 1] - 'A' + 1;
				pHash[i][j] = pHash[i][j - 1] * base + plain[i][j - 1] - 'A' + 1;
			}
		}
		int result = Integer.MAX_VALUE;
		for (int length = 0; length < M; length++) {
			for (int left = 1; left + length <= M; left++) {
				int right = left + length;
				if (length + 1 > result) {
					break;
				}
				boolean valid = true;
				HashSet<Long> set = new HashSet<Long>();
				for (int i = 0; i < N; i++) {
					set.add(getSHash(i, left, right));
				}
				for (int i = 0; i < N; i++) {
					if (set.contains(getPHash(i, left, right))) {
						valid = false;
						break;
					}
				}
				if (valid) {
					result = length + 1;
					break;
				}
			}
			if (result != Integer.MAX_VALUE) {
				break;
			}
		}
		System.out.println(result);
		System.out.println(System.currentTimeMillis() - startTime);
		out.close();
	}

	public static long getSHash(int i, int l, int r) {
		return sHash[i][r] - sHash[i][l - 1] * powers[r - l + 1];
	}

	public static long getPHash(int i, int l, int r) {
		return pHash[i][r] - pHash[i][l - 1] * powers[r - l + 1];
	}
}
