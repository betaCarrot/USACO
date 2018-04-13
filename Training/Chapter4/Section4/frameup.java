
/*
ID: majesti2
LANG: JAVA
TASK: frameup
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class frameup {
	public static int N, M, total;
	public static char[][] map;
	public static boolean[][] connected = new boolean[26][26];
	public static int[] inDegree = new int[26];
	public static boolean[] exist = new boolean[26];
	public static char[] answer;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("frameup.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frameup.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			char[] chars = f.readLine().toCharArray();
			for (int k = 0; k < M; k++) {
				map[i][k] = chars[k];
			}
		}
		for (char c = 'A'; c <= 'Z'; c++) {
			for (int i = 0; i < N; i++) {
				for (int k = 0; k < M; k++) {
					if (map[i][k] == c) {
						exist[c - 'A'] = true;
					}
				}
			}
		}
		for (char c = 'A'; c <= 'Z'; c++) {
			if (!exist[c - 'A'])
				continue;
			total++;
			int minR = 31;
			int maxR = 0;
			int minC = 31;
			int maxC = 0;
			for (int i = 0; i < N; i++) {
				for (int k = 0; k < M; k++) {
					if (map[i][k] == c) {
						minR = Math.min(minR, i);
						maxR = Math.max(maxR, i);
						minC = Math.min(minC, k);
						maxC = Math.max(maxC, k);
					}
				}
			}
			int row = minR;
			for (int k = minC; k <= maxC; k++) {
				if (map[row][k] != c) {
					connected[c - 'A'][map[row][k] - 'A'] = true;
				}
			}
			row = maxR;
			for (int k = minC; k <= maxC; k++) {
				if (map[row][k] != c) {
					connected[c - 'A'][map[row][k] - 'A'] = true;
				}
			}
			int col = minC;
			for (int i = minR; i <= maxR; i++) {
				if (map[i][col] != c) {
					connected[c - 'A'][map[i][col] - 'A'] = true;
				}
			}
			col = maxC;
			for (int i = minR; i <= maxR; i++) {
				if (map[i][col] != c) {
					connected[c - 'A'][map[i][col] - 'A'] = true;
				}
			}
		}
		for (int i = 0; i < 26; i++) {
			for (int k = 0; k < 26; k++) {
				if (connected[i][k])
					inDegree[k]++;
			}
		}
		answer = new char[total];
		dfs(0);
		out.print(sb.toString());
		out.close();
	}

	public static void dfs(int index) {
		if (index == total) {
			for (char c : answer) {
				sb.append(c);
			}
			sb.append("\n");
			return;
		}
		for (int i = 0; i < 26; i++) {
			if (exist[i] && inDegree[i] == 0) {
				for (int j = 0; j < 26; j++) {
					if (connected[i][j])
						inDegree[j]--;
				}
				exist[i] = false;
				answer[index] = (char) (i + 'A');
				dfs(index + 1);
				for (int j = 0; j < 26; j++) {
					if (connected[i][j])
						inDegree[j]++;
				}
				exist[i] = true;
			}
		}
	}
}
