
/*
 ID: majesti2
 LANG: JAVA
 TASK: inflate
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class inflate {
	private static int M, N;
	private static int[] results;
	private static int[] values;
	private static int[] times;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		results = new int[M + 1];
		values = new int[N];
		times = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			values[i] = Integer.parseInt(st.nextToken());
			times[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i <= M; i++) {
			int curr = results[i];
			for (int k = 0; k < N; k++) {
				int nextTime = i + times[k];
				if (nextTime > M)
					continue;
				int next = curr + values[k];
				results[nextTime] = Math.max(results[nextTime], next);
			}
		}
		out.println(results[M]);
		out.close();
	}
}
