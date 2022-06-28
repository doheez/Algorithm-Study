import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int N;
    int[][] map;
    int[][] dpMin;
    int[][] dpMax;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N 입력
        N = Integer.parseInt(st.nextToken());

        // map 이차원 배열 생성
        map = new int[N][3];

        // map 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 배열 생성 및 초기화
        dpMin = new int[N][3];
        dpMax = new int[N][3];
        for (int i = 0; i < 3; i++) {
            dpMin[0][i] = map[0][i];
            dpMax[0][i] = map[0][i];
        }

        // 풀이
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                // 첫 번째 자리
                if (j == 0) {
                    dpMin[i][j] = Math.min(dpMin[i - 1][j], dpMin[i - 1][j + 1]) + map[i][j];
                    dpMax[i][j] = Math.max(dpMax[i - 1][j], dpMax[i - 1][j + 1]) + map[i][j];
                }
                // 두 번째 자리
                else if (j == 1) {
                    dpMin[i][j] = Math.min(dpMin[i - 1][j - 1], Math.min(dpMin[i - 1][j], dpMin[i - 1][j + 1])) + map[i][j];
                    dpMax[i][j] = Math.max(dpMax[i - 1][j - 1], Math.max(dpMax[i - 1][j], dpMax[i - 1][j + 1])) + map[i][j];
                }
                // 세 번째 자리
                else {
                    dpMin[i][j] = Math.min(dpMin[i - 1][j - 1], dpMin[i - 1][j]) + map[i][j];
                    dpMax[i][j] = Math.max(dpMax[i - 1][j - 1], dpMax[i - 1][j]) + map[i][j];
                }
            }
        }
        int min = Math.min(dpMin[N - 1][0], Math.min(dpMin[N - 1][1], dpMin[N - 1][2]));
        int max = Math.max(dpMax[N - 1][0], Math.max(dpMax[N - 1][1], dpMax[N - 1][2]));

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(max).append(" ").append(min);
        System.out.println(sb);
        br.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}