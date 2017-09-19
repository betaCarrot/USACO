/*
 ID: majesti2
 LANG: JAVA
 TASK: cowtour
 */

import java.util.*;
import java.io.*;
import java.lang.Math;

public class cowtour{
	private static int[][] pastures;	
	private static boolean[][] matrix;
	private static int[] component;
	private static double[][] distances;
	private static double[] longestPath;
	private static int n;

	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		pastures = new int[n][2];
		matrix = new boolean[n][n];
		distances = new double[n][n];
		component = new int[n];
		longestPath = new double[n];
		for(int i=0;i<n;i++){
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			pastures[i][0] = Integer.parseInt(st1.nextToken());
			pastures[i][1] = Integer.parseInt(st1.nextToken());
		}
		for(int i=0;i<n;i++){
			String line = f.readLine();
			for(int k=0;k<n;k++){
				if(line.substring(k,k+1).equals("1")) {
					distances[i][k]=calculateDistance(pastures[i][0],pastures[i][1],pastures[k][0],pastures[k][1]); 
					matrix[i][k] = true;
				}
				else{
					if(k==i) distances[i][k] = 0.0;
					else distances[i][k] = (double)Integer.MAX_VALUE;
				}
			}
		}
		findComponents();
		fillPaths();
		double internalMax = 0.0;
		for(int i=0;i<n;i++) internalMax = Math.max(internalMax,longestPath[i]);
		double min = (double)Integer.MAX_VALUE;
		for(int i=0;i<n;i++){
			for(int k=0;k<n;k++){
				if(component[i]==component[k]) continue;
				min = Math.min(min,longestPath[i]+longestPath[k]+calculateDistance(pastures[i][0],pastures[i][1],pastures[k][0],pastures[k][1]));
			}
		}
		double result = Math.max(min,internalMax);
		out.println(roundNoSigFig(result,6));
		out.close();
	}

	public static String roundNoSigFig(double result, int n){
		double roundingExp = Math.pow(10,n);
		result = (double)Math.round(result * roundingExp) / roundingExp;
		String s = result+"";
		int index = -1;
		for(int i=0;i<s.length();i++){
			if(s.substring(i,i+1).equals(".")) index = i;
		}
		String check = s.substring(index+1);
		int toAdd = 0;
		if(check.length()<n) {
			toAdd = n-check.length();
			for(int i=0;i<toAdd;i++) s+="0";
		}
		return s; 
	}

	public static double calculateDistance(int row1, int col1, int row2, int col2){
		return Math.sqrt(Math.abs((row2-row1)*(row2-row1)+(col2-col1)*(col2-col1)));
	}

	public static void floodFill(int numComponent){
		while(true){
			int numVisited = 0;
			for(int i=0;i<n;i++){
				if(component[i]==-2){
					numVisited++;
					component[i] = numComponent;
					for(int k=0;k<n;k++){
						if((matrix[i][k])&&(component[k]==0)) component[k] = -2;
					}
				}
			}
			if(numVisited==0) break;
		}
	}

	public static void findComponents(){
		int numComponents = 0;
		for(int i=0;i<n;i++){
			if(component[i]==0){
				numComponents++;
				component[i]=-2;
				floodFill(numComponents);
			}
		}
	}

	private static void fillPaths(){
		for(int k=0;k<n;k++){
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if(distances[i][k]+distances[j][k] < distances[i][j]) distances[i][j] = distances[i][k] + distances[j][k];
				}
			}
		}
		for(int i=0;i<n;i++){
			double max = 0.0;
			for(int k=0;k<n;k++) if(distances[i][k]<100000) max = Math.max(max,distances[i][k]);
			longestPath[i] = max;
		}
	}
}