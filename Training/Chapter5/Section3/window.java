
/*
ID: majesti2
LANG: JAVA
TASK: window
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class window {
	public static HashMap<Character, Integer> map1 = new HashMap<Character, Integer>();
	public static HashMap<Character, Rectangle> map2 = new HashMap<Character, Rectangle>();
	public static ArrayList<Character> IDS = new ArrayList<Character>();
	public static int top = 10000;
	public static int bottom = 10001;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("window.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("window.out")));
		String line;
		StringTokenizer st;
		for (char c = 'a'; c <= 'z'; c++) {
			IDS.add(c);
		}
		for (char c = 'A'; c <= 'Z'; c++) {
			IDS.add(c);
		}
		for (char c = '0'; c <= '9'; c++) {
			IDS.add(c);
		}
		for (char c : IDS) {
			map1.put(c, -1);
		}
		StringBuilder sb = new StringBuilder();
		while ((line = f.readLine()) != null) {
			if (line.startsWith("w")) {
				line = line.substring(2, line.length() - 1);
				st = new StringTokenizer(line);
				char c = st.nextToken(",").charAt(0);
				int x1 = Integer.parseInt(st.nextToken(","));
				int y1 = Integer.parseInt(st.nextToken(","));
				int x2 = Integer.parseInt(st.nextToken(","));
				int y2 = Integer.parseInt(st.nextToken(","));
				Rectangle r = new Rectangle(Math.min(x1, x2), Math.max(x1, x2), Math.min(y1, y2), Math.max(y1, y2));
				map1.put(c, top);
				top--;
				map2.put(c, r);
			} else if (line.startsWith("t")) {
				line = line.substring(2, line.length() - 1);
				char c = line.charAt(0);
				map1.put(c, top);
				top--;
			} else if (line.startsWith("b")) {
				line = line.substring(2, line.length() - 1);
				char c = line.charAt(0);
				map1.put(c, bottom);
				bottom++;
			} else if (line.startsWith("d")) {
				line = line.substring(2, line.length() - 1);
				char c = line.charAt(0);
				map1.put(c, -1);
			} else {
				line = line.substring(2, line.length() - 1);
				char c = line.charAt(0);
				DecimalFormat df = new DecimalFormat("#.000");
				double res = calc(c) * 100;
				if (res < 1)
					sb.append("0");
				sb.append(df.format(calc(c) * 100)).append("\n");
			}
		}
		out.print(sb.toString());
		out.close();
	}

	public static double calc(char target) {
		Rectangle check = map2.get(target);
		int area = area(check);
		int covered = 0;
		ArrayList<Rectangle> covering = new ArrayList<Rectangle>();
		for (char c : IDS) {
			if (map1.get(c) != -1 && map1.get(c) < map1.get(target) && overlap(map2.get(c), map2.get(target))) {
				covering.add(map2.get(c));
			}
		}
		ArrayList<Rectangle> trimmed = new ArrayList<Rectangle>();
		for (int i = 0; i < covering.size(); i++) {
			Rectangle r = covering.get(i);
			Rectangle temp = new Rectangle(r.x1, r.x2, r.y1, r.y2);
			temp.x1 = Math.max(temp.x1, check.x1);
			temp.x2 = Math.min(temp.x2, check.x2);
			temp.y1 = Math.max(temp.y1, check.y1);
			temp.y2 = Math.min(temp.y2, check.y2);
			trimmed.add(temp);
		}
		ArrayList<Rectangle> splitted = solve(trimmed);
		for (Rectangle r : splitted) {
			covered += area(r);
		}
		int visible = area - covered;
		return (visible + 0.0) / area;
	}

	public static ArrayList<Rectangle> solve(ArrayList<Rectangle> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (overlap(list.get(i), list.get(j))) {
					ArrayList<Rectangle> next = new ArrayList<Rectangle>();
					for (int k = 0; k < list.size(); k++) {
						if (k != i && k != j) {
							next.add(list.get(k));
						}
					}
					next.addAll(split(list.get(i), list.get(j)));
					return solve(next);
				}
			}
		}
		return list;
	}

	public static boolean overlap(Rectangle r1, Rectangle r2) {
		Rectangle[] array = new Rectangle[] { r1, r2 };
		Arrays.sort(array);
		r1 = array[0];
		r2 = array[1];
		if (r2.x1 >= r1.x2) {
			return false;
		}
		if (r2.y2 <= r1.y1) {
			return false;
		}
		if (r2.y1 >= r1.y2) {
			return false;
		}
		return true;
	}

	public static ArrayList<Rectangle> split(Rectangle r1, Rectangle r2) {
		Rectangle[] array = new Rectangle[] { r1, r2 };
		ArrayList<Rectangle> list = new ArrayList<Rectangle>();
		Arrays.sort(array);
		r1 = array[0];
		r2 = array[1];
		if (r2.x2 <= r1.x2) {
			if (r2.y1 <= r1.y1) {
				if (r2.y2 <= r1.y2) {
					list.add(r1);
					list.add(new Rectangle(r2.x1, r2.x2, r2.y1, r1.y1));
				} else {
					list.add(r1);
					list.add(new Rectangle(r2.x1, r2.x2, r2.y1, r1.y1));
					list.add(new Rectangle(r2.x1, r2.x2, r1.y2, r2.y2));
				}
			} else {
				if (r2.y2 <= r1.y2) {
					list.add(r1);
				} else {
					list.add(r1);
					list.add(new Rectangle(r2.x1, r2.x2, r1.y2, r2.y2));
				}
			}
		} else {
			if (r2.y1 <= r1.y1) {
				if (r2.y2 <= r1.y2) {
					list.add(r1);
					list.add(new Rectangle(r2.x1, r2.x2, r2.y1, r1.y1));
					list.add(new Rectangle(r1.x2, r2.x2, r1.y1, r2.y2));
				} else {
					list.add(r2);
					list.add(new Rectangle(r1.x1, r2.x1, r1.y1, r1.y2));
				}
			} else {
				if (r2.y2 <= r1.y2) {
					list.add(r1);
					list.add(new Rectangle(r1.x2, r2.x2, r2.y1, r2.y2));
				} else {
					list.add(r1);
					list.add(new Rectangle(r1.x2, r2.x2, r2.y1, r1.y2));
					list.add(new Rectangle(r2.x1, r2.x2, r1.y2, r2.y2));
				}
			}
		}
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		for (Rectangle r : list) {
			if (area(r) != 0) {
				result.add(r);
			}
		}
		return result;
	}

	public static int area(Rectangle r) {
		return (r.x1 - r.x2) * (r.y1 - r.y2);
	}
}

class Rectangle implements Comparable<Rectangle> {
	public int x1, x2, y1, y2;

	public Rectangle(int a, int b, int c, int d) {
		x1 = a;
		x2 = b;
		y1 = c;
		y2 = d;
	}

	public int compareTo(Rectangle next) {
		return x1 - next.x1;
	}
}
