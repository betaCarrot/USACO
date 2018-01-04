
/*
 ID: majesti2
 LANG: JAVA
 TASK: heritage
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class heritage {
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("heritage.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));
		String in = f.readLine();
		String pre = f.readLine();
		solve(in, pre);
		out.println(sb.toString());
		out.close();
	}

	public static void solve(String in, String pre) {
		String root = pre.substring(0, 1);
		String left = in.substring(0, in.indexOf(root));
		String right = in.substring(in.indexOf(root) + 1);
		System.out.println("root: " + root);
		System.out.println("left: " + left);
		System.out.println("right: " + right);
		System.out.println("pre: " + pre);
		int index = 1;
		while (index < pre.length() && left.indexOf(pre.substring(index, index + 1)) >= 0) {
			index++;
		}
		index--;
		System.out.println(index);
		System.out.println();
		if (left.length() <= 1) {
			sb.append(left);
		} else {
			String preLeft = pre.substring(1, index + 1);
			solve(left, preLeft);
		}
		if (right.length() <= 1) {
			sb.append(right);
		} else {
			String preRight = pre.substring(index + 1);
			solve(right, preRight);
		}
		sb.append(root);
	}
}
