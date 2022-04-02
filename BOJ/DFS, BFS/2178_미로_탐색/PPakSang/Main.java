package BOJ.DFSBFS.미로탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.PolicyNode;
import java.util.*;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int n;
    static int m;

    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");


        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }
        bfs(0, 0);

        System.out.println(map[n-1][m-1]);
    }

    static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();

        visited[x][y] = true;
        queue.add(new Point(x, y));

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = p.x + dirX[i];
                int nextY = p.y + dirY[i];

                if ((0 <= nextX && nextX < n) && (0 <= nextY && nextY < m) && (map[nextX][nextY] == 1) && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    map[nextX][nextY] = map[p.x][p.y] + 1;
                    queue.add(new Point(nextX, nextY));
                }
            }
        }

        return;
    }
}
