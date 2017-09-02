/*
 ID: majesti2
 LANG: JAVA
 TASK: subset
 */

import java.util.*;
import java.io.*;
import java.lang.Math;

public class subset {
	private static long[] possibleSums;
	private static int target;
	
	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int sum = (1+N)*N/2;
		possibleSums = new long[sum+1];
		if(sum%2!=0){
			out.println(0);
		}
		else{
			target = sum/2;
			possibleSums[0]++;
			possibleSums[1]++;
			for(int i=2;i<=N;i++){
				int currentSize=0;
				while(possibleSums[currentSize]!=0) currentSize++;
				long[] temp = new long[sum+1];
				for(int m=0;m<sum;m++) temp[m] = possibleSums[m];
				for(int k=0;k<currentSize;k++){
					possibleSums[k+i]+=temp[k];
				}
			}
			out.println(possibleSums[target]/2);
		}
		out.close();
	}			
}


	
	
