/*
 ID: majesti2
 LANG: JAVA
 TASK: castle
 */

import java.util.*;
import java.io.*;
import java.lang.Math;

public class castle {
	private static room[][] rooms;
	private static int depth;
	private static int width;
	private static int maxSize = 0;
	private static int maxRow = 0;
	private static int maxCol = 0;
	private static String maxOrientation = "N";
	private static int[] roomSizes;

	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		width = Integer.parseInt(st.nextToken());
		depth = Integer.parseInt(st.nextToken());
		int[][] walls = new int[depth][width];
		rooms = new room[depth][width];
		for(int i=0;i<depth;i++){
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			for(int k=0;k<width;k++){
				walls[i][k] = Integer.parseInt(st1.nextToken());
			}
		}
		for(int i=0;i<depth;i++){
			for(int k=0;k<width;k++){
				rooms[i][k] = new room(i,k);
			}
		}
		for(int i=0;i<depth;i++){
			for(int k=0;k<width;k++){
				int wall = walls[i][k];
				if(wall==0){
					rooms[i][k].addNeighbor(rooms[i-1][k]);
					rooms[i][k].addNeighbor(rooms[i+1][k]);
					rooms[i][k].addNeighbor(rooms[i][k-1]);
					rooms[i][k].addNeighbor(rooms[i][k+1]);
				}
				else if(wall==1){
					rooms[i][k].addNeighbor(rooms[i-1][k]);
					rooms[i][k].addNeighbor(rooms[i+1][k]);
					rooms[i][k].addNeighbor(rooms[i][k+1]);
				}
				else if(wall==2){
					rooms[i][k].addNeighbor(rooms[i+1][k]);
					rooms[i][k].addNeighbor(rooms[i][k-1]);
					rooms[i][k].addNeighbor(rooms[i][k+1]);
				}
				else if(wall==3){
					rooms[i][k].addNeighbor(rooms[i+1][k]);
					rooms[i][k].addNeighbor(rooms[i][k+1]);
				}
				else if(wall==4){
					rooms[i][k].addNeighbor(rooms[i-1][k]);
					rooms[i][k].addNeighbor(rooms[i+1][k]);
					rooms[i][k].addNeighbor(rooms[i][k-1]);
				}
				else if(wall==5){
					rooms[i][k].addNeighbor(rooms[i-1][k]);
					rooms[i][k].addNeighbor(rooms[i+1][k]);
				}
				else if(wall==6){
					rooms[i][k].addNeighbor(rooms[i+1][k]);
					rooms[i][k].addNeighbor(rooms[i][k-1]);
				}
				else if(wall==7){
					rooms[i][k].addNeighbor(rooms[i+1][k]);
				}
				else if(wall==8){
					rooms[i][k].addNeighbor(rooms[i-1][k]);
					rooms[i][k].addNeighbor(rooms[i][k-1]);
					rooms[i][k].addNeighbor(rooms[i][k+1]);
				}
				else if(wall==9){
					rooms[i][k].addNeighbor(rooms[i-1][k]);
					rooms[i][k].addNeighbor(rooms[i][k+1]);
				}
				else if(wall==10){
					rooms[i][k].addNeighbor(rooms[i][k-1]);
					rooms[i][k].addNeighbor(rooms[i][k+1]);
				}
				else if(wall==11){
					rooms[i][k].addNeighbor(rooms[i][k+1]);
				}
				else if(wall==12){
					rooms[i][k].addNeighbor(rooms[i-1][k]);
					rooms[i][k].addNeighbor(rooms[i][k-1]);
				}
				else if(wall==13){
					rooms[i][k].addNeighbor(rooms[i-1][k]);
				}
				else if(wall==14){
					rooms[i][k].addNeighbor(rooms[i][k-1]);
				}
			}
		}
		roomSizes = findComponents();
		out.println(roomSizes.length);
		int MaxRoomSize = 0;
		for(int i:roomSizes){
			if(i>MaxRoomSize) MaxRoomSize = i;
		}
		out.println(MaxRoomSize);
		if(depth==1){
			for(int i=0;i<width-1;i++){
				compare(rooms[0][i],rooms[0][i+1],"E");
			}
		}
		else if(width==1){
			for(int i=depth-1;i>0;i--){
				compare(rooms[i][0],rooms[i-1][0],"N");
			}
		}
		else{
		for(int k=0;k<width;k++){
			for(int i=depth-1;i>0;i--){
				room thisRoom = rooms[i][k];
				if((i==0)&&(k==0)){
					compare(thisRoom, rooms[i][k+1],"E");
					compare(thisRoom, rooms[i+1][k],"S");
				}
				else if((i==0)&&(k==width-1)){
					compare(thisRoom,rooms[i][k-1],"W");
					compare(thisRoom,rooms[i+1][k],"S");
				}
				else if((i==depth-1)&&(k==0)){
					compare(thisRoom,rooms[i-1][k],"N");
					compare(thisRoom,rooms[i][k+1],"E");
				}
				else if((i==depth-1)&&(k==width-1)){
					compare(thisRoom,rooms[i-1][k],"N");
					compare(thisRoom,rooms[i][k-1],"W");
				}
				else if(i==0){
					compare(thisRoom, rooms[i][k+1],"E");
					compare(thisRoom,rooms[i][k-1],"W");
					compare(thisRoom,rooms[i+1][k],"S");
				}
				else if(i==depth-1){
					compare(thisRoom,rooms[i-1][k],"N");
					compare(thisRoom, rooms[i][k+1],"E");
					compare(thisRoom,rooms[i][k-1],"W");
				}
				else if(k==0){
					compare(thisRoom,rooms[i-1][k],"N");
					compare(thisRoom, rooms[i][k+1],"E");
					compare(thisRoom,rooms[i+1][k],"S");
				}
				else if(k==width-1){
					compare(thisRoom,rooms[i-1][k],"N");
					compare(thisRoom,rooms[i][k-1],"W");
					compare(thisRoom,rooms[i+1][k],"S");
				}
				else{
					compare(thisRoom,rooms[i][k+1],"E");
					compare(thisRoom,rooms[i-1][k],"N");
					compare(thisRoom,rooms[i][k-1],"W");
					compare(thisRoom,rooms[i+1][k],"S");
				}
			}
		}
	}	
		out.println(maxSize);
		out.println(maxRow+" "+maxCol+" "+maxOrientation);
		out.close();
	}
	public static void compare(room thisRoom, room nextRoom, String orientation){
		if(thisRoom.getComponent()==nextRoom.getComponent()) return;
		int size = roomSizes[thisRoom.getComponent()-1]+roomSizes[nextRoom.getComponent()-1];
		if(size>maxSize){
			maxSize = size;
			maxRow = thisRoom.getRow()+1;
			maxCol = thisRoom.getCol()+1;
			maxOrientation = orientation;
		}
	}
			
			
			

	public static int floodfill(int numComponent){
		int roomCount = 0;
		while(true){
			int numVisited = 0;
			for(int i=0;i<depth;i++){
				for(int k=0;k<width;k++){
					if(rooms[i][k].getComponent()==-2){
						numVisited++;
						roomCount++;
						rooms[i][k].setComponent(numComponent);
						ArrayList<room> neighbors = rooms[i][k].getNeighbors();
						for(int j=0;j<neighbors.size();j++){
							if(rooms[neighbors.get(j).getRow()][neighbors.get(j).getCol()].getComponent()==0) 
								rooms[neighbors.get(j).getRow()][neighbors.get(j).getCol()].setComponent(-2);
						}
					}
				}
			}
			if(numVisited==0) break;
		}
		return roomCount;
	}
							
	public static int[] findComponents(){
		int numComponent = 0;
		ArrayList<Integer> roomCounts = new ArrayList<Integer>();
		for(int i=0;i<depth;i++){
			for(int k=0;k<width;k++){
				if(rooms[i][k].getComponent()==0){
					numComponent++;
					rooms[i][k].setComponent(-2);
					int roomCount = floodfill(numComponent);
					roomCounts.add(roomCount);
				}
			}
		}
		int[] roomSizes = new int[roomCounts.size()];
		for(int i=0;i<roomCounts.size();i++) roomSizes[i] = roomCounts.get(i);
		return roomSizes;
	}
}

class room{
	private int row;
	private int col;
	private int component;
	private	ArrayList<room> neighbors;
	
	public room(int r, int c){
		row = r;
		col = c;
		component = 0;
		neighbors = new ArrayList<room>();
	}

	public int getRow() {return row;}
	public int getCol() {return col;}
	public int getComponent() {return component;}
	public void setComponent(int c) {component = c;}
	public void addNeighbor(room r) {neighbors.add(r);}
	public ArrayList<room> getNeighbors() {return neighbors;}
	public String toString() {return "row: "+row+" col: "+col+ " component: "+component;}
}


	
	
