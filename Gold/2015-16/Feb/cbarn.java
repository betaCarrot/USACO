import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class cbarn {
	private static int N;
	private static int[] array, original;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		N = Integer.parseInt(f.readLine());
		array = new int[N];
		original = new int[N];
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(f.readLine());
			original[i] = temp;
			array[i] = temp;
		}
		long result = 0;
		while (true) {
			result = 0;
			rotate(1);
			ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
			boolean valid = true;
			for (int i = 0; i < N; i++) {
				for (int k = 0; k < array[i]; k++) {
					queue.offer(i);
				}
				if (queue.size() == 0) {
					valid = false;
					break;
				}
				int start = queue.poll();
				result += (long) (i - start) * (i - start);
			}
			if (valid) {
				break;
			}
		}
		out.println(result);
		out.close();
	}

	public static void rotate(int offset) {
		int[] temp = new int[N];
		for (int i = 0; i < offset; i++) {
			temp[i] = array[i];
		}
		for (int i = offset; i < N; i++) {
			array[i - offset] = array[i];
		}
		for (int i = 0; i < offset; i++) {
			array[N - offset + i] = temp[i];
		}
	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
