
/*
 ID: majesti2
 LANG: JAVA
 TASK: prefix
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class prefix {
	private static ArrayList<String> list = new ArrayList<String>();
	private static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		String line;
		while ((line = f.readLine()).equals(".") == false) {
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreTokens()) {
				list.add(st.nextToken());
			}
		}
		StringBuilder sb = new StringBuilder();
		while ((line = f.readLine()) != null) {
			sb.append(line);
		}
		str = sb.toString();
		boolean[] dp = new boolean[str.length() + 1];
		dp[0] = true;
		int result = 0;
		boolean complete = true;
		for (int i = 0; i <= str.length(); i++) {
			if (!dp[i]) {
				result = i;
				complete = false;
				break;
			}
			for (int j = 0; j < list.size(); j++) {
				String s = list.get(j);
				int next = s.length() + i;
				if (next <= str.length()) {
					if (str.substring(i, next).equals(s)) {
						for (int k = i; k <= next; k++) {
							dp[k] = true;
						}
					}
				}
			}
		}
		if (complete) {
			result = str.length() + 1;
		}
		out.println(result - 1);
		out.close();
	}
}
