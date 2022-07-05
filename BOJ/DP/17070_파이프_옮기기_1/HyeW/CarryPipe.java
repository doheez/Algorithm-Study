import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CarryPipe {
    static int[][] map;
    static Pipe[][] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new Pipe[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        // 초기화
        dp[0][0] = new Pipe(0, 0, 0);
        dp[0][1] = new Pipe(1, 0, 0);
        dp[1][0] = new Pipe(0, 0, 0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 벽 검사
                if (map[i][j] == 1) {
                    dp[i][j] = new Pipe(0, 0, 0);
                    continue;
                }
                // 초기화한 칸 패스
                if (dp[i][j] != null)
                    continue;
                int hor = 0;
                int ver = 0;
                int dia = 0;

                if (i != 0)
                    // 세로
                    ver = dp[i - 1][j].ver + dp[i - 1][j].dia;

                if (j != 0)
                    // 가로
                    hor = dp[i][j - 1].hor + dp[i][j - 1].dia;
                if (i != 0 && j != 0)
                    // 대각선
                    if (map[i - 1][j] == 0 && map[i][j - 1] == 0)
                        dia = dp[i - 1][j - 1].hor + dp[i - 1][j - 1].ver + dp[i - 1][j - 1].dia;

                dp[i][j] = new Pipe(hor, ver, dia);
            }
        }

        int ans = dp[N - 1][N - 1].hor + dp[N - 1][N - 1].ver + dp[N - 1][N - 1].dia;
        System.out.println(ans);

    }

    static class Pipe {
        int hor;
        int ver;
        int dia;

        Pipe(int hor, int ver, int dia) {
            this.hor = hor;
            this.ver = ver;
            this.dia = dia;
        }
    }
}
