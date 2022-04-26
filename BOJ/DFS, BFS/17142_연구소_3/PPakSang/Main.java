package BOJ.DFSBFS.연구소3.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Position {
    int x;
    int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N;
    static int M;

    static int[][] map;
    static int[][] virusMap;

    static List<Position> virusP = new ArrayList<>();
    static List<Position> selected = new ArrayList<>();

    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};

    static int total = 0;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");

        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        map = new int[N][N];
        virusMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(temp[j]);
                if (num == 2) {
                    virusP.add(new Position(i, j));
                    map[i][j] = 0;
                    virusMap[i][j] = 1;
                } else if (num == 0) {
                    map[i][j] = 0;
                    total++;
                } else {
                    map[i][j] = -1;
                }
            }
        }

        // virus 위치 저장했고, 벽 뺴고 초기화된 상태
        if (total == 0) {
            System.out.println(0);
            return;
        }
        choose(0, virusP.size(), 0);
        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    static void choose(int start, int end, int cnt) {
        if (cnt == M) {
            bfs();
            return;
        }

        for (int i = start; i < end; i++) {
            Position p = virusP.get(i);
            selected.add(p);
            map[p.x][p.y] = 1;
            choose(i+1, end, cnt+1);
            selected.remove(selected.size()-1);
            map[p.x][p.y] = 0;
        }
    }

    static void bfs() {
        Queue<Position> q = new LinkedList<>(selected);
        int cnt = 0;
        int max = Integer.MIN_VALUE;

        int[][] visit = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visit[i][j] = map[i][j];
            }
        }

        while (!q.isEmpty()) {
            Position p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = p.x + dirX[i];
                int nextY = p.y + dirY[i];

                if ((0<=nextX && nextX<N) && (0<=nextY && nextY<N) &&
                visit[nextX][nextY] == 0) {
                    if (virusMap[nextX][nextY] == 0) cnt++;
                    if (cnt == total) {
                        answer = Math.min(answer, visit[p.x][p.y]);
                        return;
                    }
                    q.add(new Position(nextX, nextY));
                    visit[nextX][nextY] = visit[p.x][p.y] + 1;
                }
            }
        }
    }
}
