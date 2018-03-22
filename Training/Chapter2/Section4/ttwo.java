/*
 ID: majesti2
 LANG: JAVA
 TASK: ttwo
 */

import java.util.*;
import java.io.*;
import java.lang.Math;

public class ttwo{
	private static boolean[][] obstacles = new boolean[10][10];
	private static int cowRow,cowCol,farmerRow,farmerCol,cowDirection,farmerDirection;

	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
		cowRow = 0;
		cowCol = 0;
		farmerRow = 0;
		farmerCol = 0;
		for(int i=0;i<10;i++){
			StringTokenizer st = new StringTokenizer(f.readLine());
			String str = st.nextToken();
			for(int k=0;k<10;k++){
				String s = str.substring(k,k+1);
				if(s.equals("*")) obstacles[i][k] = true;
				if(s.equals("C")){
					cowRow = i;
					cowCol = k;
				}
				if(s.equals("F")){
					farmerRow = i;
					farmerCol = k;
				}
			}
		}
		cowDirection = 1;
		farmerDirection = 1;
		int currentTime = 0;
		boolean canMeet = false;
		while(currentTime<10000){
			if((cowRow==farmerRow)&&(cowCol==farmerCol)){
				out.println(currentTime);
				canMeet = true;
				break;
			}
			moveCow();
			moveFarmer();
			currentTime++;
		}
		if(!canMeet) out.println(0);
		out.close();
	}

	public static void moveCow(){
		if(cowDirection==1){
			if(cowRow==0) cowDirection = 2;
			else if(obstacles[cowRow-1][cowCol]) cowDirection =2;
			else cowRow--;
		}
		else if(cowDirection==2){
			if(cowCol==9) cowDirection = 3;
			else if(obstacles[cowRow][cowCol+1]) cowDirection = 3;
			else cowCol++;
		}
		else if(cowDirection==3){
			if(cowRow==9) cowDirection = 4;
			else if(obstacles[cowRow+1][cowCol]) cowDirection = 4;
			else cowRow++;
		}
		else if(cowDirection==4){
			if(cowCol==0) cowDirection = 1;
			else if(obstacles[cowRow][cowCol-1]) cowDirection = 1;
			else cowCol--;
		}
	}

	public static void moveFarmer(){
		if(farmerDirection==1){
			if(farmerRow==0) farmerDirection = 2;
			else if(obstacles[farmerRow-1][farmerCol]) farmerDirection =2;
			else farmerRow--;
		}
		else if(farmerDirection==2){
			if(farmerCol==9) farmerDirection = 3;
			else if(obstacles[farmerRow][farmerCol+1]) farmerDirection = 3;
			else farmerCol++;
		}
		else if(farmerDirection==3){
			if(farmerRow==9) farmerDirection = 4;
			else if(obstacles[farmerRow+1][farmerCol]) farmerDirection = 4;
			else farmerRow++;
		}
		else if(farmerDirection==4){
			if(farmerCol==0) farmerDirection = 1;
			else if(obstacles[farmerRow][farmerCol-1]) farmerDirection = 1;
			else farmerCol--;
		}
	}
}