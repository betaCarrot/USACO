/*
ID: majesti2
LANG: JAVA
TASK: barn1
*/
import java.io.*;
import java.util.*;

public class barn1{
  public static void main (String [] args) throws IOException {
	BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
	StringTokenizer st = new StringTokenizer(f.readLine());
	int numBoards = Integer.parseInt(st.nextToken());
	int numStalls = Integer.parseInt(st.nextToken());
	int length = Integer.parseInt(st.nextToken());
	int[] stall = new int[length];
if(numBoards<length){
	for(int i=0;i<length;i++){
		StringTokenizer st1 = new StringTokenizer(f.readLine());
		stall[i] = Integer.parseInt(st1.nextToken());
		}
	int[] stalls = quickSort(stall,0,stall.length-1);
	ArrayList<Board> boards = new ArrayList<Board>();
	boards.add(new Board(stalls[0],stalls[length-1]));
	int N=1;
	while(N<numBoards){
		//System.out.println();
		//System.out.println("boards: "+N);
		//for(Board b:boards) System.out.println(b);
		int[] ISE = findLargestGapInArrayList(stalls,boards);
		int I = ISE[0];
		//System.out.println("I: "+I);
		int newBoard1Start = boards.get(I).getStart();
		int newBoard1End = ISE[1];
		int newBoard2Start = ISE[2];
		int newBoard2End = boards.get(I).getEnd();
		boards.remove(I);
		boards.add(new Board(newBoard1Start,newBoard1End));
		boards.add(new Board(newBoard2Start,newBoard2End));
		//for(Board b:boards) System.out.println(b);
		N++;
	}
	//System.out.println("final: ");
	//for(Board b:boards) System.out.println(b);
	int result=0;
	for(Board b:boards) result += b.getLength();
	//System.out.println(result);
	out.println(result);
}
else out.println(length);
	out.close();	
	}

	public static int[] findLargestGap(int[] stalls, Board b){
		//System.out.println(b);
		int largestGapStart = 0;
		int largestGapEnd = 0;
		int largestGapLength = 0;
		int i=0;
		while(i<stalls.length){
			if(stalls[i]==b.getStart()){
				if(stalls[i]==b.getEnd()){int[] SEL = new int[3]; SEL[0]=b.getStart();SEL[1]=b.getEnd();SEL[2]=0;return SEL;}
				//System.out.println("Index: "+stalls[i]);
				largestGapStart = stalls[i];
				largestGapEnd = stalls[i+1];
				largestGapLength = stalls[i+1]-stalls[i];
				break;
			}
			i++;
		}
		int[] SEL = new int[3];
		i++;
		//System.out.println(i);
		while((i<stalls.length)&&(stalls[i]<=b.getEnd())){
			int gap = stalls[i]-stalls[i-1];
			if(gap>largestGapLength){
				largestGapLength = gap;
				largestGapStart = stalls[i-1];
				largestGapEnd = stalls[i];
			}
			i++;
		}
		SEL[0] = largestGapStart;
		SEL[1] = largestGapEnd;
		SEL[2] = largestGapLength;
		return SEL;
	}

	public static int[] findLargestGapInArrayList(int[] stalls, ArrayList<Board> boards){
		int maxIndex = 0;
		//System.out.println(boards.get(1));
		int[] initialState = findLargestGap(stalls,boards.get(0));
		//for(int i:initialState) System.out.print(i+ " ");
		int maxStart = initialState[0];
		int maxEnd = initialState[1];
		int maxGap = initialState[2];
		for(int i=0;i<boards.size();i++){
			int[] gap = findLargestGap(stalls,boards.get(i));
			if(gap[2]>maxGap){
				maxIndex = i;
				maxStart = gap[0];
				maxEnd = gap[1];
				maxGap = gap[2];
			}
		}
		int[] ISE = new int[3];
		ISE[0]=maxIndex;
		ISE[1]=maxStart;
		ISE[2]=maxEnd;
		return ISE;
	}

	public static int[] quickSort(int[] array,int lowerIndex, int higherIndex) {
         
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which 
             * is greater then the pivot value, and also we will identify a number 
             * from right side which is less then the pivot value. Once the search 
             * is done, then we exchange both numbers.
             */
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
               int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(array,lowerIndex, j);
        if (i < higherIndex)
            quickSort(array,i, higherIndex);
	return array;
    }
 
}

class Board{
	private int start;
	private int end;
	
	public Board(int s, int e){
		start = s;
		end = e;
	}
	
	public int getStart(){ return start;}
	public int getEnd() {return end;}
	public int getLength() {return end-start+1;}
	public String toString(){ return "start: "+start + " end: "+end;}
}
