/*
 ID: majesti2
 LANG: JAVA
 TASK: ariprog
 */

import java.util.*;
import java.io.*;


public class ariprog {	
	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		StringTokenizer st1 = new StringTokenizer(f.readLine());
		int M = Integer.parseInt(st1.nextToken());
		boolean[] bisquares = new boolean[2*M*M+1];
		for(int i=0;i<bisquares.length;i++) bisquares[i]=false;
		for(int p=0;p<=M;p++){
			for(int q=p;q<=M;q++){
				bisquares[p*p+q*q]=true;
			}
		}
		int maxDiff = 2*M*M/(N-1)+1;
		ArrayList<pair> pairs = new ArrayList<pair>();
		for(int start=0;start<=2*M*M;start++){
			for(int diff=1;diff<=maxDiff;diff++){
				int length=1;
				int currentNumber = start;
				while((currentNumber<=2*M*M)&&(bisquares[currentNumber]==true)&&(length<=N)){
					currentNumber += diff;
					length++;
				}
				if(length==(N+1)){
					pairs.add(new pair(start,diff));
				}
			}
		}
		if(pairs.size()==0) out.println("NONE");
		else{
			pairs = removeDuplicates(pairs);
			pairs = quickSort(pairs,0,pairs.size()-1);		     
			for(pair p:pairs) out.println(p);}
		out.close();
	}

	public static ArrayList<pair> removeDuplicates(ArrayList<pair> pairs){
		for(int i=0;i<pairs.size();i++){
			int k=0;
			while(k<pairs.size()){
				if((k!=i)&&(pairs.get(k).compareTo(pairs.get(i))==0)) pairs.remove(k);
				else k++;
			}
		}
		return pairs;
	}

	public static ArrayList<pair> quickSort(ArrayList<pair> pairs,int lowerIndex, int higherIndex) {        
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        pair pivot = pairs.get(lowerIndex+(higherIndex-lowerIndex)/2);
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which 
             * is greater then the pivot value, and also we will identify a number 
             * from right side which is less then the pivot value. Once the search 
             * is done, then we exchange both numbers.
             */
            while (pairs.get(i).compareTo(pivot)<0) {
                i++;
            }
            while (pairs.get(j).compareTo(pivot)>0) {
                j--;
            }
            if (i <= j) {
               pair temp = pairs.get(i);
	       pairs.set(i,pairs.get(j));
	       pairs.set(j,temp);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(pairs,lowerIndex, j);
        if (i < higherIndex)
            quickSort(pairs,i, higherIndex);
	return pairs;
    }
}

class pair{
	private int start;
	private int difference;

	public pair(int s,int d){
		start = s;
		difference = d;
	}

	public int getStart() {return start;}
	public int getDifference() {return difference;}

	public int compareTo(pair next){
		if(difference<next.getDifference()) return -1;
		if(difference>next.getDifference()) return 1;
		if(start<next.getStart()) return -1;
		if(start>next.getStart()) return 1;
		return 0;
	}

	public String toString() { return start+ " "+difference;}
}
	
