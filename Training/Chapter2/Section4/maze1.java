/*
 ID: majesti2
 LANG: JAVA
 TASK: maze1
 */

import java.util.*;
import java.io.*;
import java.lang.Math;

public class maze1{
	private static boolean[][] hWalls;
	private static boolean[][] vWalls;
	private static int nRows,nCols;
	private static int exitRow1, exitRow2, exitCol1, exitCol2;

	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		nCols = Integer.parseInt(st.nextToken());
		nRows = Integer.parseInt(st.nextToken());
		int inputDepth = 2*nRows+1;
		int inputWidth = 2*nCols+1;
		hWalls = new boolean[nRows+1][nCols];
		vWalls = new boolean[nRows][nCols+1];
		for(int i=0;i<inputDepth;i++){
			String line = f.readLine();
			if(i%2==0){
				for(int k=0;k<inputWidth;k++){
					if(k%2==0) continue;
					if(line.substring(k,k+1).equals("-")) hWalls[i/2][k/2] = true;
				}
			}
			else{
				for(int k=0;k<inputWidth;k++){
					if(k%2!=0) continue;
					if(line.substring(k,k+1).equals("|")) vWalls[i/2][k/2] = true;
				}
			}
		}
		boolean found1Exit = false;
		for(int i=0;i<vWalls.length;i++){
			if(!vWalls[i][0]){
				if(!found1Exit) {exitRow1 = i; exitCol1 = 0; found1Exit = true;}
				else {exitRow2 = i; exitCol2 = 0;}
			}
		}
		for(int i=0;i<vWalls.length;i++){
			if(!vWalls[i][vWalls[0].length-1]){
				if(!found1Exit) {exitRow1 = i; exitCol1 = vWalls[0].length-2; found1Exit = true;}
				else {exitRow2 = i; exitCol2 = vWalls[0].length-2;}
			}
		}
		for(int i=0;i<hWalls[0].length;i++){
			if(!hWalls[0][i]){
				if(!found1Exit) {exitRow1 = 0; exitCol1 = i; found1Exit = true;}
				else {exitRow2 = 0; exitCol2 = i;}
			}
		}
		for(int i=0;i<hWalls[0].length;i++){
			if(!hWalls[hWalls.length-1][i]){
				if(!found1Exit) {exitRow1 = hWalls.length-2; exitCol1 = i; found1Exit = true;}
				else {exitRow2 = hWalls.length-2; exitCol2 = i;}
			}
		}
		int result = search(exitRow1, exitCol1, exitRow2, exitCol2);
		out.println(result+1);
		out.close();
	}

	public static int search(int exitRow1, int exitCol1, int exitRow2, int exitCol2){
		int[][] distances1 = shortestPath(exitRow1, exitCol1);
		int[][] distances2 = shortestPath(exitRow2, exitCol2);
		int result = 0;
		for(int i=0;i<distances1.length;i++){
			for(int k=0;k<distances1[0].length;k++){
				int check = Math.min(distances1[i][k],distances2[i][k]);
				result = Math.max(result,check);
			}
		}
		return result;
	}
	public static int[][] shortestPath(int row, int col){
		int result = 0;
		int nodesVisited = 0;
		int graphSize = nRows*nCols;
		int[][] distances = new int[nRows][nCols];
		for(int i=0;i<nRows;i++){
			for(int k=0;k<nCols;k++){
				distances[i][k] = Integer.MAX_VALUE;	
			}
		}
		boolean[][] visited = new boolean[nRows][nCols];
		distances[row][col] = 0;
		while(nodesVisited<graphSize){
			int minDistance = Integer.MAX_VALUE;
			int minRow = -1;
			int minCol = -1;
			for(int i=0;i<nRows;i++){
				for(int k=0;k<nCols;k++){
					if(distances[i][k]<minDistance){
						if(visited[i][k]) continue;
						minDistance = distances[i][k];
						minRow = i;
						minCol = k;
					}
				}
			}
			if(minRow==-1) break;
			visited[minRow][minCol] = true;
			nodesVisited++;
			if((minRow!=0)&&(!hWalls[minRow][minCol])) distances[minRow-1][minCol] = Math.min(distances[minRow-1][minCol],distances[minRow][minCol]+1);
			if((minRow!=nRows-1)&&(!hWalls[minRow+1][minCol])) distances[minRow+1][minCol] = Math.min(distances[minRow+1][minCol],distances[minRow][minCol]+1); 
			if((minCol!=0)&&(!vWalls[minRow][minCol])) distances[minRow][minCol-1] = Math.min(distances[minRow][minCol-1],distances[minRow][minCol]+1); 
			if((minCol!=nCols-1)&&(!vWalls[minRow][minCol+1])) distances[minRow][minCol+1] = Math.min(distances[minRow][minCol+1],distances[minRow][minCol]+1);
		}
		return distances;
	}
}