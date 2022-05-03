package BOJ.DFSBFS.빙산;

import java.util.*;
import java.io.*;

class Location {
    int x;
    int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int N;
    static int M;
    static int[][] map;

    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");

        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        System.out.println(getLastDay());
    }

    static int getLastDay() {
        int days = 0;
        while (true) {
            days++;
            boolean flag = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0) {
                        flag = true;

                        int melt = check(i, j, days);

                        map[i][j] = map[i][j] - melt> 0 ? map[i][j] - melt : days*-1;
                    }

                }
            }

            if (search() >= 2) return days;

            if (!flag) return 0;
        }


    }

    static int check(int x, int y, int day) {
        int zeroCnt = 0;

        for (int i = 0; i < 4; i++) {
            int nX = x + dirX[i];
            int nY = y + dirY[i];


            if ((0<=nX && nX<N) && (0<=nY && nY<M)) {
                if (day*-1 < map[nX][nY] && map[nX][nY] <= 0) {
                    zeroCnt++;
                }
            }
        }

        return zeroCnt;
    }

    static int search() {
        boolean[][] visited = new boolean[N][M];
        Queue<Location> q = new LinkedList<>();
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    visited[i][j] = true;
                    q.add(new Location(i, j));
                    while (!q.isEmpty()) {
                        Location l = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nX = l.x + dirX[k];
                            int nY = l.y + dirY[k];

                            if ((0<=nX && nX<N) && (0<=nY && nY<M) && map[nX][nY] > 0 && !visited[nX][nY]) {
                                visited[nX][nY] = true;
                                q.add(new Location(nX, nY));
                            }
                        }
                    }
                    cnt++;
                }
            }
        }

        return cnt;
    }
}

