import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class balancing {
	private static int N;
	private static int result = Integer.MAX_VALUE;
	private static int[][] inputs;
	private static cell[] cells;
	private static ArrayList<Integer> interestingX = new ArrayList<Integer>();
	private static ArrayList<Integer> interestingY = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("balancing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		N = Integer.parseInt(f.readLine());
		inputs = new int[N][2];
		cells = new cell[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			for (int k = 0; k < 2; k++) {
				int temp = Integer.parseInt(st.nextToken());
				inputs[i][k] = temp;
				if (k == 0 && !interestingX.contains(temp)) {
					interestingX.add(temp);
				}
				if (k == 1 && !interestingY.contains(temp)) {
					interestingY.add(temp);
				}
			}
		}
		Collections.sort(interestingX);
		Collections.sort(interestingY);
		for (int i = 0; i < interestingX.size(); i++) {
			int target = interestingX.get(i);
			int replacement = i * 2 + 1;
			for (int index = 0; index < N; index++) {
				if (inputs[index][0] == target) {
					inputs[index][0] = replacement;
				}
			}
		}
		for (int i = 0; i < interestingY.size(); i++) {
			int target = interestingY.get(i);
			int replacement = i * 2 + 1;
			for (int index = 0; index < N; index++) {
				if (inputs[index][1] == target) {
					inputs[index][1] = replacement;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			cells[i] = new cell(inputs[i][1], inputs[i][0]);
		}
		Arrays.sort(cells);
		int maxY = (interestingY.size() - 1) * 2 + 1;
		int maxX = (interestingX.size() - 1) * 2 + 1;
		for (int x = 0; x < maxX; x += 2) {
			int left = 0;
			int right = 0;
			for (cell c : cells) {
				if (c.getX() < x) {
					left++;
				} else {
					right++;
				}
			}
			int y = 0;
			int index = 0;
			int leftBelow = 0;
			int rightBelow = 0;
			while (y < maxY) {
				while (index < N && cells[index].getY() < y) {
					if (cells[index].getX() < x) {
						leftBelow++;
					} else {
						rightBelow++;
					}
					index++;
				}
				int count = max(leftBelow, rightBelow, left - leftBelow, right - rightBelow);
				result = Math.min(result, count);
				y += 2;
			}
		}
		out.println(result);
		out.close();
	}

	public static int max(int a, int b, int c, int d) {
		return Math.max(a, Math.max(b, Math.max(c, d)));
	}
}

class cell implements Comparable<cell> {
	private int y;
	private int x;

	public cell(int y, int x) {
		this.y = y;
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public int compareTo(cell next) {
		if (Integer.compare(y, next.y) != 0) {
			return Integer.compare(y, next.y);
		}
		return Integer.compare(x, next.x);
	}
}
