import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class proximity {
	private static int N, K;
	private static int[] counts = new int[1000000 + 1];
	private static cow[] cows;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("proximity.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("proximity.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cows = new cow[N];
		for (int i = 0; i < N; i++) {
			cows[i] = new cow(Integer.parseInt(f.readLine()), i);
		}
		out.println(solution1());
		out.close();
	}

	private static int solution1() {
		K++;
		int result = -1;
		for (int i = 0; i < N; i++) {
			counts[cows[i].getBreed()]++;
			if (i >= K) {
				counts[cows[i - K].getBreed()]--;
			}
			if (counts[cows[i].getBreed()] > 1)
				result = Math.max(result, cows[i].getBreed());
		}
		return result;
	}

	private static int solution2() {


		Arrays.sort(cows);
		int result = -1;
		for (int i = 0; i < N - 1; i++) {
			if (cows[i].getBreed() == cows[i + 1].getBreed()) {
				int thisPosition = cows[i].getPosition();
				int nextPosition = cows[i + 1].getPosition();
				if (Math.abs(thisPosition - nextPosition) <= K) {
					result = cows[i].getBreed();
					break;
				}
			}
		}
		return result;
	}
}

class cow implements Comparable<cow> {
	int breed;
	int position;

	public cow(int b, int p) {
		breed = b;
		position = p;
	}

	public int compareTo(cow next) {
		if (breed > next.breed)
			return -1;
		else if (breed < next.breed)
			return 1;
		else {
			if (position > next.position)
				return -1;
			else if (position < next.position)
				return 1;
			else
				return 0;
		}
	}

	public int getBreed() {
		return breed;
	}

	public int getPosition() {
		return position;
	}
}