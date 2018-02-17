import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class lineup {
	private static int N, numBreeds;
	private static cow[] cows;
	private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("lineup.in"));
		PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter("C:\\Users\\majesticdennis\\Desktop\\judge\\lineup.out")));
		N = Integer.parseInt(f.readLine());
		cows = new cow[N];
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int pos = Integer.parseInt(st.nextToken());
			int breed = Integer.parseInt(st.nextToken());
			cows[i] = new cow(pos, breed);
			set.add(breed);
		}
		numBreeds = set.size();
		Arrays.sort(cows);
		int left = 0;
		int right = 0;
		int count = 0;
		int result = Integer.MAX_VALUE;
		while (right < N) {
			while (right < N && count != numBreeds) {
				int breed = cows[right].breed;
				if (!map.containsKey(breed) || map.get(breed) == 0) {
					count++;
				}
				if (!map.containsKey(breed))
					map.put(breed, 1);
				else
					map.put(breed, map.get(breed) + 1);
				right++;
			}
			if (count != numBreeds)
				break;
			while (left <= right) {
				int breed = cows[left].breed;
				result = Math.min(result, cows[right - 1].pos - cows[left].pos);
				left++;
				map.put(breed, map.get(breed) - 1);
				if (map.get(breed) == 0) {
					count--;
					break;
				}
			}
		}
		out.println(result);
		out.close();
		System.err.println(System.currentTimeMillis() - startTime);
	}
}

class cow implements Comparable<cow> {
	public int pos, breed;

	public cow(int a, int b) {
		pos = a;
		breed = b;
	}

	public int compareTo(cow next) {
		return pos - next.pos;
	}
}
