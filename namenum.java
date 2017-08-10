/*
ID: majesti2
LANG: JAVA
TASK: namenum
*/
import java.io.*;
import java.util.*;

public class namenum {
  public static void main (String [] args) throws IOException {
	BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
	StringTokenizer st = new StringTokenizer(f.readLine());
	long number = Long.parseLong(st.nextToken());
	String temp = Long.toString(number);
	ArrayList<String> acceptedNames = new ArrayList<String>();		
	BufferedReader r = new BufferedReader(new FileReader("dict.txt"));
	String line;
	while ((line = r.readLine()) != null) {
		StringTokenizer st1 = new StringTokenizer(line);
		acceptedNames.add(st1.nextToken());
	}
	boolean found = false;
	for(String s:acceptedNames) {
		long a = convertToNumber(s);
		if(a==number){
			out.println(s);
			found = true;
		}
	}
	if(!found) out.println("NONE");
	out.close();		
   }
	
	public static long convertToNumber(String s){
		long result = 0;
		char[] chars = s.toCharArray();
		for(int i=0; i<chars.length;i++){
			if((chars[i]=='A')||(chars[i]=='B')||(chars[i]=='C')) result += 2*Math.pow(10,chars.length-1-i);
			if((chars[i]=='D')||(chars[i]=='E')||(chars[i]=='F')) result += 3*Math.pow(10,chars.length-1-i);
			if((chars[i]=='G')||(chars[i]=='H')||(chars[i]=='I')) result += 4*Math.pow(10,chars.length-1-i);
			if((chars[i]=='J')||(chars[i]=='K')||(chars[i]=='L')) result += 5*Math.pow(10,chars.length-1-i);
			if((chars[i]=='M')||(chars[i]=='N')||(chars[i]=='O')) result += 6*Math.pow(10,chars.length-1-i);
			if((chars[i]=='P')||(chars[i]=='R')||(chars[i]=='S')) result += 7*Math.pow(10,chars.length-1-i);
			if((chars[i]=='T')||(chars[i]=='U')||(chars[i]=='V')) result += 8*Math.pow(10,chars.length-1-i);
			if((chars[i]=='W')||(chars[i]=='X')||(chars[i]=='Y')) result += 9*Math.pow(10,chars.length-1-i);
		}
		return result;
	}
}