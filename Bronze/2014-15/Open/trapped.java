import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class trapped {
	private static int N;
	private static hay[] bales;
	private static boolean[] dpRight;
	private static boolean[] dpLeft;
	private static int[] intervals;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("trapped.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("trapped.out")));
		N = Integer.parseInt(f.readLine());
		bales = new hay[N];
		dpRight = new boolean[N];
		dpLeft = new boolean[N];
		intervals = new int[N - 1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int value = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			bales[i] = new hay(value, index);
		}
		Arrays.sort(bales);
		for (int i = 0; i < N; i++) {
			dpRight[i] = dpRight(i);
			dpLeft[i] = dpLeft(i);
		}
		for (int i = 0; i < N; i++) {
			if (!dpLeft[i])
				intervals[i - 1]++;
			if (!dpRight[i])
				intervals[i]++;
		}
		int result = 0;
		for (int i = 0; i < N - 1; i++) {
			if (intervals[i] == 2) {
				result += bales[i + 1].getIndex() - bales[i].getIndex();
			}
		}
		out.println(result);
		out.close();
	}

	public static boolean dpLeft(int i) {
		int leftIndex = i - 1;
		int rightIndex = i;
		boolean left = true;
		boolean justTurned = false;
		while (true) {
			if (leftIndex == -1 || rightIndex == N)
				return true;
			if (left) {
				int distance = Math.abs(bales[rightIndex].getIndex() - bales[leftIndex].getIndex());
				if (distance > bales[leftIndex].getValue()) {
					justTurned = false;
					leftIndex--;
				} else {
					if (justTurned)
						return false;
					left = false;
					justTurned = true;
				}
			} else {
				int distance = Math.abs(bales[leftIndex].getIndex() - bales[rightIndex].getIndex());
				if (distance > bales[rightIndex].getValue()) {
					justTurned = false;
					rightIndex++;
				} else {
					if (justTurned)
						return false;
					left = true;
					justTurned = true;
				}
			}
		}
	}

	public static boolean dpRight(int i) {
		int leftIndex = i;
		int rightIndex = i + 1;
		boolean left = false;
		boolean justTurned = false;
		while (true) {
			if (leftIndex == -1 || rightIndex == N)
				return true;
			if (left) {
				int distance = Math.abs(bales[rightIndex].getIndex() - bales[leftIndex].getIndex());
				if (distance > bales[leftIndex].getValue()) {
					justTurned = false;
					leftIndex--;
				} else {
					if (justTurned)
						return false;
					left = false;
					justTurned = true;
				}
			} else {
				int distance = Math.abs(bales[leftIndex].getIndex() - bales[rightIndex].getIndex());
				if (distance > bales[rightIndex].getValue()) {
					justTurned = false;
					rightIndex++;
				} else {
					if (justTurned)
						return false;
					left = true;
					justTurned = true;
				}
			}
		}
	}
}

class hay implements Comparable<hay> {
	private int index;
	private int value;

	public hay(int value, int index) {
		this.index = index;
		this.value = value;
	}

	public int getIndex() {
		return index;
	}

	public int getValue() {
		return value;
	}

	public int compareTo(hay next) {
		if (!(Integer.compare(index, next.index) == 0)) {
			return Integer.compare(index, next.index);
		}
		return Integer.compare(value, next.value);
	}

	public String toString() {
		return index + " " + value;
	}
}
