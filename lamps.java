/*
 ID: majesti2
 LANG: JAVA
 TASK: lamps
 */

import java.util.*;
import java.io.*;
import java.lang.Math;

public class lamps {
	private static boolean[] lamps;
	private static int N;
	private static PrintWriter out;
	private static ArrayList<Integer> mustBeOn;
	private static ArrayList<Integer> mustBeOff;
	private static boolean isImpossible=true;
	private static ArrayList<String> strings = new ArrayList<String>();
	
	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());	
		StringTokenizer st1 = new StringTokenizer(f.readLine());
		int counter = Integer.parseInt(st1.nextToken());
		StringTokenizer st2 = new StringTokenizer(f.readLine());
		mustBeOn = new ArrayList<Integer>();
		while(true){
			int temp = Integer.parseInt(st2.nextToken());
			if(temp==-1) break;
			mustBeOn.add(temp);
		}
		StringTokenizer st3 = new StringTokenizer(f.readLine());
		mustBeOff = new ArrayList<Integer>();
		while(true){
			int temp = Integer.parseInt(st3.nextToken());
			if(temp==-1) break;
			mustBeOff.add(temp);
		}
		lamps = new boolean[N];
		reset();
		run(counter);
		Collections.sort(strings);
		if(isImpossible) out.println("IMPOSSIBLE");
		else{
			for(String s:strings) out.println(s);
		}
		out.close();
	}

	public static void run(int counter){
		if(counter==0) printLamps();
		else if(counter==1){
			pressButton1();
			printLamps();
			reset();
			pressButton2();
			printLamps();
			reset();
			pressButton3();
			printLamps();
			reset();
			pressButton4();
			printLamps();
			reset();
		}
		else if(counter==2){
			printLamps();
			pressButton1();
			pressButton2();
			printLamps();
			reset();
			pressButton1();
			pressButton3();
			printLamps();
			reset();
			pressButton1();
			pressButton4();
			printLamps();
			reset();
			pressButton2();
			pressButton3();
			printLamps();
			reset();
			pressButton2();
			pressButton4();
			printLamps();
			reset();
			pressButton3();
			pressButton4();
			printLamps();
			reset();
		}
		else if(counter%2==1){
			pressButton1();
			printLamps();
			reset();
			pressButton2();
			printLamps();
			reset();
			pressButton3();
			printLamps();
			reset();
			pressButton4();
			printLamps();
			reset();
			pressButton1();
			pressButton2();
			pressButton3();
			printLamps();
			reset();
			pressButton1();
			pressButton2();
			pressButton4();
			printLamps();
			reset();
			pressButton1();
			pressButton3();
			pressButton4();
			printLamps();
			reset();
			pressButton2();
			pressButton3();
			pressButton4();
			printLamps();
			reset();
		}
		else{
			printLamps();
			pressButton1();
			pressButton2();
			printLamps();
			reset();
			pressButton1();
			pressButton3();
			printLamps();
			reset();
			pressButton1();
			pressButton4();
			printLamps();
			reset();
			pressButton2();
			pressButton3();
			printLamps();
			reset();
			pressButton2();
			pressButton4();
			printLamps();
			reset();
			pressButton3();
			pressButton4();
			printLamps();
			reset();
			pressButton1();
			pressButton2();
			pressButton3();
			pressButton4();
			printLamps();
			reset();
		}			
	}

	public static void printLamps(){
		for(int i: mustBeOn){
			if(!lamps[i-1]) return;
		}
		for(int i:mustBeOff){
			if(lamps[i-1]) return;
		}
		isImpossible = false;
		String s = "";
		for(int i=0;i<N;i++){
			if(lamps[i]) s +=1;
			else s+=0;
		}
		strings.add(s);
	}
			

	public static void reset(){
		for(int i=0;i<N;i++) lamps[i] = true;
	}

	public static void pressButton1(){
		for(int i=0;i<N;i++) lamps[i] = !lamps[i];
	}

	public static void pressButton2(){
		for(int i=0;i<N;i+=2) lamps[i] = !lamps[i];
	}

	public static void pressButton3(){
		for(int i=1;i<N;i+=2) lamps[i] = !lamps[i];
	}

	public static void pressButton4(){
		for(int i=0;i<N;i+=3) lamps[i] = !lamps[i];
	}				
}


	
	
