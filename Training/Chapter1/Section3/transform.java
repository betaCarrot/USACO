/*
ID: majesti2
LANG: JAVA
TASK: transform
*/
import java.io.*;
import java.util.*;

public class transform {
  public static void main (String [] args) throws IOException {
	BufferedReader f = new BufferedReader(new FileReader("transform.in"));
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
	StringTokenizer st = new StringTokenizer(f.readLine());
	int N = Integer.parseInt(st.nextToken());
	int[][] original = new int[N][N];
	int[][] transformed = new int[N][N];
	for(int i=0;i<N;i++){
		StringTokenizer st1 = new StringTokenizer(f.readLine());
		char[] chars = st1.nextToken().toCharArray();
		for(int k=0;k<N;k++){
			if(chars[k]=='@') original[i][k]=0;
			else original[i][k]=1;
		}
	}
	for(int i=0;i<N;i++){
	StringTokenizer st1 = new StringTokenizer(f.readLine());
	char[] chars = st1.nextToken().toCharArray();
	for(int k=0;k<N;k++){
		if(chars[k]=='@') transformed[i][k]=0;
		else transformed[i][k]=1;
		}
	}
	int result = 7;
	if(compare90(original,transformed)==true) result=1;
	else if(compare180(original,transformed)==true) result=2;
	else if(compare270(original,transformed)==true) result=3;
	else if(reflection(original,transformed)==true) result=4;
	else if(combination(original,transformed)==true) result=5;
	else if(noChange(original,transformed)==true) result=6;
	out.println(result);
	out.close();
	}

	public static boolean compare90(int[][]a,int[][]b){
		int N = a.length;
		for(int i=0;i<N;i++){
			for(int k=0;k<N;k++){
				if((a[i][k]==b[k][N-1-i])==false) return false;
			}
		}
		return true;
	}

	public static boolean compare180(int[][]a,int[][]b){
		int N = a.length;
		for(int i=0;i<N;i++){
			for(int k=0;k<N;k++){
				if((a[i][k]==b[N-1-i][N-1-k])==false) return false;
			}
		}
		return true;
	}

	public static boolean compare270(int[][]a,int[][]b){
		int N = a.length;
		for(int i=0;i<N;i++){
			for(int k=0;k<N;k++){
				if((a[i][k]==b[N-1-k][i])==false) return false;
			}
		}
		return true;
	}

	public static boolean reflection(int[][]a,int[][]b){
		int N = a.length;
		for(int i=0;i<N;i++){
			for(int k=0;k<N;k++){
				if((a[i][k]==b[i][N-1-k])==false) return false;
			}
		}
		return true;
	}

	public static boolean combination(int[][]a,int[][]b){
		int N = a.length;
		int[][] c = new int[N][N];
		for(int i=0;i<N;i++){
			for(int k=0;k<N;k++){
				c[i][k]=a[i][N-1-k];
			}
		}
		if(compare90(c,b)==true) return true;
		if(compare180(c,b)==true) return true;
		if(compare270(c,b)==true) return true;
		return false;
	}

	public static boolean noChange(int[][]a,int[][]b){
		int N = a.length;
		for(int i=0;i<N;i++){
			for(int k=0;k<N;k++){
				if((a[i][k]==b[i][k])==false) return false;
			}
		}
		return true;
	}
}