import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class scode {
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("scode.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("scode.out")));
		String line = f.readLine();
		dfs(line);
		out.println(result);
		out.close();
	}

	public static void dfs(String s) {
		if (s.length() % 2 == 0 || s.length() == 1)
			return;
		String first = s.substring(0, s.length() / 2 + 1);
		String second = s.substring(s.length() / 2 + 1);
		if (first.substring(0, first.length() - 1).equals(second)) {
			result++;
			dfs(first);
		}
		if (first.substring(1).equals(second)) {
			result++;
			dfs(first);
		}
		String third = s.substring(0, s.length() / 2);
		String fourth = s.substring(s.length() / 2);
		if (fourth.substring(0, fourth.length() - 1).equals(third)) {
			result++;
			dfs(fourth);
		}
		if (fourth.substring(1).equals(third)) {
			result++;
			dfs(fourth);
		}
	}
}
