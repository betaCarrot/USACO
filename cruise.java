import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cruise {
	private static int N, M, K;
	private static boolean[][] visited;
	private static int[] steps;
	private static int[][] paths;
	private static final int LEFT = 0;
	private static final int RIGHT = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cruise.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cruise.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		long total = (long) M * K;
		visited = new boolean[M][N];
		steps = new int[M];
		paths = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			paths[i][LEFT] = a;
			paths[i][RIGHT] = b;
		}
		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < M; i++) {
			if (st.nextToken().equals("L")) {
				steps[i] = LEFT;
			} else {
				steps[i] = RIGHT;
			}
		}
		int currentIndex = 0;
		int currentPosition = 0;
		int preLength = 0;
		while (true) {
			if (visited[currentIndex][currentPosition]) {
				break;
			}
			visited[currentIndex][currentPosition] = true;
			currentPosition = paths[currentPosition][steps[currentIndex]];
			currentIndex++;
			currentIndex %= M;
			preLength++;
		}
		int cycleIndex = currentIndex;
		int cyclePosition = currentPosition;
		int cycleLength = 0;
		visited = new boolean[M][N];
		while (true) {
			if (visited[currentIndex][currentPosition]) {
				break;
			}
			visited[currentIndex][currentPosition] = true;
			currentPosition = paths[currentPosition][steps[currentIndex]];
			currentIndex++;
			currentIndex %= M;
			cycleLength++;
		}
		int pre = preLength - cycleLength;
		int cycle = cycleLength;
		int result = -1;
		if (total <= pre) {
			currentIndex = 0;
			currentPosition = 0;
			int count = 0;
			while (count < total) {
				currentPosition = paths[currentPosition][steps[currentIndex]];
				currentIndex++;
				currentIndex %= M;
				count++;
			}
			result = currentPosition + 1;
		} else {
			long remainder = (total - pre) % cycle;
			currentIndex = cycleIndex;
			currentPosition = cyclePosition;
			int count = 0;
			while (count < remainder) {
				currentPosition = paths[currentPosition][steps[currentIndex]];
				currentIndex++;
				currentIndex %= M;
				count++;
			}
			result = currentPosition + 1;
		}
		out.println(result);
		out.close();
	}
}
