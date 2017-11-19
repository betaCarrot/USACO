import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class moocrypt {
	private static int[][] map;
	private static int R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("moocrypt.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocrypt.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			String line = f.readLine();
			for (int k = 0; k < C; k++) {
				map[i][k] = convert(line.substring(k, k + 1));
			}
		}
		int result = 0;
		for (int i = 0; i < 26; i++) {
			for (int k = 0; k < 26; k++) {
				if (i == 12 || k == 14)
					continue;
				if (i == k)
					continue;
				result = Math.max(result, find(i, k));
			}
		}
		out.println(result);
		out.close();
	}

	public static int convert(String s) {
		int ascii = s.charAt(0);
		return ascii - 65;
	}

	public static int find(int a, int b) {
		return findH(a, b) + findV(a, b) + findD(a, b);
	}

	public static int findD(int a, int b) {
		int count = 0;
		for (int i = 0; i < R - 2; i++) {
			for (int k = 0; k < C - 2; k++) {
				if ((map[i][k] == a) && (map[i + 1][k + 1] == b) && (map[i + 2][k + 2] == b))
					count++;
				if ((map[i][k] == b) && (map[i + 1][k + 1] == b) && (map[i + 2][k + 2] == a))
					count++;
				if ((map[i + 2][k] == a) && (map[i + 1][k + 1] == b) && (map[i][k + 2] == b))
					count++;
				if ((map[i + 2][k] == b) && (map[i + 1][k + 1] == b) && (map[i][k + 2] == a))
					count++;
			}
		}
		return count;
	}

	public static int findH(int a, int b) {
		int count = 0;
		for (int i = 0; i < R; i++) {
			for (int k = 0; k < C - 2; k++) {
				if ((map[i][k] == a) && (map[i][k + 1] == b) && (map[i][k + 2] == b))
					count++;
				if ((map[i][k] == b) && (map[i][k + 1] == b) && (map[i][k + 2] == a))
					count++;
			}
		}
		return count;
	}

	public static int findV(int a, int b) {
		int count = 0;
		for (int i = 0; i < R - 2; i++) {
			for (int k = 0; k < C; k++) {
				if ((map[i][k] == a) && (map[i + 1][k] == b) && (map[i + 2][k] == b))
					count++;
				if ((map[i][k] == b) && (map[i + 1][k] == b) && (map[i + 2][k] == a))
					count++;
			}
		}
		return count;
	}
}
