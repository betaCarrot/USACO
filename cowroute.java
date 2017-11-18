import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowroute {
	private static int[][] map;
	private static int[] costs;
	private static int A, B, N;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowroute.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowroute.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][500];
		costs = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			costs[i] = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(f.readLine());
			for (int n = 0; n < k; n++) {
				map[i][n] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			if (search(map[i])) {
				result = Math.min(result, costs[i]);
			}
		}
		for (int i = 0; i < N; i++) {
			int aIndex = findIndex(map[i], A);
			if (aIndex == -1)
				continue;
			int cost1 = costs[i];
			for (int k = aIndex + 1; k < map[i].length; k++) {
				if (map[i][k] == 0)
					break;
				int medium = map[i][k];
				for (int index = 0; index < N; index++) {
					if (search(map[index], medium)) {
						result = Math.min(result, cost1 + costs[index]);
					}
				}
			}
		}
		if (result == Integer.MAX_VALUE)
			out.println(-1);
		else
			out.println(result);
		out.close();
	}

	public static boolean search(int[] array, int medium) {
		int aIndex = findIndex(array, medium);
		int bIndex = findIndex(array, B);
		if (aIndex == -1 || bIndex == -1)
			return false;
		if (aIndex > bIndex)
			return false;
		return true;
	}

	public static boolean search(int[] array) {
		int aIndex = findIndex(array, A);
		int bIndex = findIndex(array, B);
		if (aIndex == -1 || bIndex == -1)
			return false;
		if (aIndex > bIndex)
			return false;
		return true;
	}

	public static int findIndex(int[] array, int target) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == target)
				return i;
		}
		return -1;
	}
}
