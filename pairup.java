import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class pairup {
	private static int result = 0;
	private static int N;
	private static cow[] cows;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("pairup.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
		N = Integer.parseInt(f.readLine());
		cows = new cow[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			cows[i] = new cow(a, b);
		}
		Arrays.sort(cows);
		int left = 0;
		int right = N - 1;
		while (true) {
			result = Math.max(result, cows[left].getValue() + cows[right].getValue());
			if (left == right) {
				break;
			}
			if (cows[left].getCount() < cows[right].getCount()) {
				cows[right].setCount(cows[right].getCount() - cows[left].getCount());
				left++;
			} else if (cows[left].getCount() > cows[right].getCount()) {
				cows[left].setCount(cows[left].getCount() - cows[right].getCount());
				right--;
			} else {
				left++;
				right--;
			}
		}
		out.println(result);
		out.close();
	}
}

class cow implements Comparable<cow> {
	private int count;
	private int value;

	public cow(int c, int v) {
		count = c;
		value = v;
	}

	public int getCount() {
		return count;
	}

	public int getValue() {
		return value;
	}

	public void setCount(int c) {
		count = c;
	}

	public int compareTo(cow next) {
		return Integer.compare(value, next.value);
	}
}
