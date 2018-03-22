/*
ID: majesti2
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

public class ride {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("ride.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    String comet = st.nextToken();
    int cometIndex = convertStringIntoInt(comet);
    StringTokenizer st2 = new StringTokenizer(f.readLine());
    String group = st2.nextToken();
    int groupIndex = convertStringIntoInt(group);
    if((cometIndex%47)==(groupIndex%47)) out.println("GO");
	else out.println("STAY");
    out.close();
  }

  public static int convertStringIntoInt(String s){
	int index = 1;
	for(int i=0;i<s.length();i++){
	char c = s.charAt(i);
	int number = convertCharIntoInt(c);
	index *= number;}
	return index;
    }

  public static int convertCharIntoInt(char c){
	return Character.getNumericValue(c)-9;
	}
}
