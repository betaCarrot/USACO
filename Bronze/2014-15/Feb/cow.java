import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class cow {
	private static int N;
	private static long C, CO, result;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cow.out")));
		N = Integer.parseInt(f.readLine());
		String line = f.readLine();
		C = 0L;
		CO = 0L;
		result = 0L;
		for (int i = 0; i < N; i++) {
			String s = line.substring(i, i + 1);
			if (s.equals("C"))
				C++;
			else if (s.equals("O"))
				CO += C;
			else if (s.equals("W"))
				result += CO;
		}
		out.println(result);
		out.close();
	}
}
