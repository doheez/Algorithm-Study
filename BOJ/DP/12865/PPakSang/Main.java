package BOJ.DP.평범한배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[k+1][n+1];
        int[] w = new int[n+1];
        int[] v = new int[n+1];

        for (int i = 1; i <= n; i++) {

            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= k; i++) {

            for (int j = 1; j<=n; j++) {
                int totalW = i - w[j];

                if (totalW >= 0) {
                    dp[i][j] = Math.max(v[j] + dp[totalW][j-1], dp[i][j-1]);
                }
                else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }

        System.out.println(dp[k][n]);

    }
}
