import java.util.*;
import java.io.*;
import java.lang.Math;

public class where{
	private static int N;
	private static int[][] map;
	private static boolean[][] visited = new boolean[20][20];

	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("where.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("where.out")));
		N = Integer.parseInt(f.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++){
			String line = f.readLine();
			for(int k=0;k<N;k++){
				map[i][k] = convertToInt(line.substring(k,k+1));
			}
		}
		ArrayList<PCL> results = new ArrayList<PCL>();
		for(int i1=0;i1<N;i1++){
			for(int j1=0;j1<N;j1++){
				for(int i2=0;i2<N;i2++){
					for(int j2=0;j2<N;j2++){
						if(checkPCL(i1,j1,i2,j2)) results.add(new PCL(i1,j1,i2,j2));
					}
				}	
			}
		}
		int result = 0;
		for(int i=0;i<results.size();i++){
			PCL checking = results.get(i);
			boolean isMaximal = true;
			for(int k=0;k<results.size();k++){
				if(i==k) continue;
				if(checking.isInOtherPCL(results.get(k))){
					isMaximal = false;
					break;
				}
			}
			if(isMaximal) result++;
		}
		out.println(result);
		out.close();
	}

	public static boolean checkPCL(int i1, int j1, int i2, int j2){
		int numColors = 0;
		int[] colorCount = new int[26];
		for(int i=i1;i<=i2;i++){
			for(int j=j1;j<=j2;j++){
				visited[i][j] = false;
			}
		}
		for(int i=i1;i<=i2;i++){
			for(int j=j1;j<=j2;j++){
				if(visited[i][j]) continue;
				int color = map[i][j];
				if(colorCount[color]==0) numColors++;
				colorCount[color]++;
				visit(i,j,color,i1,j1,i2,j2);
			}
		}
		if(numColors!=2) return false;
		boolean foundOne = false;
		boolean foundMany = false;
		for(int i=0;i<26;i++){
			if(colorCount[i]==1) foundOne = true;
			if(colorCount[i]>=2) foundMany = true;
		}
		return foundOne && foundMany;
	}

	public static void visit(int i, int j, int color, int i1, int j1, int i2, int j2){
		if(visited[i][j]) return;
		visited[i][j] = true;
		if(i>i1 && map[i-1][j]==color) visit(i-1,j,color,i1,j1,i2,j2);
		if(i<i2 && map[i+1][j]==color) visit(i+1,j,color,i1,j1,i2,j2);
		if(j>j1 && map[i][j-1]==color) visit(i,j-1,color,i1,j1,i2,j2);
		if(j<j2 && map[i][j+1]==color) visit(i,j+1,color,i1,j1,i2,j2);
	}

	public static int convertToInt(String s){
		return ((int) (s.charAt(0)))-65;
	}
}

class PCL{
	private int i1;
	private int j1;
	private int i2;
	private int j2;

	public PCL(int a, int b, int c, int d){
		i1=a;
		j1=b;
		i2=c;
		j2=d;
	}

	public boolean isInOtherPCL(PCL next){
		return next.i1<=i1 && next.i2>=i2 && next.j1<=j1 && next.j2>=j2;
	}
}
