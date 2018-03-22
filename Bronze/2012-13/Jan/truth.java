import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class truth {
	private static int[] components;
	private static int N, M;
	private static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("truth.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("truth.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		result = -1;
		N = Integer.parseInt(st.nextToken());
		components = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			components[i] = i;
		}
		M = Integer.parseInt(st.nextToken());
		for (int ind = 0; ind < M; ind++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			String indicator = st.nextToken();
			if (Math.abs(components[first]) == Math.abs(components[second])) {
				if ((components[first] == components[second]) && indicator.equals("L")) {
					result = ind;
					break;
				}
				if ((components[first] == -components[second]) && indicator.endsWith("T")) {
					result = ind;
					break;
				}
			}
			int aComp = components[first];
			int bComp = components[second];
			if (indicator.equals("T")) {
				for (int i = 1; i <= N; i++) {
					if (components[i] == bComp)
						components[i] = aComp;
					if (components[i] == -bComp)
						components[i] = -aComp;
				}
			} else {
				for (int i = 1; i <= N; i++) {
					if (components[i] == bComp)
						components[i] = -aComp;
					if (components[i] == -bComp)
						components[i] = aComp;
				}
			}
		}
		if (result == -1)
			result = M;
		out.println(result);
		out.close();
	}
}
