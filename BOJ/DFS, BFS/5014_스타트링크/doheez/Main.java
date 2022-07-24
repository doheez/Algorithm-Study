import java.io.*;
import java.util.*;

public class Main {
    int F, S, G, U, D;
    int[] visited;

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // visited 초기화
        visited = new int[F + 1];
        Arrays.fill(visited, 0);

        // bfs 탐색
        int result = bfs();

        // 출력
        if (result == -1) {
            System.out.println("use the stairs");
        } else {
            System.out.println(result);
        }

        br.close();
    }

    public int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);

        // 시작 층을 1로 카운팅
        visited[S] = 1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // 목표 층에 도착했다면 탐색 종료
            if (curr == G) {
                return visited[curr] - 1;
            }

            // UP
            int next = curr + U;
            if (next <= F && visited[next] == 0) {
                visited[next] = visited[curr] + 1;
                queue.add(next);
            }

            // DOWN
            next = curr - D;
            if (next > 0 && visited[next] == 0) {
                visited[next] = visited[curr] + 1;
                queue.add(next);
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
