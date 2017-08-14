/*
ID: majesti2
LANG: JAVA
TASK: combo
*/
import java.io.*;
import java.util.*;

public class combo{
public static void main (String [] args) throws IOException {
	BufferedReader f = new BufferedReader(new FileReader("combo.in"));
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
	StringTokenizer st = new StringTokenizer(f.readLine());
	int N = Integer.parseInt(st.nextToken());
	StringTokenizer st1 = new StringTokenizer(f.readLine());
	int a = Integer.parseInt(st1.nextToken());
	int b = Integer.parseInt(st1.nextToken());
	int c = Integer.parseInt(st1.nextToken());
	StringTokenizer st2 = new StringTokenizer(f.readLine());
	int d = Integer.parseInt(st2.nextToken());
	int e = Integer.parseInt(st2.nextToken());
	int f1 = Integer.parseInt(st2.nextToken());
	ArrayList<Digits> answer = new ArrayList<Digits>();
	for(int i=-2;i<=2;i++){
		for(int k=-2;k<=2;k++){
			for(int p=-2;p<=2;p++){
				Digits toAdd = new Digits(correction(a+i,N),correction(b+k,N),correction(c+p,N));
				 if(!isOutOfBounds(toAdd,N)) answer.add(toAdd);
			}
		}
	}
	for(int i=-2;i<=2;i++){
		for(int k=-2;k<=2;k++){
			for(int p=-2;p<=2;p++){
				Digits toAdd = new Digits(correction(d+i,N),correction(e+k,N),correction(f1+p,N));
				if(!isOutOfBounds(toAdd,N)) answer.add(toAdd);
			}
		}
	}
	for(int i=0;i<answer.size();i++){
		int k=0;
      while(k<answer.size()){
			if((i!=k)&&(answer.get(i).isEqual(answer.get(k)))) answer.remove(k);
        	else k++;
		}
	}
	//for(Digits d1:answer) System.out.println(d1);
        //System.out.println(answer.size());
	out.println(answer.size());
	out.close();
	}

	public static int correction(int a,int N){
		if(a>N) a = a-N;
		if(a<=0) a = N+a;
      	if(a<=0) return Integer.MAX_VALUE;
		return a;
	}
	
	public static boolean isOutOfBounds(Digits checking,int N){
		if(checking.getA()>N) return true;
		if(checking.getB()>N) return true;
		if(checking.getC()>N) return true;
		return false;
	}
}

class Digits{
	private int a;
	private int b;
	private int c;
	
	public Digits(int a,int b,int c){
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public int getA() {return a;}
	public int getB() {return b;}
	public int getC() {return c;}

	public boolean isEqual(Digits next){
		return (a==next.getA())&&(b==next.getB())&&(c==next.getC());
	}

  public String toString(){
    	return (a+","+b+","+c);
  }
}
		
    
