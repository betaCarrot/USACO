import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class speeding {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("speeding.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("speeding.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		ArrayDeque<segment> pqr = new ArrayDeque<segment>();
		ArrayDeque<segment> pqb = new ArrayDeque<segment>();
		for (int i = 0; i < a; i++) {
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			int l = Integer.parseInt(st1.nextToken());
			int s = Integer.parseInt(st1.nextToken());
			pqr.offer(new segment(l, s));
		}
		for (int i = 0; i < b; i++) {
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			int l = Integer.parseInt(st1.nextToken());
			int s = Integer.parseInt(st1.nextToken());
			pqb.offer(new segment(l, s));
		}

		int rl = 0;
		int bl = 0;
		int result = 0;

		while (!(pqr.isEmpty() && pqb.isEmpty())) {
			segment rs = pqr.peek();
			segment bs = pqb.peek();
			int rsa = rs.getLength();
			int rss = rs.getSpeed();
			int bsa = bs.getLength();
			int bss = bs.getSpeed();
			if (rsa + rl < bsa + bl) {
				rl = rsa + rl;
				if (bss > rss)
					result = Math.max(result, bss - rss);
				pqr.poll();
			} else if (rsa + rl > bsa + bl) {
				bl = bsa + bl;
				if (bss > rss)
					result = Math.max(result, bss - rss);
				pqb.poll();
			} else {
				rl = rsa + rl;
				bl = bsa + bl;
				if (bss > rss)
					result = Math.max(result, bss - rss);
				pqr.poll();
				pqb.poll();
			}
		}
		out.println(result);
		out.close();
	}
}

class segment {
	private int length;
	private int speed;

	public segment(int l, int s) {
		length = l;
		speed = s;
	}

	public int getLength() {
		return length;
	}

	public int getSpeed() {
		return speed;
	}
}
