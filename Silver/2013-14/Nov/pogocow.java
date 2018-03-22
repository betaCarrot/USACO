import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class pogocow {
	private static int N;
	private static target[] targets;
	private static int[][] values;

	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader f = new BufferedReader(new FileReader("pogocow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pogocow.out")));
		N = Integer.parseInt(f.readLine());
		targets = new target[N];
		values = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				values[i][k] = -1;
			}
		}
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int pos = Integer.parseInt(st.nextToken());
			int point = Integer.parseInt(st.nextToken());
			targets[i] = new target(pos, point);
		}
		Arrays.sort(targets);
		int result = 0;
		for (int i = 0; i < N; i++) {
			result = Math.max(result, dfs(i, i + 1));
		}
		target[] temp = new target[N];
		temp[0] = new target(targets[0].position, targets[N - 1].point);
		for (int i = 1; i < N; i++) {
			temp[i] = new target(temp[i - 1].position + (targets[N - i].position - targets[N - i - 1].position),
					targets[N - i - 1].point);
		}
		for (int i = 0; i < N; i++) {
			targets[i] = temp[i];
		}
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				values[i][k] = -1;
			}
		}
		for (int i = 0; i < N; i++) {
			result = Math.max(result, dfs(i, i + 1));
		}
		System.out.println(result);
		out.close();
	}

	public static int dfs(int thisIndex, int nextIndex) {
		if (nextIndex >= N) {
			return targets[thisIndex].point;
		}
		if (values[thisIndex][nextIndex] >= 0) {
			return values[thisIndex][nextIndex];
		}
		int point = targets[thisIndex].point;
		int max = 0;
		for (int i = nextIndex; i < N; i++) {
			int distance = targets[i].position - targets[thisIndex].position;
			int next = Arrays.binarySearch(targets, new target(targets[i].position + distance, -1));
			if (next < 0) {
				next = -next - 1;
			}
			max = Math.max(max, dfs(i, next));
		}
		values[thisIndex][nextIndex] = point + max;
		return point + max;
	}
}

class target implements Comparable<target> {
	public int position, point;

	public target(int a, int b) {
		position = a;
		point = b;
	}

	public String toString() {
		return position + " " + point;
	}

	public int compareTo(target next) {
		return position - next.position;
	}
}
