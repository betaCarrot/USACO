/*
 ID: majesti2
 LANG: JAVA
 TASK: sort3
 */

import java.util.*;
import java.io.*;

public class sort3 {
	private static int[] array;
	private static int length;
	private static int numOnes=0;
	private static int numTwos=0;

	public static void main (String [] args) throws IOException {
		int count = 0;
		BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		length= Integer.parseInt(st.nextToken());
		array = new int[length];
		for(int i=0;i<length;i++){
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			int k = Integer.parseInt(st1.nextToken());
			if(k==1) numOnes++;
			if(k==2) numTwos++;
			array[i] = k;
		}
		count += numOnes-findOnesInPlace();
		sortOnes();
		count+= numTwos-findTwosInPlace();
		out.println(count);
		out.close();
	}

	private static int findOnesInPlace(){
		int result=0;
		for(int i=0;i<numOnes;i++){
			if(array[i]==1) result++;
		}
		return result;
	}

	private static void sortOnes(){
		for(int i=0;i<numOnes;i++){
			if(array[i]==2){
				exchange(i,findFirstOneIndex());
			}
		}
		for(int i=0;i<numOnes;i++){
			if(array[i]==3){
				exchange(i,findFirstOneIndex());
			}
		}
	}

	private static int findFirstOneIndex(){
		for(int i=numOnes;i<length;i++){
			if(array[i]==1) return i;
		}
		return -1;
	}

	private static int findTwosInPlace(){
		int result = 0;
		for(int i=numOnes;i<numOnes+numTwos;i++){
			if(array[i]==2) result++;
		}
		return result;
	}

	private static void exchange(int p, int q){
		int temp = array[p];
		array[p] = array[q];
		array[q] = temp;
	}
}


	
	
