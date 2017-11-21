import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowcode {
	private static int base;
	private static long index;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowcode.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		String s = st.nextToken();
		index = Long.parseLong(st.nextToken()) - 1;
		base = s.length();
		long length = findLength();
		int result = solve(length);
		out.println(s.substring(result, result + 1));
		out.close();
	}

	public static long findLength() {
		long length = (long) base;
		while (index >= length) {
			length *= 2;
		}
		return length;
	}

	public static int solve(long length) {
		while (true) {
			if (index < base)
				return (int) index;
			if (index == length / 2) {
				index = length / 2 - 1;
			} else {
				index = length / 2 - (length - index) - 1;
			}
			while (index < length / 2)
				length /= 2;
		}
	}
}
