import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class paint {
	private static int a, b, c, d;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("paint.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		out.println(solve());
		out.close();
	}

	public static int solve() {
		if (d < a) {
			return (d - c) + (b - a);
		}
		if (d <= b) {
			return b - Math.min(a, c);
		}
		if (c > b) {
			return (d - c) + (b - a);
		}
		return d - Math.min(a, c);
	}
}
