import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class censor {
	private static int length;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("censor.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("censor.out")));
		String S = f.readLine();
		String T = f.readLine();
		length = T.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < S.length(); i++) {
			sb.append(S.substring(i, i + 1));
			if (sb.length() < length)
				continue;
			if (sb.substring(sb.length() - length).equals(T)) {
				sb.delete(sb.length() - length, sb.length());
			}
		}
		out.println(sb.toString());
		out.close();
	}
}
