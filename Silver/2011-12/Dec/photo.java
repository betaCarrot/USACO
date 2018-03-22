import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class photo {
	private static int N;
	private static ArrayList<Integer> list = new ArrayList<Integer>();
	private static int[][] pos;
	private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	private static Comparator<Integer> comp = new Comparator<Integer>() {
		public int compare(Integer a, Integer b) {
			int indexA = map.get(a);
			int indexB = map.get(b);
			int count = 0;
			for (int i = 0; i < 5; i++) {
				if (pos[i][indexA] < pos[i][indexB]) {
					count++;
				}
			}
			if (count >= 3)
				return -1;
			else
				return 1;
		}
	};

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("photo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("photo.out")));
		N = Integer.parseInt(f.readLine());
		pos = new int[5][N];
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(f.readLine().trim());
			list.add(num);
			map.put(num, i);
			pos[0][i] = i;
		}
		for (int c = 1; c <= 4; c++) {
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(f.readLine());
				int index = map.get(num);
				pos[c][index] = i;
			}
		}
		Collections.sort(list, comp);
		StringBuilder sb = new StringBuilder();
		for (int i : list) {
			sb.append(i).append("\n");
		}
		out.print(sb.toString());
		out.close();
		System.err.println(System.currentTimeMillis() - startTime);
	}
}
