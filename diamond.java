import java.util.*;
import java.io.*;
import java.lang.Math;

public class diamond{
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("diamond.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] array = new int[N];
		int result = 0;
		for(int i=0;i<N;i++){
			array[i] = Integer.parseInt(f.readLine());
		}
		Arrays.sort(array);
		for(int lowerBound=1;lowerBound<10000;lowerBound++){
			int upperBound = lowerBound+K;
			int i=0;
			while(i<N){
				if(array[i]>=lowerBound) break;
				i++;
			}
			int k=i;
			while(k<N){
				if(array[k]>upperBound) break;
				k++;
			}
			k--;
			result = Math.max(result,k-i+1);
		}
		out.println(result);
		out.close();
	}
}