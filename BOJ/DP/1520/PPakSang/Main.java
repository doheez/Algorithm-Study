package BOJ.DP.내리막길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Main {
    static long[][] map;
    static boolean[][] visited;
    static int[][] memo;
    static int[] dirx = {1, -1, 0, 0};
    static int[] diry = {0, 0, -1, 1};

    static int m;
    static int n;

    public static void main(String[] args) throws IOException {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     StringTokenizer st = new StringTokenizer(br.readLine());

     m = Integer.parseInt(st.nextToken());
     n = Integer.parseInt(st.nextToken());

     map = new long[m][n];
     memo = new int[m][n];

     for (int i=0; i<m; i++) {
         st = new StringTokenizer(br.readLine());
         for (int j=0; j<n; j++) {
             map[i][j] = Long.parseLong(st.nextToken());
             memo[i][j] = -1;
         }
     }

     System.out.println(goDown(0, 0));

    }

    static int goDown(int x, int y) {
        if ((y == m-1) && (x == n-1)) {
            return 1;
        }
        if (memo[y][x] != -1) {

            return memo[y][x];
        }

        memo[y][x] = 0;

        long temp = map[y][x];
        for (int k=0; k<4; k++) {
            int nextX = x + dirx[k];
            int nextY = y + diry[k];
            if ((0<=nextX && nextX<n) && (0<=nextY && nextY<m)) {
                if(temp > map[nextY][nextX]) {
                    memo[y][x] += goDown(nextX, nextY);
                }
            }
        }

        return memo[y][x];
    }
}
