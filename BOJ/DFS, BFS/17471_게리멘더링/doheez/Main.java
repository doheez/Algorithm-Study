import java.util.*;
import java.io.*;

/**
 * 시간 초과
 */
public class Main {
    int N;
    int[] people;
    ArrayList<Integer> group1 = new ArrayList<>();
    ArrayList<Integer> group2 = new ArrayList<>();
    ArrayList<Integer>[] graph;
    boolean[] visited;
    int min = Integer.MAX_VALUE;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 입력
        N = Integer.parseInt(br.readLine());

        // visited 초기화
        visited = new boolean[N + 1];
        Arrays.fill(visited, false);

        // 구역별 인구 수 입력
        people = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }
        // 그래프 생성
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        // 그래프 입력
        for (int u = 1; u <= N; u++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            for (int i = 0; i < V; i++) {
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }
        }
        // 풀이
        combination(0);

        // 출력
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
        br.close();
    }

    public void combination(int cnt) {
        if (cnt > 0) {
            // 선거구 나누기
            for (int i = 1; i <= N; i++) {
                if (visited[i]) {
                    group1.add(i);
                } else {
                    group2.add(i);
                }
            }

            // 게리맨더링
            gerrymandering();

            // 선거구 초기화
            group1.clear();
            group2.clear();
        }

        // N개 중 x개를 고른 것과 (N-x)개를 고른 것은 같으니까 중복 제거
        if (cnt == N / 2) {
            return;
        }

        // 조합
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            combination(cnt + 1);
            visited[i] = false;
        }
    }

    public void gerrymandering() {
        // 선거구 각각 bfs 수행하여 연결되어 있는지 검사
        int group1Cnt = bfs(1);
        if (group1Cnt == -1) {
            return;
        }
        int group2Cnt = bfs(2);

        // 인구 합 차이랑 min 중 최솟값 구하기
        if (group2Cnt != -1) {
            min = Math.min(min, Math.abs(group1Cnt - group2Cnt));
        }
    }

    public int bfs(int type) {
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> group;
        boolean[] visited = new boolean[N + 1];
        boolean isConnected = true;
        int cnt = 0;

        if (type == 1) {
            group = group1;
        } else {
            group = group2;
        }

        visited[group.get(0)] = true;
        queue.add(group.get(0));

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // 인접 노드들 탐색
            for (int next : graph[curr]) {
                // 선거구에 포함되면 방문하고 큐에 넣기
                if (!visited[next] && group.contains(next)) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        // 방문하지 않은 구역이 있다면 연결된 선거구 X
        for (int i = 0; i < group.size(); i++) {
            if (!visited[group.get(i)]) {
                isConnected = false;
            }
        }
        // 선거구 전체 인구 수 세기
        if (isConnected) {
            for (int i : group) {
                cnt += people[i];
            }
            return cnt;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
