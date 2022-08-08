import java.io.*;
import java.util.*;

public class Main {
    int N, M; // 세로 크기, 가로 크기
    int r, c, d; // 로봇 청소기의 위치, 바라보는 방향
    int[][] map; // 0: 청소 전, 1: 벽, 2: 청소 후
    int cnt = 0; // 청소한 칸 개수

    int DIRTY = 0;
    int WALL = 1;
    int CLEAN = 2;

    // 북동남서
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, M 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 로봇 청소기 위치, 바라보는 방향 입력
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        // 지도 입력
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 청소
        cleanUp(r, c, d);
        System.out.println(cnt);
        br.close();
    }

    public void cleanUp(int row, int col, int dir) {
        int nextDir = dir; // 회전했을 때의 방향 (dr, dc의 index)
        int backDir = dir - 2; // 반대 방향
        if (backDir < 0)
            backDir += 4;

        // 현위치 청소
        if (map[row][col] == DIRTY) {
            map[row][col] = CLEAN;
            cnt++;
        }

        for (int i = 0; i < 4; i++) {
            // 왼쪽으로 회전
            if (nextDir == 0) nextDir = 3;
            else nextDir--;

            // 회전한 방향이 청소 전이라면 직진
            int nextRow = row + dr[nextDir];
            int nextCol = col + dc[nextDir];
            if (map[nextRow][nextCol] == DIRTY) {
                cleanUp(nextRow, nextCol, nextDir);
                return;
            }
        }
        // 현위치로부터 네 방향 모두 청소했거나 벽이라면 방향 유지한 채로 후진
        int nextRow = row + dr[backDir];
        int nextCol = col + dc[backDir];
        if (map[nextRow][nextCol] != WALL) {
            cleanUp(nextRow, nextCol, dir);
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
