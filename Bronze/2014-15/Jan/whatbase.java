import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class whatbase {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("whatbase.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("whatbase.out")));
		int N = Integer.parseInt(f.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			for (int base = 10; base <= 15000; base++) {
				int key = baseConvert(a, base);
				int result = binarySearch(b, key);
				if (result != -1) {
					out.println(base + " " + result);
					break;
				}
			}
		}
		out.close();
	}

	public static int baseConvert(int number, int base) {
		return (number / 100) * base * base + ((number / 10) % 10) * base + ((number % 100) % 10);
	}

	public static int binarySearch(int number, int key) {
		int low = 10;
		int high = 15000;
		while (high >= low) {
			int middle = (low + high) / 2;
			if (baseConvert(number, middle) == key) {
				return middle;
			}
			if (baseConvert(number, middle) < key) {
				low = middle + 1;
			}
			if (baseConvert(number, middle) > key) {
				high = middle - 1;
			}
		}
		return -1;
	}
}
