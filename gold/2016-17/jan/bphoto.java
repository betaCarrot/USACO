import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class bphoto {
	private static int N;
	private static cow[] cows;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("bphoto.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
		N = Integer.parseInt(f.readLine());
		cows = new cow[N];
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(f.readLine());
			cows[i] = new cow(i, height);
		}
		Arrays.sort(cows);
		BIT bit = new BIT(N);
		int result = 0;
		for (int i = 0; i < N; i++) {
			int left = bit.query(cows[i].pos);
			int right = i - left;
			int max = Math.max(left, right);
			int min = Math.min(left, right);
			if (min * 2 < max) {
				result++;
			}
			bit.update(cows[i].pos, 1);
		}
		out.println(result);
		out.close();
	}
}

class cow implements Comparable<cow> {
	public int pos, height;

	public cow(int a, int b) {
		pos = a;
		height = b;
	}

	public int compareTo(cow next) {
		return next.height - height;
	}
}

class BIT {
	public int[] tree;

	public BIT(int n) {
		tree = new int[n + 5];
	}

	public void update(int index, int val) {
		index++;
		while (index < tree.length) {
			tree[index] += val;
			index += index & -index;
		}
	}

	public int query(int index) {
		int ret = 0;
		index++;
		while (index > 0) {
			ret += tree[index];
			index -= index & -index;
		}
		return ret;
	}
}
