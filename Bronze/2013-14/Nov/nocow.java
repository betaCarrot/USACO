import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class nocow {
	private static int numTypes;
	private static String[][] types;
	private static int[] indexes;
	private static String[][] nocows;
	private static int K;
	private static ArrayList<Integer> noIndexes = new ArrayList<Integer>();
	private static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("nocow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocow.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()) - 1;
		types = new String[30][100];
		nocows = new String[30][100];
		for (int ind = 0; ind < N; ind++) {
			String line = f.readLine();
			String[] ss = line.split(" ");
			if (ind == 0) {
				numTypes = ss.length - 5;
				types = new String[numTypes][100];
				nocows = new String[N][numTypes];
				indexes = new int[numTypes];
				dp = new int[numTypes];
			}
			for (int i = 0; i < numTypes; i++) {
				String next = ss[i + 4];
				nocows[ind][i] = next;
				if (contains(types[i], next, indexes[i]))
					continue;
				types[i][indexes[i]] = next;
				indexes[i]++;
			}
		}
		dp[dp.length - 1] = 1;
		for (int i = dp.length - 2; i >= 0; i--) {
			dp[i] = dp[i + 1] * indexes[i + 1];
		}
		for (int i = 0; i < types.length; i++) {
			Arrays.sort(types[i], new Comparator<String>() {
				public int compare(String o1, String o2) {
					if (o1 == null && o2 == null) {
						return 0;
					}
					if (o1 == null) {
						return 1;
					}
					if (o2 == null) {
						return -1;
					}
					return o1.compareTo(o2);
				}
			});
		}
		for (String[] ss : nocows) {
			int result = 0;
			for (int i = 0; i < numTypes; i++) {
				result += getIndexes(types[i], ss[i]) * dp[i];
			}
			noIndexes.add(result);
		}
		Collections.sort(noIndexes);
		for (int i = 0; i < noIndexes.size(); i++) {
			if (noIndexes.get(i) <= K)
				K++;
		}
		out.println(getCow(K));
		out.close();
	}

	public static String getCow(int index) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dp.length; i++) {
			String toAppend = types[i][index / dp[i]];
			sb.append(toAppend);
			index = index % dp[i];
			if (i != dp.length - 1)
				sb.append(" ");
		}
		return sb.toString();
	}

	public static int getIndex(int[] indexes) {
		int result = 0;
		for (int i = 0; i < indexes.length; i++) {
			result += indexes[i] * dp[i];
		}
		return result;
	}

	public static int getIndexes(String[] ss, String s) {
		for (int i = 0; i < ss.length; i++) {
			if (ss[i].equals(s))
				return i;
		}
		return -1;
	}

	public static boolean contains(String[] ss, String next, int index) {
		for (int i = 0; i < index; i++) {
			if (ss[i].equals(next))
				return true;
		}
		return false;
	}
}
