import java.io.*;
import java.util.*;

public class BuildBridge {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Node> q;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static int min = Integer.MAX_VALUE;
    static int distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int land_cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                if (map[i][j] < 0) {
                    continue;
                }

                // 섬 구분하기
                land_cnt++;
                classify(i, j, land_cnt * -1);
                // 다리 만들기
                bfs(i,j,land_cnt*-1);
                q.clear();
                visited = new boolean[N][N];
            }

        System.out.println(min);

    }

    // 섬구분하고 해당 섬에 대한 bfs 초기화
    static void classify(int x, int y, int cnt) {
        Queue<Node> land = new LinkedList<>();
        land.add(new Node(x, y));
        map[x][y] = cnt;

        while (!land.isEmpty()) {
            Node cur = land.poll();

            for (int k = 0; k < 4; k++) {
                int dx = cur.x + dr[k];
                int dy = cur.y + dc[k];

                if (dx >= N || dy >= N || dx < 0 || dy < 0)
                    continue;
                if(map[dx][dy] == 0 && !visited[dx][dy]) {
                    q.add(new Node(dx, dy));
                    visited[dx][dy] = true;
                    continue;
                }
                if (map[dx][dy] == cnt || map[dx][dy] == 0)
                    continue;
                map[dx][dy] = cnt;
                land.add(new Node(dx, dy));

            }
        }

    }

    static void bfs(int x, int y, int land_cnt) {
        int cnt = 0;
        while (!q.isEmpty()) {
            int s = q.size();
            cnt ++;
            if (cnt > min)
                return;

            while (s-- > 0) {
                Node cur = q.poll();

                for (int i = 0; i < 4; i++) {
                    int dx = cur.x + dr[i];
                    int dy = cur.y + dc[i];

                    if (dx >= N || dy >= N || dx < 0 || dy < 0)
                        continue;

                    if (map[dx][dy] == land_cnt || visited[dx][dy])
                        continue;

                    if(map[dx][dy] != 0) {
                        min = Math.min(min, cnt);
                        return;
                    }
                    q.add(new Node(dx, dy));
                    visited[dx][dy] = true;
                }
            }
        }
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
