import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class odometer {
	private static ArrayList<Long> list = new ArrayList<Long>();

	public static void main(String[] args) throws IOException {
		for (int i = 1; i <= 9; i++) {
			fill(i);
		}
		fillZero();
		list.add(10000000000000000L);
		BufferedReader f = new BufferedReader(new FileReader("odometer.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("odometer.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		long lower = Long.parseLong(st.nextToken());
		long higher = Long.parseLong(st.nextToken());
		int result = 0;
		for (long i : list) {
			if (i >= lower && i <= higher)
				result++;
		}
		out.println(result);
		out.close();
	}

	public static void fillZero() {
		String s = "0";
		for (int i = 1; i <= 15; i++) {
			for (int k = 1; k <= 9; k++) {
				String temp = insert(s, 0, k);
				list.add(Long.parseLong(temp));
			}
			s += "0";
		}
	}

	public static void fill(int number) {
		String s = number + "";
		for (int i = 1; i <= 15; i++) {
			for (int k = 0; k <= 9; k++) {
				if (number == k)
					continue;
				for (int j = 0; j <= s.length(); j++) {
					if (k == 0 && j == 0)
						continue;
					String temp = insert(s, j, k);
					list.add(Long.parseLong(temp));
				}
			}
			s += number;
		}
	}

	public static String insert(String s, int index, int digit) {
		return s.substring(0, index) + digit + s.substring(index);
	}
}
