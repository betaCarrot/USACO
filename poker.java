import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class poker {
	private static int N;
	private static long result = 0L;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("poker.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poker.out")));
		N = Integer.parseInt(f.readLine());
		int prev = 0;
		for (int i = 0; i < N; i++) {
			int curr = Integer.parseInt(f.readLine());
			if (curr > prev)
				result += curr - prev;
			prev = curr;
		}
		out.println(result);
		out.close();
	}
}
