import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static Queue<Node> q;
    static int n, cnt, sum, shark = 2, fish, eat;
    static boolean check;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        q = new LinkedList<>();
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    q.add(new Node(i, j));
                    map[i][j] = 0;
                    visited[i][j] = true;
                } else if (map[i][j] != 0) {
                    check = true;
                    fish++;
                }
            }
        }

        if (!check) { // 물고기가 없을 경우
            System.out.println(0);
            return;
        }
        BFS();
        System.out.println(sum);
    }

    static void BFS() {
        int s = 0;
        int min_row = 999;
        int min_col = 999;

        while (q.size() > 0) {
            s = q.size();
            cnt++;

            while (s-- > 0) {
                Node node = q.poll();

                for (int i = 0; i < 4; i++) {
                    int dx = node.x + dr[i];
                    int dy = node.y + dc[i];

                    if (dx < 0 || dy < 0 || dx >= n || dy >= n)
                        continue;

                    if (map[dx][dy] > shark || visited[dx][dy])
                        continue;

                    if (map[dx][dy] != 0 && map[dx][dy] < shark) {// 상어보다 작은 물고기가 있는 경우

                        // row 값과 col값이 작은 아이를 먼저 먹음
                        if (dx < min_row) {
                            min_row = dx;
                            min_col = dy;
                        }

                    }

                    q.add(new Node(dx, dy));
                    visited[dx][dy] = true;
                }
            }

            // 자식 노드 한번 다 끝남
            if (min_row != 999) {
                sum += cnt;
                fish--;

                // 물고기 없으면 탈출
                if (fish == 0)
                    return;

                eat++;
                map[min_row][min_col] = 0;
                if (eat == shark) {
                    shark++;
                    eat = 0;
                }

                // 배열 초기화
                q.clear();
                q.add(new Node(min_row, min_col));
                visited = new boolean[n][n];
                visited[min_row][min_col] = true;

                // 초기화
                cnt = 0;
                min_row = 999;
                min_col = 999;
            }

        }
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