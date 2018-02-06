import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class censor {
	private static String S, T;
	private static int N;
	private static final long base = 31;
	private static long[] hash, powers;
	private static char[] s;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("censor.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("censor.out")));
		S = f.readLine();
		T = f.readLine();
		N = S.length();
		s = S.toCharArray();
		int length = T.length();
		hash = new long[N + 1];
		powers = new long[1000001];
		powers[0] = 1;
		for (int i = 1; i < 1000001; i++) {
			powers[i] = powers[i - 1] * base;
		}
		hash[0] = 0;
		long hashT = 0;
		long[] tsh = new long[length + 1];
		char[] tChars = T.toCharArray();
		tsh[0] = 0;
		for (int i = 1; i <= length; i++) {
			tsh[i] = tsh[i - 1] * base + (tChars[i - 1] - 'a');
		}
		hashT = tsh[length];
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (int i = 1; i <= N; i++) {
			sb.append(s[i - 1]);
			int pos = i - count * length;
			hash[pos] = hash[pos - 1] * base + s[i - 1] - 'a';
			if (pos - length >= 0) {
				long check = getHash(pos - length + 1, pos);
				if (check == hashT && sb.substring(sb.length() - length).equals(T)) {
					sb.delete(sb.length() - length, sb.length());
					count++;
				}
			}
		}
		out.println(sb.toString());
		out.close();
	}

	public static long getHash(int l, int r) {
		return hash[r] - hash[l - 1] * powers[r - l + 1];
	}
}
