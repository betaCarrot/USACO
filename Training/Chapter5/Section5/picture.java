
/*
ID: majesti2
LANG: JAVA
TASK: picture
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class picture {
	public static int N;
	public static Line[] hLines, vLines;
	public static int[] currs;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("picture.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("picture.out")));
		N = Integer.parseInt(f.readLine());
		hLines = new Line[2 * N];
		vLines = new Line[2 * N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x1 = Integer.parseInt(st.nextToken()) + 10000;
			int y1 = Integer.parseInt(st.nextToken()) + 10000;
			int x2 = Integer.parseInt(st.nextToken()) + 10000;
			int y2 = Integer.parseInt(st.nextToken()) + 10000;
			hLines[i * 2] = new Line(x1, x2, y1, true);
			hLines[i * 2 + 1] = new Line(x1, x2, y2, false);
			vLines[i * 2] = new Line(y1, y2, x1, true);
			vLines[i * 2 + 1] = new Line(y1, y2, x2, false);
		}
		currs = new int[20005];
		Arrays.sort(hLines);
		Arrays.sort(vLines);
		int res = 0;
		for (Line l : hLines) {
			if (l.up) {
				for (int i = l.x1; i < l.x2; i++) {
					currs[i]++;
					if (currs[i] == 1) {
						res++;
					}
				}
			} else {
				for (int i = l.x1; i < l.x2; i++) {
					currs[i]--;
					if (currs[i] == 0) {
						res++;
					}
				}
			}
		}
		currs = new int[20005];
		for (Line l : vLines) {
			if (l.up) {
				for (int i = l.x1; i < l.x2; i++) {
					currs[i]++;
					if (currs[i] == 1) {
						res++;
					}
				}
			} else {
				for (int i = l.x1; i < l.x2; i++) {
					currs[i]--;
					if (currs[i] == 0) {
						res++;
					}
				}
			}
		}
		out.println(res);
		out.close();
	}
}

class Line implements Comparable<Line> {
	public int x1, x2, y;
	public boolean up;

	public Line(int a, int b, int c, boolean d) {
		x1 = a;
		x2 = b;
		y = c;
		up = d;
	}

	public int compareTo(Line next) {
		if (y == next.y) {
			if (up) {
				if (next.up) {
					return 0;
				}
				return -1;
			}
			if (next.up) {
				return 1;
			}
			return 0;
		}
		return y - next.y;
	}
}