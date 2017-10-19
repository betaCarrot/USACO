import java.util.*;
import java.io.*;
import java.lang.Math;

public class cowtip{
	private static boolean[][] map;

	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("cowtip.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtip.out")));
		int N = Integer.parseInt(f.readLine());
		map = new boolean[N][N];
		for(int i=0;i<N;i++){
			String line = f.readLine();
			for(int k=0;k<N;k++){
				map[i][k] = Integer.parseInt(line.substring(k,k+1))==0;
			}
		}
		int count = 0;
		for(int i=N-1;i>=0;i--){
			for(int k=N-1;k>=0;k--){
				if(!map[i][k]){
					flip(i,k);
					count++;
				}
			}
		}
		out.println(count);
		out.close();
	}

	public static void flip(int row, int col){
		for(int i=0;i<=row;i++){
			for(int k=0;k<=col;k++){
				map[i][k] = !map[i][k];
			}
		}
	}
}