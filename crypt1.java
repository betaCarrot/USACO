/*
ID: majesti2
LANG: JAVA
TASK: crypt1
*/
import java.io.*;
import java.util.*;

public class crypt1{
  public static void main (String [] args) throws IOException {
	BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
	StringTokenizer st = new StringTokenizer(f.readLine());
	int length = Integer.parseInt(st.nextToken());
	int[] array = new int[length];
	StringTokenizer st1 = new StringTokenizer(f.readLine());
	for(int i=0;i<length;i++){
		array[i]=Integer.parseInt(st1.nextToken());
	}
	int answer = 0;	
	for(int a=0;a<length;a++){
		for(int b=0;b<length;b++){
			for(int c=0;c<length;c++){
				for(int d=0;d<length;d++){
					for(int e=0;e<length;e++){
						boolean result = true;
						int abc = array[a]*100+array[b]*10+array[c];
						int partial1 = abc*array[e];
						int partial2 = abc*array[d];
						int sum = partial2*10+partial1;
						if(partial1>=1000) result = false;
						if(partial2>=1000) result = false;
						if(sum>=10000) result = false;
						if(check(array,partial1)==false) result = false;
						if(check(array,partial2)==false) result = false;
						if(check(array,sum)==false) result = false;
						if(result==true) answer++;
					}
				}
			}
		}
	}
	out.println(answer);
	out.close();
	}

	public static boolean check(int[] array,int number){
 		while(number>10){
			int digit = number%10;
			number = number/10;
			if(checkDigit(array,digit)==false) return false;
		}
		return checkDigit(array,number);
	}

	public static boolean checkDigit(int[] array, int digit){
		for(int i=0;i<array.length;i++){
			if(array[i]==digit) return true;
		}
		return false;
	}
}
