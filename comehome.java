/*
 ID: majesti2
 LANG: JAVA
 TASK: comehome
*/

import java.util.*;
import java.io.*;
import java.lang.Math;

public class comehome{
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
		int numRoads = Integer.parseInt(f.readLine());
		int[][] distances = new int[52][52];
		for(int i=0;i<52;i++){
			for(int k=0;k<52;k++){
				distances[i][k] = 1000000000;
			}
		}
		for(int i=0;i<numRoads;i++){
			StringTokenizer st = new StringTokenizer(f.readLine());
			char c1 = st.nextToken().charAt(0);
			int ascii1 = (int) c1;
			int index1 = -1;
			if(ascii1>=97) index1 = ascii1-97;
			else index1 = ascii1-65+26;
			char c2 = st.nextToken().charAt(0);
			int ascii2 = (int) c2;
			int index2 = -1;
			if(ascii2>=97) index2 = ascii2-97;
			else index2 = ascii2-65+26;
			int distance = Integer.parseInt(st.nextToken());
			distances[index1][index2] = Math.min(distances[index1][index2],distance);
			distances[index2][index1] = Math.min(distances[index2][index1],distance);
		}
		for(int i=0;i<52;i++){
			for(int k=0;k<52;k++){
				if(i==k) distances[i][k] = 0;
				else if(distances[i][k]==0) distances[i][k] = 1000000000;
			}
		}
		int[] Zdistance = new int[52];
		for(int i=0;i<52;i++) Zdistance[i] = distances[51][i];
		int nodeVisited = 0;
		boolean[] visited = new boolean[52];
		while(nodeVisited<52){
			int min = 1000000000;
			int minIndex = -1;
			for(int i=0;i<52;i++){
				if(visited[i]) continue;
				if(Zdistance[i]<min) {min = Zdistance[i]; minIndex = i;}
			}
			if(minIndex==-1) break;
			visited[minIndex] = true;
			nodeVisited++;
			for(int i=0;i<52;i++){
				if(Zdistance[minIndex]+distances[minIndex][i]<Zdistance[i]) Zdistance[i] = Zdistance[minIndex]+distances[minIndex][i];
			}
		}
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for(int i=26;i<51;i++){
			if(Zdistance[i]<min){
				min = Zdistance[i];
				minIndex = i;
			}
		}
		char result = (char) (minIndex-26+65);
		out.println(result+" "+min);
		out.close();
	}
}