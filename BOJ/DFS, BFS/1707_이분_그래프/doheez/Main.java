import java.io.*;
import java.util.*;

public class Main {
    int V, E;
    ArrayList<Integer>[] graph;
    int[] visited; // 0: 미방문, 1: one side, 2: the other side

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        // K개의 테스트 케이스만큼 반복
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            // 방문 배열 생성 (0번째 인덱스는 사용 X)
            visited = new int[V + 1];
            Arrays.fill(visited, 0);

            // 그래프 생성 (0번째 인덱스는 사용 X)
            graph = new ArrayList[V + 1];
            for (int j = 1; j <= V; j++) {
                graph[j] = new ArrayList<>();
            }
            // 그래프 입력
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }
            // dfs 탐색
            for (int j = 1; j <= V; j++) {
                if (visited[j] == 0) {
                    dfs(j);
                }
            }

            // 이분그래프 검사 및 출력
            if (isBipartiteGraph()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        br.close();
    }

    public void dfs(int curr) {
        // 방문하지 않았다면 방문 (시작 노드를 위한 방문 표시인 듯)
        if (visited[curr] == 0) {
            visited[curr] = 1;
        }
        // 인접 노드 방문
        for (int i = 0; i < graph[curr].size(); i++) {
            int next = graph[curr].get(i);

            if (visited[next] == 0) {
                if (visited[curr] == 1) {
                    visited[next] = 2;
                } else if (visited[curr] == 2) {
                    visited[next] = 1;
                }

                dfs(next);
            }
        }
    }

    public boolean isBipartiteGraph() {
        for (int u = 1; u <= V; u++) {
            for (int i = 0; i < graph[u].size(); i++) {
                int v = graph[u].get(i);
                if (visited[u] == visited[v]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}