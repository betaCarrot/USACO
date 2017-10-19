import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class typo {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("typo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("typo.out")));
		String line = f.readLine();
		int result = fast(line);
		out.println(result);
		out.close();
	}

	public static int fast(String line) {
		int N = line.length();
		int currentl = 0;
		int currentr = 0;
		boolean lnegative = false;
		boolean rnegative = false;
		int lCount = 0;
		int rCount = 0;
		int result = 0;
		int nl = 0;
		int nr = 0;
		for (int i = 0; i < N; i++) {
			String c = line.substring(i, i + 1);
			if (c.equals("("))
				nl++;
			else
				nr++;
		}
		if (Math.abs(nr - nl) != 2)
			return 0;
		if (nl < nr) {
			for (int i = 0; i < N; i++) {
				String c = line.substring(i, i + 1);
				if (c.equals("(")) {
					currentl++;
				} else {
					currentl--;
					if (!lnegative)
						lCount++;
					if (currentl < 0)
						lnegative = true;
				}
			}
			result = lCount;
		} else {
			for (int i = N - 1; i >= 0; i--) {
				String c = line.substring(i, i + 1);
				if (c.equals(")")) {
					currentr++;
				} else {
					currentr--;
					if (!rnegative)
						rCount++;
					if (currentr < 0)
						rnegative = true;
				}
			}
			result = rCount;
		}
		return result;
	}
}
