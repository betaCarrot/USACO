import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class angry {
	private static int N, K;
	private static int[] indexes;
	private static int[] intervals;
	private static boolean[] cuts;
	private static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("angry.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		indexes = new int[N];
		for (int i = 0; i < N; i++) {
			indexes[i] = Integer.parseInt(f.readLine());
		}
		Arrays.sort(indexes);
		out.println(search());
		out.close();
	}

	public static int search() {
		int low = 0;
		int high = 1000000000;
		int middle = 0;
		while (high != low) {
			middle = (low + high) / 2;
			if (valid(middle)) {
				high = middle;
			} else {
				low = middle + 1;
			}
		}
		return (low + high) / 2;
	}

	public static boolean valid(int r) {
		int last = 0;
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (indexes[i] <= last)
				continue;
			if (count == K)
				return false;
			count++;
			last = indexes[i] + 2 * r;
		}
		return true;
	}
}
