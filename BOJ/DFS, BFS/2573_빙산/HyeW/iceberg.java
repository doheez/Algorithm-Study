
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class iceberg {
    static int n, m, depth, year;
    static boolean flag;
    static int[][] map, water;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static boolean[][] visited;
    static ArrayList<Node> ice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        water = new int[n][m];
        visited = new boolean[n][m];
        ice = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0)
                    ice.add(new Node(i, j));
            }
        }

        // 얼음 주변 ice개수 세기
        for (int i = 0; i < ice.size(); i++) {
            Node a = ice.get(i);
            int near = 0;
            for (int j = 0; j < 4; j++) {
                int dx = a.x + dr[j];
                int dy = a.y + dc[j];

                if (dx < 0 || dy < 0 || dx >= n || dy >= m)
                    continue;

                if (map[dx][dy] == 0)
                    near++;
            }
            water[a.x][a.y] = near;
        }

        while (ice.size() > 0) {

            // 빙산 갈라졌는지 확인하기
            for (Node cur : ice) {
                if (visited[cur.x][cur.y]) // 얘를 통과했다는게 2번 돈다는 것
                    continue;
                if (flag) { // dfs가 두번 돌때
                    System.out.println(year);
                    return;
                }
                dfs(cur.x, cur.y);
                flag = true;
                if (depth == ice.size())
                    break;
            }
            flag = false;
            melt();
            year++;
            visited = new boolean[n][m];
        }

        System.out.println(0);

    }

    static void dfs(int x, int y) {
        depth++;
        visited[x][y] = true;

        if (depth == ice.size())
            return;

        for (int i = 0; i < 4; i++) {
            int dx = x + dr[i];
            int dy = y + dc[i];

            if (dx < 0 || dy < 0 || dx >= n || dy >= m)
                continue;
            if (visited[dx][dy] || map[dx][dy] <= 0)
                continue;

            dfs(dx, dy);
        }

    }

    static void melt() {
        ArrayList<Node> r_ice = new ArrayList<>();
        for (int j = 0; j < ice.size(); j++) {
            Node cur = ice.get(j);
            map[cur.x][cur.y] -= water[cur.x][cur.y];
            // 얼음이 다 녹았을때
            if (map[cur.x][cur.y] <= 0) {
                r_ice.add(cur);
            }
        }

        for (Node cur : r_ice)
            for (int i = 0; i < 4; i++) { // 주변 얼음들 w에 +1해줌
                int dx = cur.x + dr[i];
                int dy = cur.y + dc[i];

                if (dx < 0 || dy < 0 || dx >= n || dy >= m)
                    continue;
                if (map[dx][dy] <= 0)
                    continue;

                water[dx][dy] += 1;
            }
        ice.removeAll(r_ice);
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;

        }
    }
}