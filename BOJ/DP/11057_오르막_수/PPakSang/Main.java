package BOJ.DP.오르막수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[][] dp = new long[n][10];
        for (int i = 0; i < 10; i++) {
            dp[0][i] = 1L;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] += dp[i-1][k] % 10007;
                }
            }
        }

        long temp = 0;
        for (long a : dp[n-1]) {
            temp += a;
        }

        System.out.println(temp % 10007);

    }
}
