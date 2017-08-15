/*
ID: majesti2
LANG: JAVA
TASK: skidesign
*/
import java.io.*;
import java.util.*;

public class skidesign{
  public static void main (String [] args) throws IOException {
	BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
	StringTokenizer st = new StringTokenizer(f.readLine());
	int length = Integer.parseInt(st.nextToken());
	int[] hills = new int[length];
	for(int i=0;i<length;i++){
		StringTokenizer st1 = new StringTokenizer(f.readLine());
		hills[i] = Integer.parseInt(st1.nextToken());
	}
	out.println(calculateMinPrice(hills));
	out.close();
	}

	public static int calculateMinPrice(int[] hills){
		int low = findShortestHill(hills);
		int high = low +17;
		int minPrice = calculateMoney(low,high,hills);
		//System.out.println(low+" "+high+" "+minPrice);
		while(high<findTallestHill(hills)){
			int price = calculateMoney(low,high,hills);
			//System.out.println(low+" "+high+" "+price);
			if(price<minPrice) minPrice = price;
			low++;
			high++;
		}
		return minPrice;
	}

	
	public static int calculateMoney(int low, int high,int[] hills){
		int price = 0;
		for(int i:hills){
			if(i<low) price+= (low-i)*(low-i);
			if(i>high) price += (i-high)*(i-high);
		}
	return price;
	}

	public static int findTallestHill(int[] hills){
		int max = hills[0];
		for(int i=1;i<hills.length;i++){
			if(hills[i]>max){
				max = hills[i];
			}
		}
		return max;
	}

	public static int findShortestHill(int[] hills){
		int min = hills[0];
		for(int i=1;i<hills.length;i++){
			if(hills[i]<min){
				min = hills[i];
			}
		}
		return min;
	}
}
