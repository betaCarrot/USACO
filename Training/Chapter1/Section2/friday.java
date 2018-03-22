/*
ID: majesti2
LANG: JAVA
TASK: friday
*/
import java.io.*;
import java.util.*;

public class friday {
  public static void main (String [] args) throws IOException {
	int day = 0;
    int month = 1;
    int year = 1900;
    BufferedReader f = new BufferedReader(new FileReader("friday.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int endYear=Integer.parseInt(st.nextToken())+year;
    int[] days = new int[7];
    while(year<endYear){
	while(month<13){
		int daysInMonth = daysInMonth(month,year);
		int date = 1;
		while(date<=daysInMonth){
			if(day==7) day=0;
			if(date==13) days[day]++;
			day++;			
			date++;
			}
		month++;}
	year++;
	month = 1;
		}
	
	
   for(int i=5;i<7;i++) out.print(days[i]+ " ");
   for(int i=0;i<4;i++) out.print(days[i]+ " ");
   out.print(days[4]);
   out.print("\n");	
   out.close();
}

    


	public static int daysInMonth(int month,int year){
		if((month==4)||(month==6)||(month==9)||(month==11)) return 30;
		else if(month==2){
			if(isLeap(year)) return 29;
			else return 28;
		}
		else return 31;
	}

	public static boolean isLeap(int year){
		if((year%400)==0) return true;
		if((year%100)==0) return false;
		if((year%4)==0) return true;
		return false;
	}		
} 
