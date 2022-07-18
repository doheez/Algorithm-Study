package BOJ.DFSBFS.다리만들기;

import java.util.*;
import java.io.*;

class Location {
    int x;
    int y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Bridge {
    int x;
    int y;
    int area;
    int length;

    public Bridge(int x, int y, int area, int length) {
        this.x = x;
        this.y = y;
        this.area = area;
        this.length = length;
    }
}

public class Main {
    static int[][] map;

    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};
    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        int[][] addedMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int area = 2;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    changeToSameArea(i, j, area++);
                }
            }
        }

        Queue<Bridge> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        int nX = i + dirX[k];
                        int nY = j + dirY[k];

                        if ((0<=nX && nX<N) && (0<=nY && nY<N)
                                && map[nX][nY] == 0) {
                            q.add(new Bridge(i, j, map[i][j], 0));
                            break;
                        }
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        while(!q.isEmpty()) {
            Bridge cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int nX = cur.x + dirX[k];
                int nY = cur.y + dirY[k];

                if ((0<=nX && nX<N) && (0<=nY && nY<N)) {
                    if (map[nX][nY] == 0) {
                        map[nX][nY] = cur.area;
                        q.add(new Bridge(nX, nY, cur.area, cur.length+1));
                        addedMap[nX][nY] = cur.length+1;
                    } else if (map[nX][nY] != cur.area) {
                        answer = Math.min(answer, cur.length + addedMap[nX][nY]);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static void changeToSameArea(int i, int j, int area) {
        Queue<Location> q = new LinkedList<>();

        map[i][j] = area;

        q.add(new Location(i, j));

        while(!q.isEmpty()) {
            Location cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int nX = cur.x + dirX[k];
                int nY = cur.y + dirY[k];

                if ((0<=nX && nX<N) && (0<=nY && nY<N)
                && map[nX][nY] == 1) {
                    map[nX][nY] = area;
                    q.add(new Location(nX, nY));
                }
            }
        }
    }
}
