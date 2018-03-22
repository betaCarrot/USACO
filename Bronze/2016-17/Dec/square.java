import java.util.*;
import java.io.*;
import java.lang.Math;

public class square{
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("square.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("square.out")));
		int[] xArray = new int[4];
		int[] yArray = new int[4];
		StringTokenizer st = new StringTokenizer(f.readLine());
		xArray[0] = Integer.parseInt(st.nextToken());
		yArray[0] = Integer.parseInt(st.nextToken());
		xArray[1] = Integer.parseInt(st.nextToken());
		yArray[1] = Integer.parseInt(st.nextToken());
		StringTokenizer st1 = new StringTokenizer(f.readLine());
		xArray[2] = Integer.parseInt(st1.nextToken());
		yArray[2] = Integer.parseInt(st1.nextToken());
		xArray[3] = Integer.parseInt(st1.nextToken());
		yArray[3] = Integer.parseInt(st1.nextToken());
		Arrays.sort(xArray);
		Arrays.sort(yArray);
		int xLength = xArray[3]-xArray[0];
		int yLength = yArray[3]-yArray[0];
		int sideLength = Math.max(xLength,yLength);
		out.println(sideLength*sideLength);
		out.close();
	}
}