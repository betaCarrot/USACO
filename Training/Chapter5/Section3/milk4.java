
/*
ID: majesti2
LANG: JAVA
TASK: milk4
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class milk4 {
	public static int N, Q;
	public static int[] pails, sizes, last, nlast;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("milk4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));
		Q = Integer.parseInt(f.readLine());
		N = Integer.parseInt(f.readLine());
		sizes = new int[Q + 1];
		last = new int[Q + 1];
		nlast = new int[Q + 1];
		Arrays.fill(sizes, 1 << 29);
		Arrays.fill(last, -1);
		Arrays.fill(nlast, -1);
		pails = new int[N];
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(f.readLine()));
		}
		Collections.sort(list);
		Collections.reverse(list);
		for (int i = 0; i < N; i++) {
			pails[i] = list.get(i);
		}
		sizes[0] = 0;
		for (int i = 0; i < N; i++) {
			int[] tempSizes = new int[Q + 1];
			int[] tempLast = new int[Q + 1];
			int[] tempnlast = new int[Q + 1];
			for (int j = 0; j <= Q; j++) {
				tempSizes[j] = sizes[j];
				tempLast[j] = last[j];
				tempnlast[j] = nlast[j];
			}
			int p = pails[i];
			for (int j = p; j <= Q; j++) {
				int prev = j - p;
				if (tempSizes[prev] < 1 << 29) {
					if (tempLast[prev] == p) {
						tempLast[j] = p;
						tempSizes[j] = tempSizes[prev];
						tempnlast[j] = tempnlast[prev] + 1;
					}
					if (tempLast[prev] != p) {
						tempLast[j] = p;
						tempSizes[j] = tempSizes[prev] + 1;
						tempnlast[j] = 1;
					}
					if (sizes[prev] + 1 < tempSizes[j]
							|| sizes[prev] + 1 == tempSizes[j] && check(prev, j - p * tempnlast[j])) {
						tempLast[j] = p;
						tempSizes[j] = sizes[prev] + 1;
						tempnlast[j] = 1;
					}
				}
			}
			for (int j = 0; j <= Q; j++) {
				if (tempSizes[j] <= sizes[j]) {
					sizes[j] = tempSizes[j];
					last[j] = tempLast[j];
					nlast[j] = tempnlast[j];
				}
			}
		}
		ArrayList<Integer> results = new ArrayList<Integer>();
		while (Q != 0) {
			results.add(last[Q]);
			Q -= nlast[Q] * last[Q];
		}
		StringBuilder sb = new StringBuilder();
		sb.append(results.size());
		for (int i : results) {
			sb.append(" " + i);
		}
		sb.append("\n");
		out.print(sb.toString());
		out.close();
	}

	public static boolean check(int a, int b) {
		while (a > 0 && b > 0) {
			if (last[a] < last[b]) {
				return true;
			}
			if (last[a] > last[b]) {
				return false;
			}
			a -= nlast[a] * last[a];
			b -= nlast[b] * last[b];
		}
		if (a > 0) {
			return false;
		}
		return true;
	}
}
