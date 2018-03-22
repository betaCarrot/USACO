
/*
 ID: majesti2
 LANG: JAVA
 TASK:spin
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class spin {
	private static int[][][] wheels, originals;
	private static int[] sizes, speeds;
	private static final int N = 5;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("spin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
		sizes = new int[N];
		speeds = new int[N];
		wheels = new int[N][N][2];
		originals = new int[N][N][2];
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			speeds[i] = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			sizes[i] = size;
			for (int k = 0; k < size; k++) {
				int start = Integer.parseInt(st.nextToken());
				int extent = Integer.parseInt(st.nextToken());
				int end = normalize(start + extent);
				wheels[i][k][0] = start;
				wheels[i][k][1] = end;
				originals[i][k][0] = start;
				originals[i][k][1] = end;
			}
		}
		int time = 0;
		while (true) {
			if (check()) {
				break;
			}
			if (time != 0 && origin()) {
				time = -1;
				break;
			}
			rotate();
			time++;
		}
		if (time != -1)
			out.println(time);
		else
			out.println("none");
		out.close();
	}

	public static boolean origin() {
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				for (int m = 0; m < 2; m++) {
					if (wheels[i][k][m] != originals[i][k][m])
						return false;
				}
			}
		}
		return true;
	}

	public static void rotate() {
		for (int i = 0; i < N; i++) {
			int turn = speeds[i];
			for (int k = 0; k < sizes[i]; k++) {
				wheels[i][k][0] = normalize(wheels[i][k][0] + turn);
				wheels[i][k][1] = normalize(wheels[i][k][1] + turn);
			}
		}
	}

	public static boolean check() {
		for (int i = 0; i < 360; i++) {
			if (check(i))
				return true;
		}
		return false;
	}

	public static boolean check(int angle) {
		for (int i = 0; i < N; i++) {
			int[][] wedges = wheels[i];
			boolean valid = false;
			for (int k = 0; k < sizes[i]; k++) {
				int start = wedges[k][0];
				int end = wedges[k][1];
				if (within(start, end, angle)) {
					valid = true;
					break;
				}
			}
			if (!valid)
				return false;
		}
		return true;
	}

	public static boolean within(int start, int end, int angle) {
		if (end >= start)
			return angle <= end && angle >= start;
		else
			return angle <= end || angle >= start;
	}

	public static int normalize(int angle) {
		while (angle < 0)
			angle += 360;
		while (angle >= 360)
			angle -= 360;
		return angle;
	}
}
