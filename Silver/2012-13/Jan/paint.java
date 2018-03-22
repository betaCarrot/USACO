import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class paint {
	private static int N, K;
	private static ArrayList<Integer> interestings = new ArrayList<Integer>();
	private static int size;
	private static int[] cooerdinates;
	private static int[] inputs;
	private static final int LEFT = 0;
	private static final int RIGHT = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("paint.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		inputs = new int[N];
		int pos = 0;
		interestings.add(0);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int distance = Integer.parseInt(st.nextToken());
			if (st.nextToken().equals("L"))
				distance = -distance;
			inputs[i] = distance;
			pos += distance;
			interestings.add(pos);
		}
		Collections.sort(interestings);
		size = interestings.size();
		cooerdinates = new int[size];
		pos = 0;
		int index = Collections.binarySearch(interestings, 0);
		for (int i = 0; i < N; i++) {
			int next = pos + inputs[i];
			int nextIndex;
			if (inputs[i] > 0) {
				nextIndex = Collections.binarySearch(interestings, next);
				cooerdinates[index]++;
				cooerdinates[nextIndex]--;
			} else {
				nextIndex = Collections.binarySearch(interestings, next);
				cooerdinates[nextIndex]++;
				cooerdinates[index]--;
			}
			index = nextIndex;
			pos = interestings.get(index);
		}
		int result = 0;
		int curr = 0;
		for (int i = 0; i < cooerdinates.length - 1; i++) {
			curr += cooerdinates[i];
			if (curr >= K) {
				result += interestings.get(i + 1) - interestings.get(i);
			}
		}
		out.println(result);
		out.close();
	}

	public static void printArray(int[] array) {
		for (int i : array)
			System.out.print(i + " ");
		System.out.println();
	}
}
