/*
 ID: majesti2
 LANG: JAVA
 TASK: preface
 */

import java.util.*;
import java.io.*;
import java.lang.Math;

public class preface {
	private static int[] array;
	
	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("preface.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		array = new int[7];
		for(int i=1;i<=N;i++) add(i);
		for(int i=0;i<7;i++){
			if(i==0) out.println("I "+array[0]);
			else if((i==1)&&array[1]!=0) out.println("V "+array[1]);
			else if((i==2)&&array[2]!=0) out.println("X "+array[2]);
			else if((i==3)&&array[3]!=0) out.println("L "+array[3]);
			else if((i==4)&&array[4]!=0) out.println("C "+array[4]);
			else if((i==5)&&array[5]!=0) out.println("D "+array[5]);
			else if((i==6)&&array[6]!=0) out.println("M "+array[6]);
		}
		out.close();
	}

	public static void add(int i){
		if(i>=1000){
			int temp = i/1000;
			array[6] += temp;
			add(i-temp*1000);
		}
		else if(i>=900){
			array[6]++;
			array[4]++;
			add(i-900);
		}
		else if(i>=500){
			array[5]++;
			add(i-500);
		}
		else if(i>=400){
			array[5]++;
			array[4]++;
			add(i-400);
		}
		else if(i>=100){
			int temp = i/100;
			array[4] += temp;
			add(i-temp*100);
		}
		else if(i>=90){
			array[4]++;
			array[2]++;
			add(i-90);
		}
		else if(i>=50){
			array[3]++;
			add(i-50);
		}
		else if(i>=40){
			array[3]++;
			array[2]++;
			add(i-40);
		}
		else if(i>=10){
			int temp = i/10;
			array[2] += temp;
			add(i-temp*10);
		}
		else if(i==9){
			array[2]++;
			array[0]++;
		}
		else if(i>=5){
			array[1]++;
			add(i-5);
		}
		else if(i==4){
			array[1]++;
			array[0]++;
		}
		else if(i>=1){
			array[0]+=i;
		}
		else return;
	}				
}


	
	
