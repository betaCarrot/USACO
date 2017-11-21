import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class hps {
	private static final int H = 1;
	private static final int S = 2;
	private static final int P = 3;
	private static int N;
	private static int[] values;
	private static int[] dpH;
	private static int[] dpS;
	private static int[] dpP;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		N = Integer.parseInt(f.readLine());
		values = new int[N];
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			if (line.equals("H"))
				values[i] = H;
			else if (line.equals("S"))
				values[i] = S;
			else
				values[i] = P;
		}
		dpH = new int[N];
		dpS = new int[N];
		dpP = new int[N];
		if (values[0] == H)
			dpH[0] = 1;
		else if (values[0] == S)
			dpS[0] = 1;
		else
			dpP[0] = 1;
		for (int i = 1; i < N; i++) {
			dpH[i] = dpH[i - 1];
			dpS[i] = dpS[i - 1];
			dpP[i] = dpP[i - 1];
			if (values[i] == H)
				dpH[i]++;
			else if (values[i] == S)
				dpS[i]++;
			else
				dpP[i]++;
		}
		result = Math.max(result, max(dpH[N - 1], dpS[N - 1], dpP[N - 1]));
		for (int i = 0; i < N; i++) {
			int before = max(dpH[i], dpS[i], dpP[i]);
			int after = max(dpH[N - 1] - dpH[i], dpS[N - 1] - dpS[i], dpP[N - 1] - dpP[i]);
			result = Math.max(result, before + after);
		}
		out.println(result);
		out.close();
	}

	public static int max(int a, int b, int c) {
		return Math.max(a, Math.max(b, c));
	}

	public static void printArray(int[] array) {
		for (int i : array)
			System.out.print(i + " ");
		System.out.println();
	}
}
