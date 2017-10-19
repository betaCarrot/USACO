import java.util.*;
import java.io.*;
import java.lang.Math;

public class cownomics{
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int[][] spotted = new int[N][width];
		int[][] plain = new int[N][width];
		for(int i=0;i<N;i++){
			String line = f.readLine();
			for(int k=0;k<width;k++){
				String temp = line.substring(k,k+1);
				int number = -1;
				if(temp.equals("A")) number = 0;
				else if(temp.equals("C")) number = 1;
				else if(temp.equals("G")) number = 2;
				else if(temp.equals("T")) number = 3;
				spotted[i][k] = number;			
			}
		}
		for(int i=0;i<N;i++){
			String line = f.readLine();
			for(int k=0;k<width;k++){
				String temp = line.substring(k,k+1);
				int number = -1;
				if(temp.equals("A")) number = 0;
				else if(temp.equals("C")) number = 1;
				else if(temp.equals("G")) number = 2;
				else if(temp.equals("T")) number = 3;
				plain[i][k] = number;			
			}
		}
		int result = 0;
		for(int a=0;a<width;a++){
			for(int b=a+1;b<width;b++){
				for(int c=b+1;c<width;c++){
					boolean OK = true;
					boolean[] spottedSequences = new boolean[64];
					boolean[] plainSequences = new boolean[64];
					for(int k=0;k<N;k++){
						int index1 = spotted[k][a]*16+spotted[k][b]*4+spotted[k][c];
						spottedSequences[index1] = true;
						int index2 = plain[k][a]*16+plain[k][b]*4+plain[k][c];
						plainSequences[index2] = true;
					} 
					for(int i=0;i<64;i++){
						if(spottedSequences[i]&&plainSequences[i]) OK = false;
					}
					if(OK) {
						result++;
					}
				}
			}
		}
		out.println(result);
		out.close();
	}
}