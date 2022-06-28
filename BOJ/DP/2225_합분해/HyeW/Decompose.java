import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Decompose {
    static int[][] dp;
    static int N,K;
    static final int div = 1000000000;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = br.readLine().split(" ");
        N = Integer.parseInt(arr[0]);
        K = Integer.parseInt(arr[1]);

        dp = new int[K+1][N+1];

        for(int i = 0; i < N+1; i++)
            dp[1][i] = 1;

        for(int i = 2; i <= K; i++)
            for(int j = 0; j <= N; j++) {
                for(int k = 0; k <= j; k++)
                    dp[i][j] = (dp[i][j] + dp[i-1][k]) % div;
            }

        System.out.println(dp[K][N]);
    }


}
