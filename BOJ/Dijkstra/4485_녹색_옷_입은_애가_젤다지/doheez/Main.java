import java.io.*;
import java.util.*;

public class Main {
    // 상하좌우
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    int[][] map;
    int[][] minCost;
    int N;

    public int dijkstra() {
        // 최단 거리가 갱신된 노드들을 담을 우선순위 큐
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 초기값
        minCost[0][0] = map[0][0];
        pq.add(new Node(0, 0, map[0][0]));

        while (!pq.isEmpty()) {
            // 현재 최소 비용을 갖는 노드 선택
            Node currNode = pq.poll();

            // 선택한 노드의 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nextRow = currNode.row + dr[i];
                int nextCol = currNode.col + dc[i];

                // 범위 검사
                if (nextRow < 0 || nextRow > N - 1 || nextCol < 0 || nextCol > N - 1) {
                    continue;
                }

                // 선택된 노드를 거쳐서 다음 노드로 이동 할 때의 비용이 다음 노드의 최소 비용보다 작을 때 최소 비용 갱신
                if (minCost[nextRow][nextCol] > currNode.cost + map[nextRow][nextCol]) {
                    minCost[nextRow][nextCol] = currNode.cost + map[nextRow][nextCol];
                    pq.add(new Node(nextRow, nextCol, minCost[nextRow][nextCol]));
                }
            }
        }
        return minCost[N - 1][N - 1];
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = 0;
        int count = 0;

        while (true) {
            count++;
            N = Integer.parseInt(br.readLine());

            if (N == 0) {
                break;
            }

            map = new int[N][N];
            minCost = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    minCost[i][j] = Integer.MAX_VALUE;
                }
            }
            // 다익스트라
            int result = dijkstra();

            // 출력
            System.out.println("Problem " + count + ": " + result);
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

class Node implements Comparable<Node> {
    int row;
    int col;
    int cost;

    Node(int row, int col, int cost) {
        this.row = row;
        this.col = col;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        // 비용이 적을수록 우선 순위 높다
        return Integer.compare(this.cost, o.cost);
    }
}