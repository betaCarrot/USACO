import java.util.*;
import java.io.*;
import java.lang.Math;

public class cowsignal{
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("cowsignal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowsignal.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int depth = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int times = Integer.parseInt(st.nextToken());
		String[][] original = new String[depth+1][width+1];
		for(int i=1;i<=depth;i++){
			String line = f.readLine();
			for(int k=0;k<width;k++){
				original[i][k+1] = line.substring(k,k+1);
			}
		}
		String[][] result = new String[depth+1][width*times+1];
		for(int row=1;row<=depth;row++){
			int index = 1;
			while(index<=width){
				for(int i=((index-1)*times+1);i<=index*times;i++) result[row][i] = original[row][index];
				index++;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int row=1;row<=depth;row++){
			for(int i=0;i<times;i++){
				for(int k=1;k<=width*times;k++) sb.append(result[row][k]);
				sb.append("\n");
			}
		}
		out.print(sb.toString());
		out.close();
	}
}