import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class reduce {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("reduce.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
		int N = Integer.parseInt(f.readLine());
		int[] xs = new int[N];
		int[] ys = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			xs[i] = Integer.parseInt(st.nextToken());
			ys[i] = Integer.parseInt(st.nextToken());
		}
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = 0;
		int maxY = 0;
		int minXIndex = -1;
		int minYIndex = -1;
		int maxXIndex = -1;
		int maxYIndex = -1;
		int minXCount = 0;
		int minYCount = 0;
		int maxXCount = 0;
		int maxYCount = 0;
		boolean minXUnique = true;
		boolean minYUnique = true;
		boolean maxXUnique = true;
		boolean maxYUnique = true;
		for (int i = 0; i < N; i++) {
			if (xs[i] < minX) {
				minX = xs[i];
				minXIndex = i;
			}
			if (xs[i] > maxX) {
				maxX = xs[i];
				maxXIndex = i;
			}
			if (ys[i] < minY) {
				minY = ys[i];
				minYIndex = i;
			}
			if (ys[i] > maxY) {
				maxY = ys[i];
				maxYIndex = i;
			}
		}
		for (int i = 0; i < N; i++) {
			if (xs[i] == minX)
				minXCount++;
			if (ys[i] == minY)
				minYCount++;
			if (xs[i] == maxX)
				maxXCount++;
			if (ys[i] == maxY)
				maxYCount++;
		}
		if (minXCount > 1)
			minXUnique = false;
		if (maxXCount > 1)
			maxXUnique = false;
		if (minYCount > 1)
			minYUnique = false;
		if (maxYCount > 1)
			maxYUnique = false;
		int result = (maxX - minX) * (maxY - minY);
		if (minXUnique) {
			ArrayList<Integer> removedXs = new ArrayList<Integer>();
			ArrayList<Integer> removedYs = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				removedXs.add(xs[i]);
				removedYs.add(ys[i]);
			}
			removedXs.remove(minXIndex);
			removedYs.remove(minXIndex);
			int area = calculateArea(removedXs, removedYs);
			result = Math.min(result, area);
		}
		if (maxXUnique) {
			ArrayList<Integer> removedXs = new ArrayList<Integer>();
			ArrayList<Integer> removedYs = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				removedXs.add(xs[i]);
				removedYs.add(ys[i]);
			}
			removedXs.remove(maxXIndex);
			removedYs.remove(maxXIndex);
			int area = calculateArea(removedXs, removedYs);
			result = Math.min(result, area);
		}
		if (minYUnique) {
			ArrayList<Integer> removedXs = new ArrayList<Integer>();
			ArrayList<Integer> removedYs = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				removedXs.add(xs[i]);
				removedYs.add(ys[i]);
			}
			removedXs.remove(minYIndex);
			removedYs.remove(minYIndex);
			int area = calculateArea(removedXs, removedYs);
			result = Math.min(result, area);
		}
		if (maxYUnique) {
			ArrayList<Integer> removedXs = new ArrayList<Integer>();
			ArrayList<Integer> removedYs = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				removedXs.add(xs[i]);
				removedYs.add(ys[i]);
			}
			removedXs.remove(maxYIndex);
			removedYs.remove(maxYIndex);
			int area = calculateArea(removedXs, removedYs);
			result = Math.min(result, area);
		}
		out.print(result);
		out.close();
	}

	public static int calculateArea(ArrayList<Integer> removedXs, ArrayList<Integer> removedYs) {
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = 0;
		int maxY = 0;
		for (int i = 0; i < removedXs.size(); i++) {
			minX = Math.min(minX, removedXs.get(i));
			maxX = Math.max(maxX, removedXs.get(i));
			minY = Math.min(minY, removedYs.get(i));
			maxY = Math.max(maxY, removedYs.get(i));
		}
		return (maxX - minX) * (maxY - minY);
	}
}