import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class overplanting {
	private static int N;
	private static ArrayList<Integer> ys = new ArrayList<Integer>();
	private static int[][] inputs;
	private static int size;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("overplanting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("overplanting.out")));
		N = Integer.parseInt(f.readLine());
		inputs = new int[N][4];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			inputs[i][0] = x1;
			inputs[i][1] = y1;
			inputs[i][2] = x2;
			inputs[i][3] = y2;
			if (!ys.contains(y1)) {
				ys.add(y1);
			}
			if (!ys.contains(y2)) {
				ys.add(y2);
			}
		}
		size = ys.size();
		Collections.sort(ys);
		long result = 0;
		for (int i = 0; i < size - 1; i++) {
			int high = ys.get(i + 1);
			int low = ys.get(i);
			ArrayList<node> nodes = new ArrayList<node>();
			for (int k = 0; k < N; k++) {
				if (inputs[k][1] >= high && inputs[k][3] <= low) {
					nodes.add(new node(inputs[k][0], inputs[k][2]));
				}
			}
			result += solve(nodes) * (high - low);
		}
		out.println(result);
		out.close();
	}

	public static long solve(ArrayList<node> nodes) {
		if (nodes.size() == 0)
			return 0;
		Collections.sort(nodes);
		long result = 0;
		int right = nodes.get(0).left;
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).right <= right) {
				continue;
			}
			if (nodes.get(i).left >= right) {
				result += nodes.get(i).right - nodes.get(i).left;
			} else {
				result += nodes.get(i).right - right;
			}
			right = nodes.get(i).right;
		}
		return result;
	}
}

class node implements Comparable<node> {
	public int left, right;

	public node(int a, int b) {
		left = a;
		right = b;
	}

	public int compareTo(node next) {
		if (left == next.left) {
			return right - next.right;
		}
		return left - next.left;
	}
}