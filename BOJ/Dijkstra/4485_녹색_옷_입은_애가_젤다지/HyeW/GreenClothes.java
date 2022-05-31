import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GreenClothes {
    static int N;
    static int[][] cave;
    static int[][] dist;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int num = 0;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0)
                break;

            cave = new int[N][N];
            dist = new int[N][N];

            for (int i = 0; i < N; i++)
                Arrays.fill(dist[i], Integer.MAX_VALUE);

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    cave[i][j] = Integer.parseInt(st.nextToken());
            }
            dist[0][0] = cave[0][0];
            int ans = dijkstra();
            num++;
            sb.append("Problem " + num + ": ").append(ans).append("\n");

        }

        System.out.println(sb);

    }

    static int dijkstra() {
        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

        q.add(new Node(0, 0, dist[0][0]));

        while (!q.isEmpty()) {
            Node curNode = q.poll();

            if (curNode.cost > dist[curNode.x][curNode.y])
                continue;

            for (int i = 0; i < 4; i++) {
                int dx = curNode.x + dr[i];
                int dy = curNode.y + dc[i];

                if (dx >= N || dy >= N || dx < 0 || dy < 0)
                    continue;

                if (dist[dx][dy] > curNode.cost + cave[dx][dy]) {
                    dist[dx][dy] = curNode.cost + cave[dx][dy];
                    q.add(new Node(dx, dy, dist[dx][dy]));
                }
            }

        }

        return dist[N - 1][N - 1];
    }

    static class Node {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
