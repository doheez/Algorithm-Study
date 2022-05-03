import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RGB {
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static boolean[][] visited;
    static char[][] map;
    static int n, normal, special;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        visited = new boolean[n][n];
        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    normal++;
                    dfs(i, j);
                }
            }

        visited = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 'G')
                    map[i][j] ='R';
            }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (!visited[i][j]) {
                    special++;
                    dfs(i, j);
                }

        System.out.println(normal+" "+special);
    }

    static void dfs(int x, int y) {
        int dx, dy;

        for (int i = 0; i < 4; i++) {
            dx = x + dr[i];
            dy = y + dc[i];

            if (dx < 0 || dy < 0 || dx >= n || dy >= n)
                continue;
            if (visited[dx][dy])
                continue;
            if (map[dx][dy] != map[x][y])
                continue;

            visited[dx][dy] = true;
            dfs(dx, dy);
        }
    }
}