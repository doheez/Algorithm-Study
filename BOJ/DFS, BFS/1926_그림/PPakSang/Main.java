package BOJ.DFSBFS.그림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};

    static int n;
    static int m;

    static int[][] map;

    static int max = 0;
    static int pictureNum = 0;
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

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    max = Math.max(max, bfs(i, j));
                    pictureNum++;
                }
            }
        }

        System.out.println(pictureNum);
        System.out.println(max);
    }

    static int bfs(int x, int y) {
        map[x][y] = 0;
        int temp = 1;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];

            if ((0 <= nextX && nextX < n) && (0 <= nextY && nextY < m)) {

                if (map[nextX][nextY] == 1) {
                    temp += bfs(nextX, nextY);
                }
            }
        }

        return temp;
    }
}