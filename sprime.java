/*
 ID: majesti2
 LANG: JAVA
 TASK: sprime
 */

import java.util.*;
import java.io.*;
import java.lang.Math;

public class sprime {
	private static ArrayList<Integer> sprimes = new ArrayList<Integer>();
	private static ArrayList<Integer> result = new ArrayList<Integer>();

	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int length = Integer.parseInt(st.nextToken());
		generateSuperPrime(length);
		for(int i:result) out.println(i);
		out.close();
	}

	public static void generateSuperPrime(int length){
		int lower = (int)Math.pow(10,length-1);
		int upper = (int)Math.pow(10,length)-1;
		sprimes.add(2);
		sprimes.add(3);
		sprimes.add(5);
		sprimes.add(7);
		for(int i=0;i<length-1;i++){
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for(int m=0;m<sprimes.size();m++){
				for(int k=1;k<=9;k+=2){
					String s = ""+sprimes.get(m)+k;
					int n = Integer.parseInt(s);
					if(isPrime(n)) temp.add(n);
				}
			}
			for(int q:temp) sprimes.add(q);
		}
		for(int i:sprimes) if((i>lower)&&(i<upper)) result.add(i);
	}

	public static boolean isPrime(int n) {
		if(n==1) return false;
		if(n==2) return true;
    		if (n%2==0) return false;
    		for(int i=3;i*i<=n;i+=2) {
        		if(n%i==0){
            		return false;}
    		}
    		return true;
	}
}

	
	
