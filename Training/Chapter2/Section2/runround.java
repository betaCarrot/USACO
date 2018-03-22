/*
 ID: majesti2
 LANG: JAVA
 TASK: runround
 */

import java.util.*;
import java.io.*;
import java.lang.Math;

public class runround {
	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());	
		for(int i=N+1;i<=10000000;i++){
			if(check(i)) {out.println(i);break;}
		}
		out.close();
	}

	public static boolean check(int n){
		String s = n + "";
		int length = s.length();
		if(s.substring(0,1).equals(length+"")) return false;
		int[] values = new int[length];
		for(int i=0;i<length;i++){
			values[i] = Integer.parseInt(s.substring(i,i+1));
		}
		int count = 0;
		boolean[] TF = new boolean[10];
		int nextIndex = 0;
		while(count<length){
			int value = values[nextIndex]+nextIndex;
			while(value>=length) value -= length;
			nextIndex = value;
			if(TF[values[nextIndex]]) return false;
			TF[values[nextIndex]] = true;
			count++;
		}
		return true;
	}				
}


	
	
