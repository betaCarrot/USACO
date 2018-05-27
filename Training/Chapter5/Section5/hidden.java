
/*
ID: majesti2
LANG: JAVA
TASK: hidden
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class hidden {
	public static int N;
	public static char[] chars;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hidden.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hidden.out")));
		N = Integer.parseInt(f.readLine());
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = f.readLine()) != null) {
			sb.append(line);
		}
		chars = sb.toString().toCharArray();
		int i = 0;
		int j = 1;
		int k = 0;
		while (i < N && j < N && k < N) {
			int temp = chars[(i + k) % N] - chars[(j + k) % N];
			if (temp == 0) {
				k++;
				continue;
			}
			if (temp > 0) {
				i = i + k + 1;
			} else {
				j = j + k + 1;
			}
			if (i == j) {
				j++;
			}
			k = 0;
		}
		out.println(Math.min(i, j));
		out.close();
	}
}
