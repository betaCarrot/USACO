import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class promote {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("promote.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("promote.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int CuBefore = Integer.parseInt(st.nextToken());
		int CuAfter = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		int AgBefore = Integer.parseInt(st.nextToken());
		int AgAfter = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		int AuBefore = Integer.parseInt(st.nextToken());
		int AuAfter = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		int PtBefore = Integer.parseInt(st.nextToken());
		int PtAfter = Integer.parseInt(st.nextToken());
		int Pt = PtAfter - PtBefore;
		AuBefore -= Pt;
		int Au = AuAfter - AuBefore;
		AgBefore -= Au;
		int Ag = AgAfter - AgBefore;
		out.println(Ag);
		out.println(Au);
		out.println(Pt);
		out.close();
	}
}
