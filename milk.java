/*
ID: majesti2
LANG: JAVA
TASK: milk
*/
import java.io.*;
import java.util.*;

public class milk{
  public static void main (String [] args) throws IOException {
	BufferedReader f = new BufferedReader(new FileReader("milk.in"));
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
	StringTokenizer st = new StringTokenizer(f.readLine());
	int AMOUNT = Integer.parseInt(st.nextToken());
	int size = Integer.parseInt(st.nextToken());
	int PRICE = 0;
	int[] prices = new int[size];
	int[] amounts = new int[size];
	for(int i=0;i<size;i++){
		StringTokenizer st1 = new StringTokenizer(f.readLine());
		prices[i] = Integer.parseInt(st1.nextToken());
		amounts[i] = Integer.parseInt(st1.nextToken());
	}
	while(AMOUNT>0){
		int i = findCheapestPriceIndex(prices);
		if(amounts[i]<AMOUNT){
			PRICE += prices[i]*amounts[i];
			prices[i] = Integer.MAX_VALUE;
			AMOUNT -= amounts[i];
		}
		else{
			PRICE+=prices[i]*AMOUNT;
			AMOUNT = 0;
		}
	}
	out.println(PRICE);
	out.close();		
	}
	public static int findCheapestPriceIndex(int[] a){
		int MIN = a[0];
		int minIndex = 0;
		for(int i=0;i<a.length;i++){
			if(a[i]<MIN){
				MIN = a[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
}
