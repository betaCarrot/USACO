import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class clumsy {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("clumsy.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("clumsy.out")));
		String line = f.readLine();
		int N = line.length();
		int leftCount = 0;
		int result = 0;
		for (int i = 0; i < N; i++) {
			if (line.substring(i, i + 1).equals("("))
				leftCount++;
			else {
				if (leftCount == 0) {
					result++;
					leftCount++;
				} else
					leftCount--;
			}
		}
		result += leftCount / 2;
		out.println(result);
		out.close();
	}
}
