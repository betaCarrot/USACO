/*
 ID: majesti2
 LANG: JAVA
 TASK: frac1
 */

import java.util.*;
import java.io.*;
import java.lang.Math;

public class frac1 {
	private static ArrayList<fraction> fractions = new ArrayList<fraction>();
	private static fraction[] numbers;

	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		ArrayList<fraction> fractions = new ArrayList<fraction>();
		fractions.add(new fraction(0,1));
		fractions.add(new fraction(1,1));
		for(int denominator=2;denominator<=N;denominator++){
			ArrayList<Integer> nominators = generateNonFactor(denominator);
			for(int nominator:nominators){
				fractions.add(new fraction(nominator,denominator));
			}
		}
		numbers = new fraction[fractions.size()];
		for(int i=0;i<fractions.size();i++) numbers[i] = fractions.get(i);
		quicksort(0,numbers.length-1);
		for(fraction fr: numbers) out.println(fr);
		out.close();
	}	

      private static void quicksort(int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        fraction pivot = numbers[low + (high-low)/2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (numbers[i].compareTo(pivot)<0) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (numbers[j].compareTo(pivot)>0) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                 fraction temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

	public static ArrayList<Integer> generateNonFactor(int n){
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(1);
		if(n==2) return result;
		if(n%2==0){
			for(int i=3;i<n;i+=2){
				if(gcd(n,i)==1) result.add(i);
			}
		}
		else{
			for(int i=2;i<n;i++){
				if(gcd(n,i)==1) result.add(i);
			}
		}
		return result;
	}

	public static int gcd(int a, int b){
  		while(a!=0 && b!=0) // until either one of them is 0
  		{
     			int c = b;
     			b = a%b;
     			a = c;
  		}
  		return a+b; // either one is 0, so return the non-zero value
	}
}

class fraction {
	private int nominator;
	private int denominator;
	
	public fraction(int n, int d){
		nominator = n;
		denominator = d;
	}

	public double value(){
		return (double)nominator/(double)denominator;
	}
	public int compareTo(fraction next){
		if(this.value()>next.value()) return 1;
		if(this.value()<next.value()) return -1;
		return 0;
	}

	public String toString(){
		return nominator+"/"+denominator;
	}
}


	
	
