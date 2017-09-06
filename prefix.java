/*
 ID: majesti2
 LANG: JAVA
 PROG: prefix
 */
import java.io.*;
import java.util.*;

public class prefix{
	private static ArrayList<String> prefixes = new ArrayList<String>();

	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		String line;
		while(true){
			boolean end = false;
			StringTokenizer st = new StringTokenizer(f.readLine());
			while(st.hasMoreElements()) {
				line = st.nextToken();
				if(line.equals(".")) {end = true; break;}
				prefixes.add(line);
			}
			if(end) break;
		}
		StringBuilder sb = new StringBuilder();
		while((line=f.readLine())!=null){
			sb.append(line);
		}
	String sequence = sb.toString();
	boolean[] result = new boolean[sequence.length()];
	int max = 0;
	for(int i=0;i<sequence.length();i++){
		for(String s:prefixes){
			if(i-s.length()+1>=0){
				if(sequence.substring(i-s.length()+1,i+1).equals(s)){
					if((i-s.length()<0)||result[i-s.length()]){
						result[i] = true;
						max = i;
					}
				}
			}
		}
	}
	if(max==0){
		if(result[0]) out.println(1);
		else out.println(0);
	}
	else out.println(max+1);
	out.close();
	}
}