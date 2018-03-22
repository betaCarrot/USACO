import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class helpcross {
	private static int[] chickens;
	private static cow[] cows;
	private static int N, C;
	private static int result = 0;
	private static boolean[] crossed;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("helpcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		chickens = new int[C];
		cows = new cow[N];
		crossed = new boolean[N];
		for (int i = 0; i < C; i++) {
			chickens[i] = Integer.parseInt(f.readLine());
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			cows[i] = new cow(start, end);
		}
		Arrays.sort(chickens);
		Arrays.sort(cows);
		for (int i = 0; i < C; i++) {
			int target = chickens[i];
			int minIndex = -1;
			for (int k = 0; k < N; k++) {
				if (crossed[k])
					continue;
				if (cows[k].getEnd() < target)
					continue;
				if (cows[k].getStart() > target)
					break;
				if (minIndex == -1 || cows[k].getEnd() < cows[minIndex].getEnd()) {
					minIndex = k;
				}
			}
			if (minIndex != -1) {
				crossed[minIndex] = true;
				result++;
			}
		}
		out.println(result);
		out.close();
	}
}

class cow implements Comparable<cow> {
	private int start;
	private int end;

	public cow(int s, int e) {
		start = s;
		end = e;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public String toString() {
		return start + " " + end;
	}

	public int compareTo(cow next) {
		if (Integer.compare(start, next.start) == 0)
			return Integer.compare(end, next.end);
		return Integer.compare(start, next.start);
	}
}
