import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class cardgame {
	private static int N;
	private static int[] eCards, bCards;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cardgame.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
		N = Integer.parseInt(f.readLine());
		eCards = new int[N];
		bCards = new int[N];
		boolean[] seen = new boolean[2 * N + 1];
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(f.readLine());
			eCards[i] = temp;
			seen[temp] = true;
		}
		int index = 0;
		for (int i = 1; i <= N * 2; i++) {
			if (!seen[i]) {
				bCards[index] = i;
				index++;
			}
		}
		index = 0;
		ArrayList<Integer> eFirst = new ArrayList<Integer>();
		ArrayList<Integer> eSecond = new ArrayList<Integer>();
		ArrayList<Integer> bFirst = new ArrayList<Integer>();
		ArrayList<Integer> bSecond = new ArrayList<Integer>();
		for (int i = 0; i < N / 2; i++) {
			eFirst.add(eCards[i]);
		}
		for (int i = 0; i < N / 2; i++) {
			eSecond.add(eCards[i + N / 2]);
		}
		Arrays.sort(bCards);
		for (int i = 0; i < N / 2; i++) {
			bFirst.add(bCards[i + N / 2]);
		}
		for (int i = 0; i < N / 2; i++) {
			bSecond.add(bCards[i]);
		}
		int result = 0;
		Collections.sort(eFirst);
		boolean[] used = new boolean[N / 2];
		Collections.sort(bFirst);
		for (int i = 0; i < N / 2; i++) {
			int target = eFirst.get(i);
			index = -Collections.binarySearch(bFirst, target) - 1;
			while (index < N / 2) {
				if (!used[index])
					break;
				index++;
			}
			if (index < N / 2) {
				used[index] = true;
				result++;
			}
		}
		Collections.sort(eSecond);
		Collections.reverse(eSecond);
		Collections.sort(bSecond);
		used = new boolean[N / 2];
		for (int i = 0; i < N / 2; i++) {
			int target = eSecond.get(i);
			index = -Collections.binarySearch(bSecond, target) - 1;
			while (index > 0) {
				if (!used[index - 1])
					break;
				index--;
			}
			if (index > 0) {
				used[index - 1] = true;
				result++;
			}
		}
		out.println(result);
		out.close();
	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
