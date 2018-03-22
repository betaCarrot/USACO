/*
ID: majesti2
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;

public class milk2 {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int people = Integer.parseInt(st.nextToken());
    int readerIndex = 0;
    int[] startArray = new int[people];
    int[] endArray = new int[people];	
    while(readerIndex<people){
	StringTokenizer st1 = new StringTokenizer(f.readLine());
	startArray[readerIndex] = Integer.parseInt(st1.nextToken());
	endArray[readerIndex] = Integer.parseInt(st1.nextToken());
	readerIndex++;
    }
    ArrayList<line> lines = new ArrayList<line>();
    for(int i=0;i<people;i++){
	int k=0;
	boolean merged = false;
	while(k<lines.size()){
		if((lines.get(k).getStart()>startArray[i])&&(lines.get(k).getEnd()<endArray[i])){
			//System.out.println("removed: "+lines.get(k));
			lines.remove(k);
		}
		//if(lines.get(k).getStart()>endArray[i])
		else if((lines.get(k).getStart()>=startArray[i])&&(lines.get(k).getEnd()>=endArray[i])&&(lines.get(k).getStart()<=endArray[i])){
			lines.get(k).mergeLeft(new line(startArray[i],endArray[i]));
			merged = true;
						//System.out.println("mergedLeft: "+lines.get(k));
			k++;
		}
		else if((lines.get(k).getStart()<startArray[i])&&(lines.get(k).getEnd()>endArray[i])) {merged = true; k++;}
		else if((lines.get(k).getStart()<=startArray[i])&&(lines.get(k).getEnd()<=endArray[i])&&(lines.get(k).getEnd()>=startArray[i])){
			lines.get(k).mergeRight(new line(startArray[i],endArray[i]));
			merged = true;
			//System.out.println("mergedRight: "+lines.get(k));
			k++;
		}
		else k++;
		//if(lines.get(k).getEnd()<startArray[i])
	}
	if(!merged) lines.add(new line(startArray[i],endArray[i]));
	//System.out.println("added: "+new line(startArray[i],endArray[i])); 
	}
	int[] start2 = new int[lines.size()];
	int[] end2 = new int[lines.size()];
	for(int i=0;i<lines.size();i++){
		start2[i]=lines.get(i).getStart();
		end2[i]=lines.get(i).getEnd();
	}
	int[] start3 = quicksort(start2,0,start2.length-1);
	//for(int i:start3) System.out.println(i);
	int[] end3 = quicksort(end2,0,end2.length-1);
	for(int i=0;i<lines.size();i++){
		lines.set(i,new line(start3[i],end3[i]));
	}
	//for(line l:lines) System.out.println(l);
	for(int i=0;i<lines.size();i++){
		int k=0;
		while(k<lines.size()){
			if((lines.get(k).getStart()<lines.get(i).getEnd())&&(lines.get(k).getEnd()>=lines.get(i).getEnd())){
				lines.set(i,new line(lines.get(i).getStart(),lines.get(k).getEnd()));
				k++;
				//lines.remove(k);
				//System.out.println();
				//System.out.println(lines.get(i));
			}
			else k++;
		}
	}
	//for(line l:lines) System.out.println(l);
	int maxLength = 0;
	int maxGap = 0;
	int maxGapIndex = 0;
	int maxGapValue = 0;
	int lastEnd = lines.get(0).getStart();
	for(int i=0;i<lines.size();i++){
		int length = lines.get(i).getDuration();
		if(length>maxLength) maxLength = length;
		int gap = lines.get(i).getStart()-lastEnd;
		if(gap>maxGap) {maxGap = gap; maxGapIndex = i; maxGapValue = lines.get(i).getStart();}
		lastEnd = lines.get(i).getEnd();
	}

	//System.out.println("maxLength: "+maxLength);
	//System.out.println("maxGapIndex: "+maxGapIndex);
	//System.out.println("maxGapValue: "+maxGapValue);
	//System.out.println("maxGap: "+maxGap);
	out.print(maxLength+" "+maxGap+"\n");
	out.close();	 	
}

    
        private static int[] quicksort(int[] numbers,int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = numbers[low + (high-low)/2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (numbers[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (numbers[j] > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(numbers,low, j);
        if (i < high)
            quicksort(numbers,i, high);
	return numbers;
    }
}

class line{
	private int start;
	private int end;
	
	public line(int start,int end){
		this.start = start;
		this.end = end;
	}
	
	public int getStart() {return start;}
	public int getEnd() {return end;}
	public int getDuration() {return end-start;}
	public void mergeLeft(line next){
		start = next.getStart();
	}
	public void mergeRight(line next){
		end = next.getEnd();
	}
	public String toString(){ return "start: "+start + " end: "+end; }
	public boolean isGreaterThan(line next) {return start>next.getStart();}
	public boolean isSmallerThan(line next) {return start<next.getStart();}
}
		 
