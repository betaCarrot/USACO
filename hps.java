import java.util.*;
import java.io.*;
import java.lang.Math;

public class hps{
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("hps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		int N = Integer.parseInt(f.readLine());
		int result1 = 0;
		int result2 = 0;
		for(int i=0;i<N;i++){
			StringTokenizer st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			result1+=judge1(a,b);
			result2+=judge2(a,b);
		}
		out.println(Math.max(result1,result2));
		out.close();
	}

	public static int judge1(int a, int b){
		if(a==b) return 0;
		if(a==1&&b==3) return 1;
		if(a==3&&b==2) return 1;
		if(a==2&&b==1) return 1;
		return 0;
	}

	public static int judge2(int a, int b){
		if(a==b) return 0;
		if(a==1&&b==2) return 1;
		if(a==2&&b==3) return 1;
		if(a==3&&b==1) return 1;
		return 0;
	}
}