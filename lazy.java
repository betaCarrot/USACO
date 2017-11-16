import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class lazy {
	private static int MAX = 1000001;
	private static int[] grass = new int[MAX];
	private static int[] dpLeft = new int[MAX];
	private static int[] dpRight = new int[MAX];
	private static int K;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lazy.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lazy.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()) + 1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int amount = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			grass[index] = amount;
		}
		dpLeft[0] = grass[0];
		dpRight[MAX - 1] = grass[MAX - 1];
		fillLeft();
		fillRight();
		merge();
		out.println(result);
		out.close();
	}

	public static boolean valid(int i) {
		return i >= 0 && i < MAX;
	}

	public static void fillLeft() {
		for (int i = 1; i < MAX; i++) {
			int temp = dpLeft[i - 1] + grass[i];
			if (valid(i - K)) {
				temp -= grass[i - K];
			}
			dpLeft[i] = temp;
		}
	}

	public static void fillRight() {
		for (int i = MAX - 2; i >= 0; i--) {
			int temp = dpRight[i + 1] + grass[i];
			if (valid(i + K)) {
				temp -= grass[i + K];
			}
			dpRight[i] = temp;
		}
	}

	public static void merge() {
		for (int i = 0; i < MAX; i++) {
			result = Math.max(result, dpLeft[i] + dpRight[i] - grass[i]);
		}
	}
}
