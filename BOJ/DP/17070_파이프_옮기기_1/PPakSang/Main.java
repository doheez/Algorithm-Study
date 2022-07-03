package BOJ.DP.파이프옮기기기1;

import java.util.*;
import java.io.*;

public class Main {

    static int[] dirRow = {0, 1, 1};
    static int[] dirCol = {1, 0, 1};

    static int[][][] visited;
    static int[][] map;

    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int ans = 0;

        visited = new int[N][N][3];
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        move(0, 0, 1);

        for (int i = 0 ; i < 3; i++) {
            ans += visited[0][1][i];
        }
        System.out.println(ans);
    }

    static int move(int state, int row, int col) {
        if (row == N-1 && col == N-1) return 1;

        if (visited[row][col][state] > 0) return visited[row][col][state];

        for (int i = 0; i < 3; i++) {
            if (i + state == 1) {
                continue;
            }

            int nRow = row + dirRow[i];
            int nCol = col + dirCol[i];

                /*
                가려는 방향이 가로, 세로 일때는 앞만 보면 되고
                대각선일 때는 세방향 다 봐야한다
                */

            if (checkMovable(i, row, col)) {
                visited[row][col][state] += move(i, nRow, nCol);
            }
        }

        return visited[row][col][state];
    }

    static boolean checkMovable(int state, int row, int col) {
        int nRow, nCol;

        if (state == 0 || state == 1) {
            nRow = row + dirRow[state];
            nCol = col + dirCol[state];

            if ((0<=nRow && nRow<N) && (0<=nCol && nCol<N) && map[nRow][nCol] != 1) {
                return true;
            } else {
                return false;
            }
        } else {
            for (int i = 0; i < 3; i++) {
                nRow = row + dirRow[i];
                nCol = col + dirCol[i];

                if (!(0<=nRow && nRow<N) || !(0<=nCol && nCol<N) || map[nRow][nCol] == 1) {
                    return false;
                }
            }
        }

        return true;
    }
}
