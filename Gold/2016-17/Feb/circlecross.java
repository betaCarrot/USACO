import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class circlecross {
	private static int N;
	private static event[] events;
	private static int[] starts;

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new FileReader("circlecross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
		N = Integer.parseInt(f.readLine());
		starts = new int[N];
		events = new event[2 * N];
		for (int i = 0; i < N; i++) {
			starts[i] = -1;
		}
		for (int i = 0; i < 2 * N; i++) {
			int num = Integer.parseInt(f.readLine()) - 1;
			if (starts[num] == -1) {
				starts[num] = i;
				events[i] = new event(num, true);
			} else {
				events[i] = new event(num, false);
			}
		}
		int result = 0;
		BIT bit = new BIT(2 * N);
		for (int i = 0; i < 2 * N; i++) {
			event e = events[i];
			if (e.add) {
				bit.update(i, 1);
			} else {
				int start = starts[e.number];
				result += bit.query(i) - bit.query(start);
				bit.update(start, -1);
			}
		}
		out.println(result);
		out.close();

	}
}

class event {
	public int number;
	public boolean add;

	public event(int b, boolean c) {
		number = b;
		add = c;
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
