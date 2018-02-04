import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class meeting {
	private static int N, M;
	private static ArrayList<ArrayList<edge>> edges = new ArrayList<ArrayList<edge>>();
	private static HashSet<Integer> bs = new HashSet<Integer>();
	private static HashSet<Integer> es = new HashSet<Integer>();
	private static HashMap<Integer, HashSet<Integer>> bMap = new HashMap<Integer, HashSet<Integer>>();
	private static HashMap<Integer, HashSet<Integer>> eMap = new HashMap<Integer, HashSet<Integer>>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("meeting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("meeting.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			edges.add(new ArrayList<edge>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int from = Math.min(a, b);
			int to = Math.max(a, b);
			int bTime = Integer.parseInt(st.nextToken());
			int eTime = Integer.parseInt(st.nextToken());
			edges.get(from).add(new edge(to, bTime, eTime));
		}
		bs = dfsB(0);
		es = dfsE(0);
		int result = Integer.MAX_VALUE;
		for (int i : bs) {
			if (es.contains(i)) {
				result = Math.min(result, i);
			}
		}
		if (result != Integer.MAX_VALUE) {
			out.println(result);
		} else {
			out.println("IMPOSSIBLE");
		}
		out.close();
	}

	public static HashSet<Integer> dfsB(int index) {
		HashSet<Integer> result = new HashSet<Integer>();
		if (index == N - 1) {
			result.add(0);
			return result;
		}
		if (bMap.containsKey(index)) {
			return bMap.get(index);
		}
		for (edge e : edges.get(index)) {
			HashSet<Integer> ret = dfsB(e.to);
			HashSet<Integer> temp = new HashSet<Integer>();
			for (int i : ret) {
				temp.add(i + e.bTime);
			}
			result.addAll(temp);
		}
		bMap.put(index, result);
		return result;
	}

	public static HashSet<Integer> dfsE(int index) {
		HashSet<Integer> result = new HashSet<Integer>();
		if (index == N - 1) {
			result.add(0);
			return result;
		}
		if (eMap.containsKey(index)) {
			return eMap.get(index);
		}
		for (edge e : edges.get(index)) {
			HashSet<Integer> ret = dfsE(e.to);
			HashSet<Integer> temp = new HashSet<Integer>();
			for (int i : ret) {
				temp.add(i + e.eTime);
			}
			result.addAll(temp);
		}
		eMap.put(index, result);
		return result;
	}
}

class edge {
	public int to, bTime, eTime;

	public edge(int a, int b, int c) {
		to = a;
		bTime = b;
		eTime = c;
	}
}
