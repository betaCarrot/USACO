import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class scramble {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("scramble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("scramble.out")));
		int N = Integer.parseInt(f.readLine());
		String[] ss = new String[N];
		for (int i = 0; i < N; i++) {
			ss[i] = f.readLine();
		}
		String[] bestArray = new String[N];
		String[] worstArray = new String[N];
		for (int i = 0; i < N; i++) {
			bestArray[i] = best(ss[i]);
			worstArray[i] = worst(ss[i]);
		}
		Arrays.sort(bestArray);
		Arrays.sort(worstArray);
		for (int i = 0; i < N; i++) {
			String currentBest = best(ss[i]);
			String currentWorst = worst(ss[i]);
			int bestIndex = Arrays.binarySearch(worstArray, currentBest);
			int worstIndex = Arrays.binarySearch(bestArray, currentWorst);
			if (bestIndex < 0)
				bestIndex = -(bestIndex + 1);
			bestIndex++;
			if (worstIndex < 0) {
				worstIndex = -(worstIndex + 1);
				worstIndex--;
			}
			worstIndex++;
			out.println(bestIndex + " " + worstIndex);
		}
		out.close();
	}

	public static String best(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		String result = "";
		for (char c : chars)
			result += c;
		return result;
	}

	public static String worst(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		String result = "";
		for (char c : chars)
			result = c + result;
		return result;
	}
}
