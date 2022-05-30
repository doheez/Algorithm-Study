import java.io.*;
import java.util.*;

public class Main {
    ArrayList<ArrayList<Node>> graph; // 그래프
    int V, E, start; // 정점 개수, 간선 개수, 시작 인덱스
    int[] distance; // 최단 거리 배열

    public void dijkstra() {
        // 최단 거리가 갱신된 노드들을 담을 우선순위 큐
        PriorityQueue<Node> pq = new PriorityQueue<>();

        distance[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            // 현재 최소 비용을 갖는 노드 선택 (동빈나 블로그 그림 중 dist 배열에서 숫자 제일 작은 거 선택하는 과정)
            Node currNode = pq.poll();

            // 방문 체크
            if (distance[currNode.index] < currNode.cost) {
                continue;
            }

            // 선택된 노드의 모든 인접 노드 검사
            for (Node nextNode : graph.get(currNode.index)) {
                // 선택된 노드를 거쳐서 다음 노드로 이동 할 때의 비용이 다음 노드의 최단 거리보다 작을 때 최단 거리 갱신
                if (distance[nextNode.index] > currNode.cost + nextNode.cost) {
                    distance[nextNode.index] = currNode.cost + nextNode.cost;
                    pq.add(new Node(nextNode.index, distance[nextNode.index]));
                }
            }
        }
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        // 그래프 생성, 그래프의 0번째 인덱스는 사용 X
        graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<Node>());
        }

        // 그래프 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int source = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt((st.nextToken()));

            graph.get(source).add(new Node(destination, cost));
        }

        // 최단 거리 배열 생성 및 초기화
        distance = new int[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // 다익스트라
        dijkstra();

        // 출력
        for (int i = 1; i < V + 1; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
                continue;
            }
            System.out.println(distance[i]);
        }
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
        // 비용이 적을수록 우선 순위 높다
        return Integer.compare(this.cost, o.cost);
    }
}