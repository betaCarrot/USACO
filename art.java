import java.util.*;
import java.io.*;
import java.lang.Math;

public class art{
	private static int N;
	private static int[][] map;

	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("art.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("art.out")));
		N = Integer.parseInt(f.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++){
			String line = f.readLine();
			for(int j=0;j<N;j++){
				map[i][j] = Integer.parseInt(line.substring(j,j+1));
			}
		}
		int result = 0;
		for(int color=1;color<=9;color++){
			if(!colorAppears(color)) continue;
			boolean canBeFirst = true;
			for(int nextColor=1;nextColor<=9;nextColor++){
				if(!colorAppears(nextColor)) continue;
				if(nextColor==color) continue;
				if(isOnTop(color,nextColor)){
					canBeFirst = false;
					break;
				}
			}
			if(canBeFirst) result++;
		}
		out.println(result);
		out.close();
	}

	public static boolean colorAppears(int color){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(map[i][j]==color) return true;
			}
		}
		return false;
	}

	public static boolean isOnTop(int color,int nextColor){
		int minLeft = N-1;
		int maxRight = 0;
		int minTop = N-1;
		int maxBottom = 0;
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(map[i][j]==nextColor){
					minLeft = Math.min(minLeft,j);
					minTop = Math.min(minTop,i);
					maxRight = Math.max(maxRight,j);
					maxBottom = Math.max(maxBottom,i);
				}
			}
		}
		for(int i=minTop;i<=maxBottom;i++){
			for(int j=minLeft;j<=maxRight;j++){
				if(map[i][j]==color) return true;
			}
		}
		return false;
	}
}	