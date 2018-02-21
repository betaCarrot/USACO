import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class lineup {
	private static int N, K;
	private static int[] array, counts;
	private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lineup.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lineup.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		array = new int[N];
		int count = 0;
		for (int i = 0; i < N; i++) {
			int breed = Integer.parseInt(f.readLine());
			array[i] = breed;
			if (!map.containsKey(breed)) {
				map.put(breed, count);
				count++;
			}
		}
		counts = new int[count];
		int result = 0;
		int left = 0;
		int right = 0;
		int breed = 0;
		TreeSet<node> set = new TreeSet<node>();
		for (int i = 0; i < count; i++) {
			set.add(new node(i, 0));
		}
		while (right < N) {
			while (right < N) {
				int index = map.get(array[right]);
				if (counts[index] == 0) {
					breed++;
				}
				if (breed > K + 1) {
					breed--;
					break;
				}
				set.remove(new node(index, counts[index]));
				counts[index]++;
				set.add(new node(index, counts[index]));
				right++;
			}
			result = Math.max(result, set.first().value);
			int index = map.get(array[left]);
			set.remove(new node(index, counts[index]));
			counts[index]--;
			set.add(new node(index, counts[index]));
			if (counts[index] == 0) 
				breed--;
			left++;
		}
		out.println(result);
		out.close();
	}
}

class node implements Comparable<node> {
	public int index, value;

	public node(int a, int b) {
		index = a;
		value = b;
	}

	public int compareTo(node next) {
		return next.value - value;
	}
}
