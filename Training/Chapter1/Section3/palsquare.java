/*
ID: majesti2
LANG: JAVA
TASK: palsquare
*/
import java.io.*;
import java.util.*;

public class palsquare {
  public static void main (String [] args) throws IOException {
	BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
	StringTokenizer st = new StringTokenizer(f.readLine());
	int base = Integer.parseInt(st.nextToken());
	int[] numbers = new int[300];
	long[] squared = new long[300];
	ArrayList<String> answers = new ArrayList<String>();
	ArrayList<String> answersSquared = new ArrayList<String>();
	for(int i=0;i<300;i++) numbers[i]=i+1;
	for(int i=0;i<300;i++) squared[i]=(long)numbers[i]*numbers[i];
	String[] modified = new String[300];
	for(int i=0;i<300;i++) modified[i] = Converter(squared[i],base);
	for(int i=0;i<300;i++){
		if(checkPalindrome(modified[i])==true){
			answers.add(Converter(numbers[i],base));
			answersSquared.add(modified[i]);
		}
	}
	for(int i=0;i<answers.size();i++){
		out.println(answers.get(i).toUpperCase()+" "+answersSquared.get(i).toUpperCase());
	}
	out.close();
   }
	
	public static String Converter(long number, int base)
{
	return Long.toString(Long.parseLong((Long.toString(number)), 10), base);           
}
	public static boolean checkPalindrome(String s){
		char[] chars = s.toCharArray();
		for(int i=0;i<chars.length;i++){
			if(chars[i]!=chars[chars.length-1-i]) return false;
		}
	return true;
	}
}
