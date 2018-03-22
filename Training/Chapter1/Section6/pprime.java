/*
 ID: majesti2
 LANG: JAVA
 TASK: pprime
 */

import java.util.*;
import java.io.*;

public class pprime {
	private static ArrayList<Integer> result = new ArrayList<Integer>();

	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int lowerBound = Integer.parseInt(st.nextToken());
		int upperBound = Integer.parseInt(st.nextToken());
		generatePalindrome(lowerBound,upperBound);
		for(int i:result) out.println(i);
		out.close();
	}


	public static void generatePalindrome(int lowerBound, int upperBound){
		if(lowerBound>=1000000) generate7DigitPalindrome(lowerBound,upperBound);
		else if(lowerBound>=10000){
			 generate5DigitPalindrome(lowerBound,upperBound);
			 if(upperBound>=1000000) generate7DigitPalindrome(lowerBound,upperBound);
		}
		else if(lowerBound>=100){
			generate3DigitPalindrome(lowerBound,upperBound);
			if(upperBound>=10000) generate5DigitPalindrome(lowerBound,upperBound);
			if(upperBound>=1000000) generate7DigitPalindrome(lowerBound,upperBound);
		}
		else if(lowerBound>=1){
			generate1DigitPalindrome(lowerBound,upperBound);
			if(upperBound>=11) result.add(11);
			if(upperBound>=100) generate3DigitPalindrome(lowerBound,upperBound);
			if(upperBound>=10000) generate5DigitPalindrome(lowerBound,upperBound);
			if(upperBound>=1000000) generate7DigitPalindrome(lowerBound,upperBound);
		}
	}

	public static void generate7DigitPalindrome(int lowerBound, int upperBound){
		for(int d1=1;d1<=9;d1+=2){
			for(int d2=0;d2<=9;d2++){
				for(int d3=0;d3<=9;d3++){
					for(int d4=0;d4<=9;d4++){
						int palindrome = 1*d1+10*d2+100*d3+1000*d4+10000*d3+100000*d2+1000000*d1;
						if((palindrome>=lowerBound)&&(palindrome<=upperBound)&&isPrime(palindrome)) result.add(palindrome);
					}
				}
			}
		}	
	}

	public static void generate5DigitPalindrome(int lowerBound, int upperBound){
		for(int d1=1;d1<=9;d1+=2){
			for(int d2=0;d2<=9;d2++){
				for(int d3=0;d3<=9;d3++){
						int palindrome = 1*d1+10*d2+100*d3+1000*d2+10000*d1;
						if((palindrome>=lowerBound)&&(palindrome<=upperBound)&&isPrime(palindrome)) result.add(palindrome);
				}
			}
		}	
	}

	public static void generate3DigitPalindrome(int lowerBound, int upperBound){
		for(int d1=1;d1<=9;d1+=2){
			for(int d2=0;d2<=9;d2++){
						int palindrome = 1*d1+10*d2+100*d1;
						if((palindrome>=lowerBound)&&(palindrome<=upperBound)&&isPrime(palindrome)) result.add(palindrome);
			}
		}	
	}

	
	public static void generate1DigitPalindrome(int lowerBound, int upperBound){
		for(int d1=1;d1<=9;d1+=2){
			int palindrome = d1;
			if((palindrome>=lowerBound)&&(palindrome<=upperBound)&&isPrime(palindrome)) result.add(palindrome);
		}	
	}

	public static boolean isPrime(int n) {
    		if (n%2==0) return false;
    		for(int i=3;i*i<=n;i+=2) {
        		if(n%i==0){
            		return false;}
    		}
    		return true;
	}
}

	
	
