import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class crowded {
	private static int N, D;
	private static node[] array;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("crowded.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crowded.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		array = new node[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int index = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			array[i] = new node(index, height);
		}
		Arrays.sort(array);
		TreeSet<node> leftSet = new TreeSet<node>(new Comparator<node>() {
			public int compare(node node1, node node2) {
				return node1.height - node2.height;
			}
		});
		TreeSet<node> rightSet = new TreeSet<node>(new Comparator<node>() {
			public int compare(node node1, node node2) {
				return node1.height - node2.height;
			}
		});
		int result = 0;
		int index = 0;
		int rightIndex = 0;
		int leftIndex = 0;
		while (index < N) {
			leftSet.add(array[index]);
			int pos = array[index].index;
			int targetHeight = array[index].height * 2;
			int rightBound = pos + D;
			int leftBound = pos - D;
			while (rightIndex < N && array[rightIndex].index <= rightBound) {
				rightSet.add(array[rightIndex]);
				rightIndex++;
			}
			while (leftIndex < N && array[leftIndex].index < leftBound) {
				leftSet.remove(array[leftIndex]);
				leftIndex++;
			}
			if (leftSet.last().height >= targetHeight && rightSet.last().height >= targetHeight) {
				result++;
			}
			rightSet.remove(array[index]);
			index++;
		}
		out.println(result);
		out.close();
	}
}

class node implements Comparable<node> {
	public int index, height;

	public node(int a, int b) {
		index = a;
		height = b;
	}

	public String toString() {
		return index + " " + height;
	}

	public int compareTo(node next) {
		if (index == next.index) {
			return height - next.height;
		}
		return index - next.index;
	}
}
