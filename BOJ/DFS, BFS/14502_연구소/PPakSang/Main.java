package BOJ.DFSBFS.연구실;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};

    static int n;
    static int m;

    static int[][] map;
    static int[][] tempMap;

    static int max = 0;
    static int zero = 0;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(max);
    }

    static void dfs(int depth) {

        if (depth == 3) {

            tempMap = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    tempMap[i][j] = map[i][j];
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (tempMap[i][j] == 2) {
                        bfs(i, j);
                    }
                }
            }

            int tempZero = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (tempMap[i][j] == 0) tempZero++;
                }
            }
            max = Math.max(max, tempZero);

            return;
        }

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(depth + 1);
                    map[i][j] = 0;
                }
            }
        }

    }

    static void bfs(int x, int y) {

        tempMap[x][y] = 3;

        for (int k = 0; k < 4; k++) {
            int nextX = x + dirX[k];
            int nextY = y + dirY[k];
            if ((0 <= nextX && nextX < n) && (0 <= nextY && nextY < m)) {

                if (tempMap[nextX][nextY] == 0) {
                    bfs(nextX, nextY);
                }
            }
        }

    }
}