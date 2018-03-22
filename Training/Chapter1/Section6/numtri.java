/*
 ID: majesti2
 LANG: JAVA
 TASK: numtri
 */

import java.util.*;
import java.io.*;
import static java.lang.Math.max;

public class numtri {
	private static int[][] map;
	private static int N;
	
	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0;i<N;i++){
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			for(int k=0;k<=i;k++){
				map[i][k]=Integer.parseInt(st1.nextToken());
			}
		}
		for(int level=1;level<N;level++){
			map[level][0] += map[level-1][0];
			for(int index=1;index<=level;index++){
				map[level][index] += max(map[level-1][index-1],map[level-1][index]);
			}
		}
		int max=0;
		for(int i:map[N-1]){
			if(i>max) max = i;
		}
		out.println(max);
		out.close();
	}
}

	
	
