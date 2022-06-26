import java.io.*;
import java.util.*;

public class Main {
    int n, m;
    int[][] map;
    int[][] dp;
    int max;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n, m 입력
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // map 이차원 배열 생성 및 입력
        map = new int[n][];
        for (int i = 0; i < n; i++) {
            map[i] = new int[m];
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        // dp 배열 생성
        dp = new int[n][];
        for (int i = 0; i < n; i++) {
            dp[i] = new int[m];
        }

        // 풀이
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 예외 1, 0행 또는 0열의 dp값은 map값 그대로
                if (i == 0 || j == 0) {
                    dp[i][j] = map[i][j];
                }
                // 예외 2, map값이 0이라면 dp값도 0
                else if (map[i][j] == 0) {
                    dp[i][j] = 0;
                }
                // dp 값은 dp 이차원 배열에서 (왼쪽, 위쪽, 왼쪽 대각선 위쪽) 중 제일 작은 숫자 + 1
                else {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                }
                // max 갱신
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }

        // 출력
        System.out.println(max * max);
        br.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
