package BOJ.dijkstra.녹색옷입은애가젤다지;

/*
동굴을 나갈 건데 가장 돈을 덜 잃으면서 가야한다

내가 지금 당장 갈 수 있는 곳에서 적게 잃는 곳 가야지

그 곳은 가장 최소로 잃는 위치가 맞고, 그 위치에서부터 방문할 수 있는 곳 중에서 최단 경로로 갈만한 곳 다 pq 넣기

*/


import java.util.*;
import java.io.*;

class Position {
    int x;
    int y;
    int cost;

    public Position(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}

public class Main {

    static boolean[][] visited;
    static int[][] loopy;
    static int[][] map;

    static int[] dirX = new int[]{1, -1, 0, 0};
    static int[] dirY = new int[]{0, 0, 1, -1};

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int cnt = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) break;

            loopy = new int[N][N];
            map = new int[N][N];
            visited = new boolean[N][N];


            for (int i = 0; i < N; i++) {
                String[] temp = br.readLine().split(" ");

                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(temp[j]);
                    loopy[i][j] = Integer.MAX_VALUE;
                }
            }


            StringBuilder sb = new StringBuilder();
            sb.append("Problem ");
            sb.append(cnt++);
            sb.append(": ");
            sb.append(dijkstra(N-1));

            System.out.println(sb.toString());
        }


    }

    static int dijkstra(int end) {
        PriorityQueue<Position> pq = new PriorityQueue<>(new Comparator<Position>(){
            public int compare(Position p1, Position p2) {
                return p1.cost - p2.cost;
            }
        });

        pq.add(new Position(0, 0, map[0][0]));

        while(!pq.isEmpty()) {
            Position cur = pq.poll();

            if (cur.x == end && cur.y == end) return cur.cost;

            if (visited[cur.x][cur.y]) continue;

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dirX[i];
                int nextY = cur.y + dirY[i];

                if ((0<=nextX && nextX<=end) && (0<=nextY && nextY<=end)
                && !visited[nextX][nextY]) {

                    if (loopy[nextX][nextY] > cur.cost + map[nextX][nextY]) {
                        loopy[nextX][nextY] = cur.cost + map[nextX][nextY];
                        pq.add(new Position(nextX, nextY, loopy[nextX][nextY]));
                    }
                }
            }

            visited[cur.x][cur.y] = true;
        }

        return loopy[end][end];
    }
}
