
/*
 ID: majesti2
 LANG: JAVA
 TASK: fence9
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class fence9 {
	private static point a, b, c, e;
	private static double area, slope1, slope2, intercept, rightArea, wholeArea;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fence9.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		a = new point(0, 0);
		b = new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		c = new point(Integer.parseInt(st.nextToken()), 0);
		e = new point(b.getX(), 0);
		area = area(a, b, c);
		rightArea = area(b, c, e);
		wholeArea = area(a, b, e);
		int result = 0;
		slope1 = (double) b.getY() / (double) b.getX();
		slope2 = (double) (-b.getY()) / (double) (c.getX() - b.getX());
		intercept = (double) (c.getX() * b.getY()) / (double) (c.getX() - b.getX());
		if (b.getX() <= c.getX()) {
			for (int x = 1; x < Math.max(b.getX(), c.getX()); x++) {
				result += binarySearch(x) - 1;
			}
		} else {
			for (int x = 1; x <= c.getX(); x++) {
				result += Math.max(binarySearch(x) - 1, 0);
			}
			for (int x = c.getX() + 1; x < b.getX(); x++) {
				result += binarySearchWhole(x) - binarySearchRight(x);
			}
		}
		out.println(result);
		out.close();
	}

	public static boolean isOnLineUp(point d) {
		int x = d.getX();
		int y = d.getY();
		double y1 = x * slope1;
		return equals(y, y1);
	}

	public static int binarySearchRight(int x) {
		int low = 0;
		int high = b.getY();
		while (low != high) {
			int middle = (low + high) / 2;
			point d = new point(x, middle);
			if (!checkRight(d)) {
				high = middle;
			} else {
				low = middle + 1;
			}
		}
		return (low + high) / 2;
	}

	public static int binarySearchWhole(int x) {
		int low = 0;
		int high = b.getY();
		while (low != high) {
			int middle = (low + high) / 2;
			point d = new point(x, middle);
			if (!(checkWhole(d) && !isOnLineUp(d))) {
				high = middle;
			} else {
				low = middle + 1;
			}
		}
		return (low + high) / 2;
	}

	public static boolean checkRight(point d) {
		double result = area(b, c, d) + area(c, d, e) + area(b, d, e);
		return result <= rightArea || equals(result, rightArea);
	}

	public static boolean checkWhole(point d) {
		double result = area(a, b, d) + area(a, d, e) + area(b, d, e);
		return result <= wholeArea;
	}

	public static int binarySearch(int x) {
		int low = 0;
		int high = b.getY();
		while (low != high) {
			int middle = (low + high) / 2;
			point d = new point(x, middle);
			if (!valid(d)) {
				high = middle;
			} else {
				low = middle + 1;
			}
		}
		return (low + high) / 2;
	}

	public static boolean valid(point d) {
		return check(d) && !isOnLine(d);
	}

	public static boolean isOnLine(point d) {
		int x = d.getX();
		int y = d.getY();
		if (b.getX() == 0 && d.getX() == 0) {
			return true;
		}
		double y1 = x * slope1;
		if (equals(y, y1))
			return true;
		if (b.getX() == c.getX() && d.getX() == c.getX()) {
			return true;
		}
		double y2 = x * slope2 + intercept;
		return equals(y, y2);
	}

	public static boolean equals(double a, double b) {
		return Math.abs(a - b) <= 0.0000001;
	}

	public static boolean check(point d) {
		double result = area(a, b, d) + area(a, c, d) + area(b, c, d);
		return result <= area;
	}

	public static double area(point a, point b, point c) {
		int x1 = b.getX() - a.getX();
		int y1 = b.getY() - a.getY();
		int x2 = c.getX() - a.getX();
		int y2 = c.getY() - a.getY();
		int area = Math.abs(x1 * y2 - x2 * y1);
		return area * 0.5;
	}
}

class point {
	private int x;
	private int y;

	public point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String toString() {
		return x + " " + y;
	}
}
