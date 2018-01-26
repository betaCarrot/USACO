import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class fuel {
	private static station[] stations;
	private static int[] nextCheaper;
	private static Stack<station> stack = new Stack<station>();
	private static int N, G, B, D;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fuel.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fuel.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		stations = new station[N + 1];
		nextCheaper = new int[N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int index = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			stations[i] = new station(index, price);
		}
		stations[N] = new station(D, 0);
		Arrays.sort(stations);
		for (int i = N; i >= 0; i--) {
			boolean found = false;
			while (!stack.isEmpty()) {
				station next = stack.peek();
				if (next.price < stations[i].price) {
					nextCheaper[i] = Arrays.binarySearch(stations, next);
					found = true;
					break;
				}
				stack.pop();
			}
			if (!found) {
				nextCheaper[i] = -1;
			}
			stack.push(stations[i]);
		}

		int currentFuel = B;
		int currentPosition = 0;
		long result = 0L;
		for (int i = 0; i < N; i++) {
			// System.out.println("Result: " + result);
			currentFuel -= stations[i].index - currentPosition;
			currentPosition = stations[i].index;
			if (currentFuel < 0) {
				result = -1;
				break;
			}
			int next = nextCheaper[i];
			// System.out.println("currentFuel: " + currentFuel);
			// System.out.println("Next: " + next);
			if (next == -1) {
				result += (long) (G - currentFuel) * (long) (stations[i].price);
				currentFuel = G;
				continue;
			}
			int distance = stations[next].index - stations[i].index;
			if (distance < currentFuel) {
				continue;
			} else if (distance > G) {
				result += (long) (G - currentFuel) * (long) (stations[i].price);
				currentFuel = G;
			} else {
				result += (long) (distance - currentFuel) * (long) (stations[i].price);
				currentFuel += (distance - currentFuel);
			}
		}
		out.println(result);
		out.close();
	}
}

class station implements Comparable<station> {
	public int index, price;

	public station(int a, int b) {
		index = a;
		price = b;
	}

	public int compareTo(station next) {
		return Integer.compare(index, next.index);
	}
}
