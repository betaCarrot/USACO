
/*
ID: majesti2
LANG: JAVA
TASK:schlnet
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class schlnet {
	public static int N;
	public static ArrayList<ArrayList<Integer>> fEdges = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>> bEdges = new ArrayList<ArrayList<Integer>>();
	public static boolean[] isTop, reachableT, isBottom, reachableB, visited;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("schlnet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("schlnet.out")));
		N = Integer.parseInt(f.readLine());
		isTop = new boolean[N];
		reachableT = new boolean[N];
		isBottom = new boolean[N];
		reachableB = new boolean[N];
		for (int i = 0; i < N; i++) {
			fEdges.add(new ArrayList<Integer>());
			bEdges.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			while (true) {
				int to = Integer.parseInt(st.nextToken()) - 1;
				if (to == -1)
					break;
				fEdges.get(i).add(to);
				bEdges.get(to).add(i);
			}
		}
		int top = solve1();
		int bottom = solve2();
		out.println(top);
		if (check())
			out.println(0);
		else
			out.println(Math.max(top, bottom));
		out.close();
	}

	public static boolean check() {
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			if (visit(i) != N)
				return false;
		}
		return true;
	}

	public static int visit(int i) {
		if (visited[i])
			return 0;
		visited[i] = true;
		int res = 1;
		for (int j : fEdges.get(i)) {
			res += visit(j);
		}
		return res;
	}

	public static int solve1() {
		for (int i = 0; i < N; i++) {
			if (!reachableT[i]) {
				visited = new boolean[N];
				isTop[i] = true;
				for (int j : fEdges.get(i)) {
					dfs1(j, i);
				}
			}
		}
		int res = 0;
		for (boolean b : isTop) {
			if (b)
				res++;
		}
		return res;
	}

	public static void dfs1(int i, int top) {
		if (i == top)
			return;
		if (visited[i])
			return;
		visited[i] = true;
		reachableT[i] = true;
		isTop[i] = false;
		for (int j : fEdges.get(i)) {
			dfs1(j, top);
		}
	}

	public static int solve2() {
		for (int i = 0; i < N; i++) {
			if (!reachableB[i]) {
				visited = new boolean[N];
				isBottom[i] = true;
				for (int j : bEdges.get(i)) {
					dfs2(j, i);
				}
			}
		}
		int res = 0;
		for (boolean b : isBottom) {
			if (b)
				res++;
		}
		return res;
	}

	public static void dfs2(int i, int bottom) {
		if (i == bottom)
			return;
		if (visited[i])
			return;
		visited[i] = true;
		reachableB[i] = true;
		isBottom[i] = false;
		for (int j : bEdges.get(i)) {
			dfs2(j, bottom);
		}
	}
}
