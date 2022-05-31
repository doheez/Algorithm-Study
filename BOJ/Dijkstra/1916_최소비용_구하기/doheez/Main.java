import java.io.*;
import java.util.*;

public class Main {
    ArrayList<ArrayList<Node>> graph;
    int N, M, start, end;
    int[] minCost;

    public void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(start, 0));
        minCost[start] = 0;

        while (!pq.isEmpty()) {
            // 현재 최소 비용을 갖는 노드 선택
            Node currNode = pq.poll();

            // 도착 노드를 큐에서 꺼냈다면 해당 노드까지는 최솟값 갱신이 완료되었다는 것이 확정이므로 출력하고 다익스트라 종료
            if (currNode.index == end) {
                System.out.println(minCost[currNode.index]);
                return;
            }

            // 방문 체크
            if (minCost[currNode.index] < currNode.cost) {
                continue;
            }

            // 선택된 노드의 모든 인접 노드 검사
            for (Node nextNode : graph.get(currNode.index)) {
                // 선택된 노드를 거쳐서 다음 노드로 이동 할 때의 비용이 다음 노드의 최소 비용보다 작을 때 최소 비용 갱신
                if (minCost[nextNode.index] > currNode.cost + nextNode.cost) {
                    minCost[nextNode.index] = currNode.cost + nextNode.cost;
                    pq.add(new Node(nextNode.index, minCost[nextNode.index]));
                }
            }
        }
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // 그래프 생성
        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<Node>());
        }
        // 그래프 입력
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int source = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(source).add(new Node(destination, cost));
        }
        // start, end 노드 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 최소 비용 배열 생성 및 초기화
        minCost = new int[N + 1];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        // 다익스트라
        dijkstra();
        br.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

class Node implements Comparable<Node> {
    int index;
    int cost;

    Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}