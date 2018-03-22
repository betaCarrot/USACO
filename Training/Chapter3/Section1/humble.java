
/*
 ID: majesti2
 LANG: JAVA
 TASK: humble
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class humble {
	private static int N, K;
	private static int[] results;
	private static int[] indexes;
	private static int[] array;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("humble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		array = new int[K];
		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < K; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(array);
		results = new int[N + 1];
		indexes = new int[K];
		results[0] = 1;
		for (int i = 1; i <= N; i++) {
			long next = Integer.MAX_VALUE;
			int last = results[i - 1];
			for (int k = 0; k < K; k++) {
				int prime = array[k];
				while (prime * results[indexes[k]] <= last) {
					indexes[k]++;
				}
				next = Math.min(next, prime * results[indexes[k]]);
			}
			results[i] = (int) next;
		}
		System.out.println(results[N]);
		out.close();
	}
}
