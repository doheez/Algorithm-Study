import java.io.*;
import java.util.*;

public class Main {
    int N, K;
    int[][] dp;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // dp 배열 생성
        // dp[K][N]: K개의 수로 N을 만드는 경우의 수
        dp = new int[K + 1][N + 1];

        // 초기화
        dp[0][0] = 1;

        // DP[K][N] = DP[K-1][0] + DP[K-1][1] + ... + DP[K-1][N]
        for (int i = 1; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                for (int m = 0; m <= j; m++) {
                    dp[i][j] += dp[i - 1][j - m];
                    dp[i][j] %= 1000000000;
                }
            }
        }

        // 출력
        System.out.println(dp[K][N]);
        br.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
