/*
 ID: majesti2
 LANG: JAVA
 TASK: hamming
 */

import java.util.*;
import java.io.*;
import java.lang.Math;

public class hamming {
	private static int N;
	private static int B;
	private static int D;
	
	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(0);
		int count=1;
		for(int i=1;i<Math.pow(2,B);i++){
			if(checkDistance(result,i)) {
				result.add(i);
				count++;
			}
			if(count==N) break;
		}
		for(int i=0;i<result.size()-1;i++){
			out.print(result.get(i));
			if((i%10)==9) out.println();
			else out.print(" ");
		}
		out.print(result.get(result.size()-1));
		out.print("\n");
		out.close();		
	}

	public static boolean checkDistance(ArrayList<Integer> list, int next){
		for(int k:list){
			if(!checkHammingDistance(k,next)) return false;
		}
		return true;
	}

	public static boolean checkHammingDistance(int a, int b){
		String s1 = Integer.toBinaryString(a);
		String s2 = Integer.toBinaryString(b);
		for(int i=s1.length();i<B;i++){
			s1 = "0"+s1;
		}
		for(int i=s2.length();i<B;i++){
			s2 = "0"+s2;
		}
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		int count = 0;
		for(int i=0;i<B;i++){
			if(c1[i]!=c2[i]) count++;
		}
		if(count>=D) return true;
		return false;
	}
				
}


	
	
