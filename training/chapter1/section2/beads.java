/*
ID: majesti2
LANG: JAVA
TASK: beads
*/
import java.io.*;
import java.util.*;

public class beads {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("beads.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int length = Integer.parseInt(st.nextToken());
    StringTokenizer st1 = new StringTokenizer(f.readLine());
    String bead =st1.nextToken();
    char[] beads = bead.toCharArray();
	int max = 0;
	int maxIndex = 0;
    for(int i=0;i<beads.length;i++){
	int result = 0;
	char[] reconstructed = constructNewArray(beads,i);
	//System.out.print("reconstructed: ");
	//printCharArray(reconstructed);
	result += searchRight(reconstructed);
	//System.out.println("right: "+result);
	result += searchLeft(reconstructed);
	//System.out.println("total: "+result);
	if(result>max) {maxIndex=i;	max=result;}
	}
	if(max>length) max=length;
	out.print(max);
	out.print("\n");
	out.close();
	//System.out.println("MaxNumber: "+max);
	//System.out.println("MaxIndex: "+maxIndex);
	
}

	public static char[] constructNewArray(char[] beads, int index){
		char[] result = new char[beads.length];
		int k=0;
		for(int i=index;i<beads.length;i++) {result[i-index] = beads[i]; k++;}
		for(int i=0;i<index;i++) result[k+i] = beads[i];
		return result;	
	}

	public static int searchRight(char[] beads){
		int result = 0;	
		int i=0;
		char prob='w';
		while(i<beads.length){
			if(beads[i]== 'w') {result++; i++;}
			else {prob = beads[i]; break;}
		}
		while(i<beads.length){
			if((beads[i]==prob)||(beads[i]=='w')) {result++; i++;}
			else break;
		}
		return result;
	}
	
	public static int searchLeft(char[] array){
		for(int i=0; i<array.length/2; i++){ char temp = array[i]; array[i] = array[array.length -i -1]; array[array.length -i -1] = temp; }
		return searchRight(array);
	}

	/*public static void printCharArray(char[] beads){
		for(char c:beads) System.out.print(c);
		System.out.println();
	}*/
} 
