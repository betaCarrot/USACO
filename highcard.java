import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class highcard {
	private static ArrayList<Integer> bCards = new ArrayList<Integer>();
	private static ArrayList<Integer> eCards = new ArrayList<Integer>();
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("highcard.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
		N = Integer.parseInt(f.readLine());
		boolean[] temp = new boolean[2 * N];
		for (int i = 0; i < N; i++) {
			temp[Integer.parseInt(f.readLine()) - 1] = true;
		}
		for (int i = 0; i < 2 * N; i++) {
			if (temp[i])
				eCards.add(i);
			else
				bCards.add(i);
		}
		Collections.sort(eCards);
		Collections.reverse(eCards);
		Collections.sort(bCards);
		Collections.reverse(bCards);
		int eIndex = 0;
		int bIndex = 0;
		int result = 0;
		printArrayListInteger(eCards);
		printArrayListInteger(bCards);
		while (eIndex < N && bIndex < bCards.size()) {
			if (eCards.get(eIndex) > bCards.get(bIndex)) {
				eIndex++;
				bCards.remove(bCards.size() - 1);
			} else {
				result++;
				eIndex++;
				bIndex++;
			}
		}
		out.println(result);
		out.close();
	}

	public static void printArrayListInteger(ArrayList<Integer> array) {
		for (int i : array)
			System.out.print(i + " ");
		System.out.println();
	}
}
