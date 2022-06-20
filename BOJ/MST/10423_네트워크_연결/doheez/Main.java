import java.io.*;
import java.util.*;

public class Main {
    int N, M;
    ArrayList<Node>[] graph;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 정점 수, 간선 수 입력
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // 그래프 생성 (0번째 인덱스는 사용 X)
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            // 무방향 그래프이므로 양방향 다 연결
            graph[a].add(new Node(b, weight));
            graph[b].add(new Node(a, weight));
        }

        int result = kruskal();
        System.out.println(result);
        br.close();
    }

    public int prim() {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int sum = 0;
        int cnt = 0;

        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node edge = pq.poll();

            // 방문 검사
            if (visited[edge.index]) {
                continue;
            }

            visited[edge.index] = true;
            sum += edge.weight;

            // MST 완성 검사
            if (++cnt == N) {
                break;
            }

            // 인접 정점 중 방문하지 않은 정점을 큐에 넣는다
            for (int i = 0; i < graph[edge.index].size(); i++) {
                Node nextEdge = graph[edge.index].get(i);
                if (!visited[nextEdge.index]) {
                    pq.add(nextEdge);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

class Node implements Comparable<Node> {
    int index;
    int weight;

    Node(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        // 우선순위는 가중치가 낮은 순
        return Integer.compare(this.weight, o.weight);
    }
}