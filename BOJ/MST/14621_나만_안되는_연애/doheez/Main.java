import java.io.*;
import java.util.*;

public class Main {
    int N, M;
    ArrayList<Node>[] graph;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점 수, 간선 수 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 여/남학교 정보 입력
        char[] school = new char[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            school[i] = st.nextToken().charAt(0);
        }
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

            // 같은 성별 학교 사이의 간선은 그래프로 만들지 X
            if (school[a] != school[b]) {
                // 무방향 그래프이므로 양방향 다 연결
                graph[a].add(new Node(b, weight));
                graph[b].add(new Node(a, weight));
            }
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

            sum += edge.weight;
            visited[edge.index] = true;

            // MST 완성 여부 검사
            if (++cnt == N) {
                break;
            }

            // 인접 정점들 중 방문하지 않은 정점을 큐에 넣는다
            for (int i = 0; i < graph[edge.index].size(); i++) {
                Node nextEdge = graph[edge.index].get(i);
                if (!visited[nextEdge.index]) {
                    pq.add(nextEdge);
                }
            }
        }
        // 모든 학교를 연결하는 경로가 없을 경우 -1을 출력
        if (cnt != N) {
            return -1;
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