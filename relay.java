import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class relay {
	private static int N;
	private static int[] cows;
	private static int[] loopyStatus;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("relay.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("relay.out")));
		N = Integer.parseInt(f.readLine());
		cows = new int[N + 1];
		loopyStatus = new int[N + 1];
		for (int i = 0; i < N; i++) {
			cows[i + 1] = Integer.parseInt(f.readLine());
		}
		int result = 0;
		for (int i = 1; i <= N; i++) {
			if (!checkLoopy(i))
				result++;
		}
		out.println(result);
		out.close();
	}

	public static boolean checkLoopy(int i) {
		int nextIndex = cows[i];
		boolean[] visited = new boolean[N + 1];
		boolean isLoopy = false;
		while (nextIndex != 0) {
			if (loopyStatus[nextIndex] == 1)
				return true;
			if (loopyStatus[nextIndex] == 2)
				return false;
			if (visited[nextIndex]) {
				isLoopy = true;
				break;
			}
			visited[nextIndex] = true;
			nextIndex = cows[nextIndex];
		}
		if (isLoopy) {
			visited = new boolean[N + 1];
			nextIndex = cows[i];
			loopyStatus[i] = 1;
			while (nextIndex != 0) {
				if (visited[nextIndex])
					break;
				visited[nextIndex] = true;
				loopyStatus[nextIndex] = 1;
				nextIndex = cows[nextIndex];
			}
			return true;
		} else {
			visited = new boolean[N + 1];
			nextIndex = cows[i];
			loopyStatus[i] = 2;
			while (nextIndex != 0) {
				if (visited[nextIndex])
					break;
				visited[nextIndex] = true;
				loopyStatus[nextIndex] = 2;
				nextIndex = cows[nextIndex];
			}
			return false;
		}
	}
}
