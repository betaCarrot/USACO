/*
 ID: majesti2
 LANG: JAVA
 TASK: concom
 */

import java.util.*;
import java.io.*;

public class concom{
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("concom.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[101][101];
		for(int i=0;i<N;i++){
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			int row = Integer.parseInt(st1.nextToken());
			int col = Integer.parseInt(st1.nextToken());
			map[row][col] = Integer.parseInt(st1.nextToken());
		}
		for(int i=1;i<=100;i++){
			int[] counts = new int[101];
			for(int k=1;k<=100;k++) counts[k] = map[i][k];
			boolean[] hasTraversed = new boolean[101];
			while(true){
				boolean finished = true;
				for(int k=1;k<=100;k++){
					if((!hasTraversed[k])&&(counts[k]>=50)){
						finished = false;
						hasTraversed[k] = true;
						for(int m=1;m<=100;m++){
							counts[m] += map[k][m];
						}
					}
				}
				if(finished) break;
			}
			for(int k=1;k<=100;k++){
				if((k!=i)&&(counts[k]>=50)){
					out.print(i+" "+k);
					out.println();
				}
			}
		}
		out.close();
	}
}