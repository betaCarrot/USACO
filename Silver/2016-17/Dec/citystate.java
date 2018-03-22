import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class citystate {
	private static node[] nodes;
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("citystate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
		N = Integer.parseInt(f.readLine());
		nodes = new node[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			String city = st.nextToken();
			String state = st.nextToken();
			nodes[i] = new node(city, state);
		}
		Arrays.sort(nodes);
		int result = 0;
		for (int i = 0; i < N; i++) {
			node curr = nodes[i];
			node next = new node(curr.getState(), curr.getCity());
			if (curr.compareTo(next) == 0)
				continue;
			int index = Arrays.binarySearch(nodes, next);
			if (index >= 0) {
				int leftIndex = index;
				int rightIndex = index + 1;
				while (rightIndex < N && nodes[rightIndex].compareTo(next) == 0) {
					rightIndex++;
					result++;
				}
				while (leftIndex >= 0 && nodes[leftIndex].compareTo(next) == 0) {
					leftIndex--;
					result++;
				}
			}
		}
		out.println(result / 2);
		out.close();
	}
}

class node implements Comparable<node> {
	private String city;
	private String state;

	public node(String c, String s) {
		city = c.substring(0, 2);
		state = s;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String toString() {
		return city + " " + state;
	}

	public int compareTo(node next) {
		if (city.compareTo(next.city) != 0) {
			return city.compareTo(next.city);
		}
		return state.compareTo(next.state);
	}
}
