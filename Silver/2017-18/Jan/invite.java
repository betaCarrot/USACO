import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class invite {
	private static int N, G;
	private static ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<TreeSet<Integer>> list = new ArrayList<TreeSet<Integer>>();
	private static boolean[] invited;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("invite.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("invite.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		invited = new boolean[N];
		for (int i = 0; i < N; i++) {
			map.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < G; i++) {
			list.add(new TreeSet<Integer>());
		}
		for (int i = 0; i < G; i++) {
			st = new StringTokenizer(f.readLine());
			int size = Integer.parseInt(st.nextToken());
			for (int k = 0; k < size; k++) {
				int temp = Integer.parseInt(st.nextToken()) - 1;
				map.get(temp).add(i);
				list.get(i).add(temp);
			}
		}
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(0);
		int result = 1;
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			for (int i : map.get(curr)) {
				list.get(i).remove(curr);
				if (list.get(i).size() == 1) {
					if (!invited[list.get(i).first()]) {
						invited[list.get(i).first()] = true;
						queue.offer(list.get(i).pollFirst());
						result++;
					}
				}
			}
		}
		out.println(result);
		out.close();
	}
}
