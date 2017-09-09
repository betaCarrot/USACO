/*

/*
 ID: majesti2
 LANG: JAVA
 TASK: zerosum
 */

import java.io.*;
import java.util.*;
import java.lang.Math;

public class zerosum{
	private static ArrayList<int[]> markList = new ArrayList<int[]>();	

	public static void main(String[] args) throws IOException{
		ArrayList<String> result= new ArrayList<String>();
		BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		setUpArrayList(N-1);
		int[] numbers = new int[N];
		for(int i=0;i<N;i++) numbers[i]=i+1;
		for(int[] marks:markList){
			int[] temp = new int[marks.length];
			for(int i=0;i<marks.length;i++) temp[i]=marks[i];
			int[] processedNumbers = processStar(numbers,temp);
			for(int i=0;i<N;i++) numbers[i]=i+1;
			int[] processedMarks = processStarMarks(temp);
			int value = calculateValue(processedNumbers,processedMarks);
			/*if(value==0){
			System.out.println("numbers: ");
			for(int i:numbers) System.out.print(i+" ");
			System.out.println();
			System.out.println("marks: ");
			for(int i:marks) System.out.print(i+" ");
			System.out.println();
			System.out.println("processedNumbers: ");
			for(int i:processedNumbers) System.out.print(i+" ");
			System.out.println();
			System.out.println("processedMarks: ");
			for(int i:processedMarks) System.out.print(i+" ");
			System.out.println();
			System.out.println("value: "+value);
			}*/
			if(value==0){
				String s = "";
				for(int i=0;i<marks.length;i++){
					s += numbers[i];
					if(marks[i]==0) s += "+";
					if(marks[i]==1) s += "-";
					if(marks[i]==2) s += " ";
				}
				s += numbers[numbers.length-1];
				result.add(s);
			}
		}
		Collections.sort(result);
		for(String s:result) out.println(s);
		out.close();		
	}

	public static void setUpArrayList(int n){
		markList.clear();
		fillArray(0,n,new int[n]);
	}

	public static void fillArray(int count,int n, int[] temp){
		if(count==n) {
			int[] temp1 = new int[temp.length];
			for(int k=0;k<temp.length;k++) temp1[k] = temp[k];
			markList.add(temp1);
			return;
		}
		for(int i=0;i<3;i++){
			temp[count] = i;
			fillArray(count+1,n,temp);
		}
	}

	public static int[] processStar(int[] numbers, int[] marks){
		int processed = 0;
		for(int m=0;m<marks.length;m++){
			if(marks[m]==2){
				int i = m-processed;
				numbers[i] = numbers[i]*10+numbers[i+1];
				for(int k=i+1;k<numbers.length-1;k++) numbers[k] = numbers[k+1];
				numbers[numbers.length-1-processed] = 0;
				processed++;
			}
		}
		int[] temp = new int[numbers.length];
		for(int i=0;i<numbers.length;i++) temp[i]=numbers[i];
		return temp;
	}

	public static int[] processStarMarks(int[] marks){
		int i=0;
		int processed = 0;
		while(i<marks.length){
			if(marks[i]==2){
				for(int k=i;k<marks.length-1;k++) marks[k] = marks[k+1];
				marks[marks.length-1-processed] = -1;
			}
			else i++;
		}
		int[] temp = new int[marks.length];
		for(int k=0;k<marks.length;k++) temp[k]=marks[k];
		return temp;
	}

	public static int calculateValue(int[] numbers, int[] marks){
		int i=0;
		int result = numbers[0];
		while((i<marks.length)&&(marks[i]!=-1)){
			if(marks[i]==0) result += numbers[i+1];
			if(marks[i]==1) result -= numbers[i+1];
			i++;
		}
		return result;
	}
}