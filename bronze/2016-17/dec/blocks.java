import java.util.*;
import java.io.*;
import java.lang.Math;

public class blocks{
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("blocks.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blocks.out")));
		int N = Integer.parseInt(f.readLine());
		String[][] map = new String[N][2];
		for(int i=0;i<N;i++){
			StringTokenizer st = new StringTokenizer(f.readLine());
			map[i][0] = st.nextToken();
			map[i][1] = st.nextToken();
		}
		int[] result = new int[26];
		for(int i=0;i<N;i++){
			int[] letters1 = new int[26];
			for(int k=0;k<map[i][0].length();k++){
				int ascii = (int) map[i][0].charAt(k);
				int index = ascii-97;
				letters1[index]++;
			}
			int[] letters2 = new int[26];
			for(int k=0;k<map[i][1].length();k++){
				int ascii = (int) map[i][1].charAt(k);
				int index = ascii-97;
				letters2[index]++;
			}
			for(int k=0;k<26;k++){
				result[k] += Math.max(letters1[k],letters2[k]);
			}
		}
		for(int i:result) out.println(i);
		out.close();
	}
}