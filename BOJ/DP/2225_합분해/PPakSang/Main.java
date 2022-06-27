package BOJ.DP.합분해;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int k;

    public static void main(String[] args) throws IOException {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     StringTokenizer st = new StringTokenizer(br.readLine());

     n = Integer.parseInt(st.nextToken());
     k = Integer.parseInt(st.nextToken());

     Long[][] dp = new Long[201][201];

     for (int i=0; i<201; i++) {
         for (int j=0; j<201; j++) {
             dp[i][j] = 0L;
         }
     }

     for (int i=1; i<=k; i++) {
         dp[0][i] = 1L;
     }

     for (int i=1; i<=n; i++) {
         for (int j=1; j<=k; j++) {
             dp[i][j] = (dp[i][j-1] + dp[i-1][j])%1000000000L;
         }
     }

     System.out.println(dp[n][k]);
    }

}
