import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class circlecross {
	private static int[][] crossings = new int[26][2];
	private static int[] counts = new int[26];
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("circlecross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
		String line = f.readLine();
		for (int i = 0; i < 52; i++) {
			int index = toIndex(line.substring(i, i + 1));
			crossings[index][counts[index]] = i;
			counts[index]++;
		}
		for (int i = 0; i < 26; i++) {
			int start1 = crossings[i][0];
			int end1 = crossings[i][1];
			for (int k = 0; k < 26; k++) {
				if (i == k)
					continue;
				int start2 = crossings[k][0];
				int end2 = crossings[k][1];
				if (cross(start1, end1, start2, end2))
					result++;
			}
		}
		out.println(result / 2);
		out.close();
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			for (int i : is)
				System.out.print(i + " ");
			System.out.println();
		}
	}

	public static boolean cross(int start1, int end1, int start2, int end2) {
		if (end2 < start1)
			return false;
		if (end1 < start2)
			return false;
		if (start2 > start1 && end2 < end1)
			return false;
		if (start1 > start2 && end1 < end2)
			return false;
		return true;
	}

	public static int toIndex(String s) {
		int ascii = (int) (s.charAt(0));
		return ascii - 65;
	}
}
