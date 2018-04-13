
/*
ID: majesti2
LANG: JAVA
TASK: shuttle
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class shuttle {
	public static int N;
	public static int target, init;
	public static ArrayList<String> list = new ArrayList<String>();
	public static Comparator<String> comp = new Comparator<String>() {
		public int compare(String s1, String s2) {
			StringTokenizer st = new StringTokenizer(s1);
			int a = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(s2);
			int b = Integer.parseInt(st.nextToken());
			while (a == b && st.hasMoreTokens()) {
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
			}
			return a - b;
		}
	};

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("shuttle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuttle.out")));
		N = Integer.parseInt(f.readLine());
		for (int i = 0; i < N; i++) {
			target = setBit(target, i);
			init |= (1 << i);
		}
		int res = dfs(init, N, "");
		Collections.sort(list, comp);
		String str = list.get(0);
		StringTokenizer st = new StringTokenizer(str);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < res; i++) {
			sb.append(st.nextToken());
			if ((i + 1) % 20 == 0 || i == res - 1)
				sb.append("\n");
			else
				sb.append(" ");
		}
		out.print(sb.toString());
		out.close();
	}

	public static int dfs(int S, int index, String res) {
		int copy = S;
		if (S == target && index == N) {
			list.add(res);
			return 0;
		}
		int result1 = 1 << 29;
		int result2 = 1 << 29;
		int result3 = 1 << 29;
		int result4 = 1 << 29;
		if (index + 1 <= 2 * N && checkBit(S, index + 1)) {
			S = setBit(S, index);
			S = unsetBit(S, index + 1);
			result1 = dfs(S, index + 1, res + (index + 2) + " ");
		}
		S = copy;
		if (index + 2 <= 2 * N && !checkBit(S, index + 1) && checkBit(S, index + 2)) {
			S = setBit(S, index);
			S = unsetBit(S, index + 2);
			result2 = dfs(S, index + 2, res + (index + 3) + " ");
		}
		S = copy;
		if (index - 1 >= 0 && !checkBit(S, index - 1)) {
			result3 = dfs(S, index - 1, res + (index) + " ");
		}
		if (index - 2 >= 0 && !checkBit(S, index - 2) && checkBit(S, index - 1)) {
			result4 = dfs(S, index - 2, res + (index - 1) + " ");
		}
		return min(result1, result2, result3, result4) + 1;
	}

	public static int min(int a, int b, int c, int d) {
		return Math.min(a, Math.min(b, Math.min(c, d)));
	}

	public static boolean checkBit(int S, int index) {
		return ((S >> (2 * N - index)) & 1) == 1;
	}

	public static int setBit(int S, int index) {
		S |= (1 << (2 * N - index));
		return S;
	}

	public static int unsetBit(int S, int index) {
		S &= ~(1 << (2 * N - index));
		return S;
	}
}
