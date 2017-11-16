import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class auto {
	private static String[] dict;
	private static String[] original;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("auto.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("auto.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		dict = new String[N];
		original = new String[N];
		for (int i = 0; i < N; i++) {
			String s = f.readLine();
			dict[i] = s;
			original[i] = s;
		}
		Arrays.sort(dict);
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(f.readLine());
			int ind = Integer.parseInt(st.nextToken());
			String s = st.nextToken();
			int index = binarySearch(s);
			while (true) {
				if (index == -1 || !dict[index].startsWith(s))
					break;
				index--;
			}
			index++;
			index += (ind - 1);
			int result;
			if (index >= N || !dict[index].startsWith(s)) {
				result = -1;
			} else {
				String target = dict[index];
				result = findIndex(target) + 1;
			}
			sb.append(result).append("\n");
		}
		out.print(sb.toString());
		out.close();
	}

	public static int binarySearch(String s) {
		int low = 0;
		int high = N - 1;
		while (high >= low) {
			int middle = (low + high) / 2;
			if (dict[middle].startsWith(s))
				return middle;
			if (dict[middle].compareTo(s) < 0)
				low = middle + 1;
			if (dict[middle].compareTo(s) > 0)
				high = middle - 1;
		}
		return -1;
	}

	public static int findIndex(String s) {
		for (int i = 0; i < N; i++) {
			if (original[i].equals(s))
				return i;
		}
		return -1;
	}
}
