package solutions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class paint {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("paint.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		StringTokenizer st1 = new StringTokenizer(f.readLine());
		int c = Integer.parseInt(st1.nextToken());
		int d = Integer.parseInt(st1.nextToken());
		int result = solve(a, b, c, d);
		out.println(result);
		out.close();
	}

	public static int solve(int a, int b, int c, int d) {
		if (a <= c) {
			if (b <= c) {
				return ((b - a) + (d - c));
			} else if (b <= d) {
				return d - a;
			} else
				return b - a;
		} else {
			if (d >= b) {
				return d - c;
			} else if (d <= a) {
				return (b - a) + (d - c);
			} else
				return b - c;
		}
	}
}
