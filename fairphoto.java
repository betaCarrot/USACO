import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class fairphoto {
	private static final int G = 1;
	private static final int H = -1;
	private static int[] dp;
	private static int[] distances;
	private static int[][] indexes;
	private static int[][] negativeIndexes;
	private static int[] seen;
	private static int[] negativeSeen;
	private static breed[] breeds;
	private static int N;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fairphoto.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fairphoto.out")));
		N = Integer.parseInt(f.readLine());
		dp = new int[N];
		distances = new int[N];
		seen = new int[N + 1];
		negativeSeen = new int[N + 1];
		indexes = new int[N + 1][2];
		negativeIndexes = new int[N + 1][2];
		breeds = new breed[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int index = Integer.parseInt(st.nextToken());
			String type = st.nextToken();
			breeds[i] = new breed(index, type);
		}
		Arrays.sort(breeds);
		seen[0] = 1;
		indexes[0][0] = -1;
		if (breeds[0].getType() > 0) {
			dp[0] = breeds[0].getType();
			seen[dp[0]] = 1;
			indexes[dp[0]][0] = 0;
			distances[0] = breeds[0].getIndex();
		} else {
			dp[0] = breeds[0].getType();
			negativeSeen[-dp[0]] = 1;
			negativeIndexes[-dp[0]][0] = 0;
			distances[0] = breeds[0].getIndex();
		}
		for (int i = 1; i < N; i++) {
			int current = dp[i - 1] + breeds[i].getType();
			if (current >= 0) {
				if (seen[current] == 0) {
					indexes[current][0] = i;
					seen[current]++;
				} else if (seen[current] == 1) {
					indexes[current][1] = i;
					seen[current]++;
				} else {
					indexes[current][1] = i;
				}
			} else {
				current = -current;
				if (negativeSeen[current] == 0) {
					negativeIndexes[current][0] = i;
					negativeSeen[current]++;
				} else if (negativeSeen[current] == 1) {
					negativeIndexes[current][1] = i;
					negativeSeen[current]++;
				} else {
					negativeIndexes[current][1] = i;
				}
				current = -current;
			}
			dp[i] = current;
			distances[i] = breeds[i].getIndex();
		}
		for (int i = 0; i <= N; i++) {
			if (seen[i] < 2)
				continue;
			int firstIndex = indexes[i][0] + 1;
			int secondIndex = indexes[i][1];
			int firstPosition = distances[firstIndex];
			int secondPosition = distances[secondIndex];
			result = Math.max(result, secondPosition - firstPosition);
		}
		for (int i = 1; i <= N; i++) {
			if (negativeSeen[i] < 2)
				continue;
			int firstIndex = negativeIndexes[i][0] + 1;
			int secondIndex = negativeIndexes[i][1];
			int firstPosition = distances[firstIndex];
			int secondPosition = distances[secondIndex];
			result = Math.max(result, secondPosition - firstPosition);
		}
		int currentType = breeds[0].getType();
		int index = 1;
		int startIndex = 0;
		int finishIndex = 0;
		while (index < N) {
			if (breeds[index].getType() == currentType) {
				index++;
			} else {
				int firstPosition = distances[startIndex];
				int secondPosition = distances[index - 1];
				result = Math.max(result, secondPosition - firstPosition);
				startIndex = index;
				currentType = -currentType;
			}
		}
		int firstPosition = distances[startIndex];
		int secondPosition = distances[index - 1];
		result = Math.max(result, secondPosition - firstPosition);
		out.println(result);
		out.close();
	}

	public static void printArray(int[] array) {
		for (int i : array)
			System.out.print(i + " ");
		System.out.println();
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			for (int i : is)
				System.out.print(i + " ");
			System.out.println();
		}
	}

}

class breed implements Comparable<breed> {
	public static final int G = 1;
	public static final int H = -1;
	private int type;
	private int index;

	public breed(int index, String s) {
		this.index = index;
		if (s.equals("G"))
			type = G;
		else
			type = H;
	}

	public int compareTo(breed next) {
		return Integer.compare(index, next.index);
	}

	public int getType() {
		return type;
	}

	public int getIndex() {
		return index;
	}
}
