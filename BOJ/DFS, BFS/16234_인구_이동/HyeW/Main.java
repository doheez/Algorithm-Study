import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[][] A;
    static int n, l, r, people, land, day = -1;
    static ArrayList<Node> node;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static boolean[][] visited;
    static boolean not_end = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        A = new int[n][n];
        node = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }

        while (not_end) {
            not_end = false;
            visited = new boolean[n][n];
            day ++;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        dfs(i, j);
                        if (land > 1) { //국경선을 한번이라도 열었을때
                            not_end = true;
                            // 국경선 연 나라 값 넣어주기
                            int result = people / land;
                            for (Node a : node) {
                                A[a.x][a.y] = result;
                            }
                        }
                        // 값 초기화
                        land = 0;
                        people = 0;
                        node.clear();
                    }
                }
        }
        System.out.println(day);
    }

    static void dfs(int x, int y) {

        node.add(new Node(x, y));
        visited[x][y] = true;
        land++;
        people += A[x][y];

        for (int i = 0; i < 4; i++) {
            int dx = x + dr[i];
            int dy = y + dc[i];
            if (dx < 0 || dy < 0 || dx >= n || dy >= n)
                continue;
            if (visited[dx][dy])
                continue;

            int gap = Math.abs(A[dx][dy] - A[x][y]);
            if (gap >= l && gap <= r) {
                dfs(dx, dy);
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