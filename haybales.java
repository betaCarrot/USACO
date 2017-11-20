import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class haybales {
	private static int[] bales;
	private static int N, Q;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("haybales.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		bales = new int[N];
		for (int i = 0; i < N; i++) {
			bales[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(bales);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int firstIndex = Arrays.binarySearch(bales, start);
			int secondIndex = Arrays.binarySearch(bales, end);
			boolean hit = true;
			if (secondIndex < 0)
				hit = false;
			if (firstIndex < 0)
				firstIndex = -firstIndex - 1;
			if (secondIndex < 0)
				secondIndex = -secondIndex - 1;
			int result;
			if (hit)
				result = secondIndex - firstIndex + 1;
			else
				result = secondIndex - firstIndex;
			sb.append(result).append("\n");
		}
		out.print(sb);
		out.close();
	}
}