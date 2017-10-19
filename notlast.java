import java.util.*;
import java.io.*;
import java.lang.Math;

public class notlast{
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("notlast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("notlast.out")));
		int N = Integer.parseInt(f.readLine());
		int[] array = new int[7];
		int[] temp = new int[7];
		for(int i=0;i<N;i++){
			StringTokenizer st = new StringTokenizer(f.readLine());
			int index = convertNametoIndex(st.nextToken());
			int amount = Integer.parseInt(st.nextToken());
			array[index] += amount;
			temp[index] += amount;
		}
		Arrays.sort(temp);
		int minAmount = temp[0];
		int secondMinAmount = -1;
		for(int i=0;i<7;i++){
			if(temp[i]>minAmount) {secondMinAmount = temp[i]; break;} 
		}
		if(secondMinAmount==-1) out.println("Tie");
		else{
			int count = 0;
			int result = -1;
			for(int i=0;i<7;i++){
				if(array[i]==secondMinAmount){
					count++;
					result = i;
				}
			}
			if(count>1) out.println("Tie");
			else out.println(convertIndextoName(result));
		}
		out.close();
	}

	public static int convertNametoIndex(String s){
		if(s.equals("Bessie")) return 0;
		if(s.equals("Elsie")) return 1;
		if(s.equals("Daisy")) return 2;
		if(s.equals("Gertie")) return 3;
		if(s.equals("Annabelle")) return 4;
		if(s.equals("Maggie")) return 5;
		if(s.equals("Henrietta")) return 6;
		return -1;
	}

	public static String convertIndextoName(int n){
		if(n==0) return "Bessie";
		if(n==1) return "Elsie";
		if(n==2) return "Daisy";
		if(n==3) return "Gertie";
		if(n==4) return "Annabelle";
		if(n==5) return "Maggie";
		if(n==6) return "Henrietta";
		return null;
	}
}