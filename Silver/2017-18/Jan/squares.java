import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class squares {
	private static int N, K;
	private static square[] squares;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("squares.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("squares.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		squares = new square[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			squares[i] = new square(x, y);
		}
		Arrays.sort(squares);
		int result = 0;
		for (int i = 0; i < N; i++) {
			int count = 0;
			for (int k = i + 1; k < N; k++) {
				if (overLap(squares[i], squares[k])) {
					count++;
					result += area(squares[i], squares[k]);
				}
				if (squares[k].x >= squares[i].x + K) {
					break;
				}
			}
			if (count > 1) {
				result = -1;
				break;
			}
		}
		out.println(result);
		out.close();
	}

	public static boolean overLap(square a, square b) {
		return Math.abs(a.x - b.x) < K && Math.abs(a.y - b.y) < K;
	}

	public static int area(square a, square b) {
		int x1 = Math.min(a.x, b.x);
		int x2 = Math.max(a.x, b.x);
		int y1 = Math.min(a.y, b.y);
		int y2 = Math.max(a.y, b.y);
		return (x1 - x2 + K) * (y1 - y2 + K);
	}
}

class square implements Comparable<square> {
	public int x, y;

	public square(int a, int b) {
		x = a;
		y = b;
	}

	public int compareTo(square next) {
		if (x == next.x) {
			return y - next.y;
		}
		return x - next.x;
	}
}
