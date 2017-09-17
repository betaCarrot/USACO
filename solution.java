import java.util.*;
import java.io.*;
import java.lang.Math;

public class solution{
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		String word = s.next();
		ArrayList<String> result = new ArrayList<String>();
		int lastIndex = 0;
		if(word.length()<3) System.out.println(word);
		else{
			for(int i=3;i<=word.length();i++){
				if(checkTypo(word.substring(Math.max(i-3,lastIndex),i))){
					result.add(word.substring(lastIndex,i-1));
					lastIndex = i-1;
				}
			}
		if(lastIndex!=word.length()) result.add(word.substring(lastIndex,word.length()));
		for(String str:result) System.out.print(str+" ");
		}
	}

	public static boolean checkTypo(String s){
		if(s.length()<3) return false;
		char[] chars = s.toCharArray();
		if((isConsonant(chars[0]))&&(isConsonant(chars[1]))&&(isConsonant(chars[2]))){
			if((chars[0]==chars[1])&&(chars[1]==chars[2])) return false;
			return true;
		}
		return false;
	}

	public static boolean isConsonant(char c){
		if(c=='a') return false;
		if(c=='e') return false;
		if(c=='i') return false;
		if(c=='o') return false;
		if(c=='u') return false;
		return true;
	}
}
