import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class cowfind {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowfind.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowfind.out")));
		String line = f.readLine();
		int N = line.length();
		int currentCount = 0;
		int result = 0;
		for (int i = 1; i < N; i++) {
			String previous = line.substring(i - 1, i);
			String current = line.substring(i, i + 1);
			if (previous.equals("(") && current.equals("("))
				currentCount++;
			if (previous.equals(")") && current.equals(")")) {
				result += currentCount;
			}
		}
		out.println(result);
		out.close();
	}
}