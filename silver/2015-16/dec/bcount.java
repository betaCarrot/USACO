import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class bcount {
	private static final int H = 1;
	private static final int G = 2;
	private static final int J = 3;
	private static int[] dpH, dpG, dpJ;
	private static int[] breeds;
	private static int N, Q;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("bcount.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		breeds = new int[N];
		dpH = new int[N];
		dpG = new int[N];
		dpJ = new int[N];
		for (int i = 0; i < N; i++) {
			breeds[i] = Integer.parseInt(f.readLine());
		}
		for (int i = 0; i < N; i++) {
			if (i > 0) {
				dpH[i] = dpH[i - 1];
				dpG[i] = dpG[i - 1];
				dpJ[i] = dpJ[i - 1];
			}
			if (breeds[i] == H)
				dpH[i]++;
			if (breeds[i] == G)
				dpG[i]++;
			if (breeds[i] == J)
				dpJ[i]++;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int numH, numG, numJ;
			if (start == 0) {
				numH = dpH[end];
				numG = dpG[end];
				numJ = dpJ[end];
			} else {
				numH = dpH[end] - dpH[start - 1];
				numG = dpG[end] - dpG[start - 1];
				numJ = dpJ[end] - dpJ[start - 1];
			}
			sb.append(numH).append(" ").append(numG).append(" ").append(numJ).append("\n");
		}
		out.print(sb.toString());
		out.close();
	}
}
