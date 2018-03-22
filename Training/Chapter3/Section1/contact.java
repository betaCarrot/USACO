
/*
 ID: majesti2
 LANG: JAVA
 TASK: contact
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class contact {
	private static int A, B, N, length;
	private static boolean[] input;
	private static int[][] results;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("contact.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = f.readLine()) != null) {
			sb.append(line);
		}
		char[] chars = sb.toString().toCharArray();
		length = chars.length;
		input = new boolean[length];
		for (int i = 0; i < length; i++) {
			if (chars[i] == '1') {
				input[i] = true;
			}
		}
		results = new int[12 + 1][(int) (Math.pow(2, 12))];
		for (int start = 0; start < length; start++) {
			for (int k = A; k <= B; k++) {
				int end = start + k - 1;
				if (end >= length)
					break;
				toIndex(start, end);
			}
		}
		PriorityQueue<struct> pq = new PriorityQueue<struct>();
		for (int i = 1; i <= 12; i++) {
			for (int k = 0; k < results[0].length; k++) {
				if (results[i][k] == 0)
					continue;
				pq.offer(new struct(results[i][k], toString(i, k)));
			}
		}
		sb = new StringBuilder();
		int count = 0;
		while (!pq.isEmpty() && count < N) {
			struct init = pq.poll();
			int amount = init.getAmount();
			count++;
			sb.append(amount).append("\n");
			sb.append(init.getStr()).append(" ");
			int lineCount = 1;
			while (!pq.isEmpty() && pq.peek().getAmount() == amount) {
				struct curr = pq.poll();
				lineCount++;
				if (lineCount < 6)
					sb.append(curr.getStr()).append(" ");
				else {
					sb.append(curr.getStr()).append("\n");
					lineCount = 0;
				}
			}
			sb.delete(sb.length() - 1, sb.length());
			sb.append("\n");
		}
		out.print(sb.toString());
		out.close();
	}

	public static void toIndex(int start, int end) {
		int offset = start;
		int segLength = end - start + 1;
		int result = 0;
		for (int i = start; i <= end; i++) {
			int multiplier = (int) (Math.pow(2, segLength - (i - offset) - 1));
			if (input[i]) {
				result += multiplier;
			}
		}
		results[segLength][result]++;
	}

	public static String toString(int segLength, int index) {
		int start = (int) (Math.pow(2, segLength - 1));
		StringBuilder sb = new StringBuilder();
		while (sb.length() < segLength) {
			if (index / start > 0) {
				sb.append("1");
				index -= start;
			} else
				sb.append("0");
			start /= 2;
		}
		return sb.toString();
	}
}

class struct implements Comparable<struct> {
	private int amount;
	private String str;

	public struct(int a, String s) {
		amount = a;
		str = s;
	}

	public int getAmount() {
		return amount;
	}

	public String getStr() {
		return str;
	}

	public int compareTo(struct next) {
		if (Integer.compare(amount, next.amount) != 0) {
			return -Integer.compare(amount, next.amount);
		}
		if (Integer.compare(str.length(), next.str.length()) != 0) {
			return Integer.compare(str.length(), next.str.length());
		}
		return str.compareTo(next.str);
	}
}
