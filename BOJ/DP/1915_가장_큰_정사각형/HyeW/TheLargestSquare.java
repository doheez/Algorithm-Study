import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TheLargestSquare {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int max = 0;

        int[][] dp = new int[N][M];
        char[][] square = new char[N][M];

        for (int i = 0; i < N; i++) {
            square[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                dp[i][j] = square[i][j] - '0';
                if(dp[i][j] == 1 && max == 0)
                    max =1;
                if (i > 0 && j > 0)
                    if (dp[i][j] == 1) {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                        max = Math.max(max, dp[i][j]);
                    }
            }
        }

        System.out.println((int) Math.pow(max, 2));
    }

}
