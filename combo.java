import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class combo {
	private static int N;
	// private static HashSet<int[]> set = new HashSet<int[]>();
	private static ArrayList<int[]> list = new ArrayList<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("combo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
		N = Integer.parseInt(f.readLine());
		int[] FJ = new int[3];
		int[] master = new int[3];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < 3; i++) {
			FJ[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < 3; i++) {
			master[i] = Integer.parseInt(st.nextToken());
		}
		if (N <= 5) {
			out.println((int) Math.pow(N, 3));
		} else {
			solve(FJ);
			solve(master);
			removeDuplicates();
			out.println(list.size());
		}
		out.close();
	}

	public static void removeDuplicates() {
		for (int i = 0; i < list.size(); i++) {
			int k = 0;
			while (k < list.size()) {
				if ((k != i) && (equals(list.get(i), list.get(k))))
					list.remove(k);
				else
					k++;
			}
		}
	}

	public static boolean equals(int[] as, int[] bs) {
		return as[0] == bs[0] && as[1] == bs[1] && as[2] == bs[2];
	}

	private static void solve(int[] array) {
		for (int a = -2; a <= 2; a++) {
			for (int b = -2; b <= 2; b++) {
				for (int c = -2; c <= 2; c++) {
					int[] temp = new int[3];
					temp[0] = normalize(array[0] + a);
					temp[1] = normalize(array[1] + b);
					temp[2] = normalize(array[2] + c);
					list.add(temp);
				}
			}
		}
	}

	private static int normalize(int n) {
		if (n <= 0)
			return N + n;
		if (n > N)
			return n - N;
		return n;
	}
}
