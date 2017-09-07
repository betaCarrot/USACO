/*
 ID: majesti2
 LANG: JAVA
 PROG: nocows
 */
import java.io.*;
import java.util.*;
import java.lang.Math;

public class nocows{

	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[][] dp = new long[N+1][K+1];
		for(int i=1;i<=K;i++) dp[1][i] = 1;
		for(int k=1;k<=K;k++){
			for(int n=1;n<=N;n+=2){
				for(int i=1;i<n-1;i++){
					int j = n-1-i;
					dp[n][k] += dp[i][k-1]*dp[j][k-1];
				}
				dp[n][k] = dp[n][k]%9901;
			}
		}
		if(dp[N][K]<dp[N][K-1]) dp[N][K] += 9901;
		out.println((dp[N][K]-dp[N][K-1])%9901);
		out.close();
	}
}