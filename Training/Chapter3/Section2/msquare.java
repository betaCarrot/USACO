
/*
 ID: majesti2
 LANG: JAVA
 TASK: msquare
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class msquare {
	private static int[] target, init;
	private static int res;
	private static String sresult;
	private static final int MAX = 18831570;
	private static boolean[] visited = new boolean[MAX];

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("msquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
		init = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
		target = new int[8];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < 8; i++) {
			target[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		bfs();
		out.println(res);
		out.println(sresult);
		out.close();
	}

	public static int toIndex(int[] input) {
		int result = 0;
		for (int i = 0; i < 8; i++) {
			int power = 8 - i - 1;
			result += input[i] * (int) (Math.pow(8, power));
		}
		return result;
	}

	public static void bfs() {
		int count = 0;
		int result = -1;
		ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
		ArrayDeque<String> squeue = new ArrayDeque<String>();
		queue.offer(init);
		squeue.offer("");
		ArrayList<String> sresults = new ArrayList<String>();
		while (result == -1) {
			ArrayDeque<int[]> temp = new ArrayDeque<int[]>();
			ArrayDeque<String> stemp = new ArrayDeque<String>();
			while (!queue.isEmpty()) {
				int[] curr = queue.poll();
				String currS = squeue.poll();
				int index = toIndex(curr);
				if (visited[index])
					continue;
				visited[index] = true;
				if (check(curr)) {
					result = count;
					sresults.add(currS);
				}
				if (!(currS.length() >= 1 && currS.substring(currS.length() - 1).equals("A"))) {
					temp.add(transformA(curr));
					stemp.add(currS + "A");
				}
				if (!(currS.length() >= 3 && currS.substring(currS.length() - 3).equals("BBB"))) {
					temp.add(transformB(curr));
					stemp.add(currS + "B");
				}
				if (!(currS.length() >= 3 && currS.substring(currS.length() - 3).equals("CCC"))) {
					temp.add(transformC(curr));
					stemp.add(currS + "C");
				}
			}
			queue.addAll(temp);
			squeue.addAll(stemp);
			count++;
		}
		res = result;
		sresult = sresults.get(0);
	}

	public static boolean check(int[] input) {
		for (int i = 0; i < 8; i++) {
			if (input[i] != target[i])
				return false;
		}
		return true;
	}

	public static int[] transformA(int[] input) {
		int[] output = new int[8];
		output[0] = input[7];
		output[1] = input[6];
		output[2] = input[5];
		output[3] = input[4];
		output[4] = input[3];
		output[5] = input[2];
		output[6] = input[1];
		output[7] = input[0];
		return output;
	}

	public static int[] transformB(int[] input) {
		int[] output = new int[8];
		output[0] = input[3];
		output[1] = input[0];
		output[2] = input[1];
		output[3] = input[2];
		output[4] = input[5];
		output[5] = input[6];
		output[6] = input[7];
		output[7] = input[4];
		return output;
	}

	public static int[] transformC(int[] input) {
		int[] output = new int[8];
		output[0] = input[0];
		output[1] = input[6];
		output[2] = input[1];
		output[3] = input[3];
		output[4] = input[4];
		output[5] = input[2];
		output[6] = input[5];
		output[7] = input[7];
		return output;
	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
