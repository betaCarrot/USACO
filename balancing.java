import java.util.*;
import java.io.*;
import java.lang.Math;

public class balancing{
	private static int n;
	private static int b;
	private static int[] xs;
	private static int[] ys;

	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("balancing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		xs = new int[n];
		ys = new int[n];
		for(int i=0;i<n;i++){
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			xs[i] = Integer.parseInt(st1.nextToken());
			ys[i] = Integer.parseInt(st1.nextToken());
		}
		int result = Integer.MAX_VALUE;
		Set<Integer> validxs = new HashSet<Integer>();
		Set<Integer> validys = new HashSet<Integer>();
		for(int i=0;i<n;i++){
			validxs.add(xs[i]+1);
			validys.add(ys[i]+1);
		}
		for(int i:validxs){
			for(int k:validys){
				result = Math.min(result,calculateM(i,k));
			}
		}
		out.println(result);
		out.close();
	}


	public static int calculateM(int a, int b){
		int q1 = 0;
		int q2 = 0;
		int q3 = 0;
		int q4 = 0;
		for(int i=0;i<n;i++){
			int x = xs[i];
			int y = ys[i];
			if(x>a&&y>b) q1++;
			else if(x<a&&y>b) q2++;
			else if(x<a&&y<b) q3++;
			else q4++;
		}
		return max(q1,q2,q3,q4);
	}

	public static int max(int a, int b, int c, int d){
		int max = a;
		if(b>max) max = b;
		if(c>max) max = c;
		if(d>max) max = d;
		return max;
	}
}