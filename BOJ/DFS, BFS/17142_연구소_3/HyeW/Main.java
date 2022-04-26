import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static int[][] room;
    static boolean[][] visited;
    static boolean[] v_visited;
    static int n, m, min = Integer.MAX_VALUE, virus_cnt, space;
    static ArrayList<Node> virus;
    static Queue<Node> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = new LinkedList<>();
        virus = new ArrayList<>();

        room = new int[n][n];
        visited = new boolean[n][n];

        // input 받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == 2) { // 바이러스 위치 배열 만듦
                    virus.add(new Node(i, j));
                    virus_cnt++;
                } else if (room[i][j] == 0) // 0일때 공간 개수
                    space++;
            }
        }

        if (space == 0) { // 공간이 없으면 0 return
            System.out.println(0);
            return;
        }

        v_visited = new boolean[virus_cnt];

        comb(0, 0);
        if (min == Integer.MAX_VALUE) {// min이 초기값이면 바이러스를 다 퍼트리지 못함
            System.out.println(-1);
            return;
        }
        System.out.println(min);
    }

    static void comb(int d, int r) {
        if (d == m) {
            for (int j = 0; j < virus_cnt; j++) {
                if (v_visited[j]) { // 선택된 virus q에 넣고 visited true로 바꿈
                    Node active = virus.get(j);
                    q.add(active);
                    visited[active.x][active.y] = true;
                }
            }
            bfs();
            // bfs후 visited q 초기화
            visited = new boolean[n][n];
            q.clear();
            return;
        }

        for (int i = r; i < virus_cnt; i++) {

            v_visited[i] = true;
            comb(d + 1, i + 1);
            v_visited[i] = false;

        }

    }

    static void bfs() {
        Node newq;
        // cnt : 몇 초만에 퍼트리는지
        // v_space : 바이러스가 퍼진 0 개수
        int cnt = 0, dx, dy, v_space = 0;
        while (q.size() > 0) {
            if (v_space == space) { // 0공간이 없으면 min찾고 함수 끝
                min = Math.min(min, cnt);
                return;
            }
            int s = q.size();
            cnt++;
            while (s-- > 0) {
                newq = q.poll();

                for (int i = 0; i < 4; i++) {
                    dx = newq.x + dr[i];
                    dy = newq.y + dc[i];

                    if (dx < 0 || dy < 0 || dx >= n || dy >= n)
                        continue;
                    if (visited[dx][dy])
                        continue;
                    if (room[dx][dy] == 1) // 벽일때
                    {
                        visited[dx][dy] = true;
                        continue;
                    }

                    if (room[dx][dy] == 0)
                        v_space++;
                    visited[dx][dy] = true;
                    q.add(new Node(dx, dy));
                }
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