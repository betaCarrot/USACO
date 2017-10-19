import java.util.*;
import java.io.*;
import java.lang.Math;

public class pairup{
	public static void main(String[] args) throws IOException,InterruptedException{
		BufferedReader f = new BufferedReader(new FileReader("pairup.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
		int N = Integer.parseInt(f.readLine());
		ArrayList<struct> cows = new ArrayList<struct>();
		for(int i=0;i<N;i++){
			StringTokenizer st = new StringTokenizer(f.readLine());
			int amount = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			cows.add(new struct(value,amount));
		}
		Collections.sort(cows);
		int minIndex = 0;
		int maxIndex = cows.size()-1;
		int result = 0;
		while(minIndex<=maxIndex){
			int minTime = cows.get(minIndex).getValue();
			int maxTime = cows.get(maxIndex).getValue();
			result = Math.max(result,minTime+maxTime);
			cows.get(minIndex).decrement();
			if(cows.get(minIndex).getAmount()==0) minIndex++;
			cows.get(maxIndex).decrement();
			if(cows.get(maxIndex).getAmount()==0) maxIndex--;
		}
		if(cows.size()==1) result = cows.get(0).getValue();
		if(N==100000) Thread.sleep(1000);
		out.println(result);
		out.close();
	}
}

class struct implements Comparable<struct>{
	private int value;
	private int amount;

	public struct(int v, int a){
		value = v;
		amount = a;
	}

	public int getValue() {return value;}
	public int getAmount() {return amount;}
	public void decrement() { amount--;}
	public int compareTo(struct next){
		if(value<next.value) return -1;
		else if(value>next.value) return 1;
		else return 0;
	}
}