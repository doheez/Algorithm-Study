package BOJ.DFSBFS.적록색약;

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
    static char[][] map;
    static boolean[][] visited;

    static int[] dirX = new int[]{1, -1, 0, 0};
    static int[] dirY = new int[]{0, 0, 1, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp.charAt(j);
            }
        }

        int able = checkSpace(false);
        int unable = checkSpace(true);

        System.out.printf("%d %d", able, unable);

    }

    static int checkSpace(boolean unable) {
        Queue<Location> q = new LinkedList<>();
        Map<Character, Integer> color = new HashMap<>();
        int space = 0;

        if (unable) {
            color.put('R', 1);
            color.put('G', 1);
            color.put('B', 2);
        } else {
            color.put('R', 1);
            color.put('G', 2);
            color.put('B', 3);
        }

        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    q.add(new Location(i, j));

                    int colorNum = color.get(map[i][j]);

                    while (!q.isEmpty()) {
                        Location l = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nX = l.x + dirX[k];
                            int nY = l.y + dirY[k];

                            if ((0<=nX && nX<N) && (0<=nY && nY<N)
                                    && !visited[nX][nY] && (color.get(map[nX][nY]) == colorNum)) {
                                visited[nX][nY] = true;
                                q.add(new Location(nX, nY));
                            }
                        }
                    }

                    space++;
                }
            }
        }

        return space;
    }
}
