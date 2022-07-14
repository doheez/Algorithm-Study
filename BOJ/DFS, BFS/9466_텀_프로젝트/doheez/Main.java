import java.io.*;
import java.util.*;

public class Main {
    int n;
    int cnt = 0;
    int[] pick;
    boolean[] visited; // 단순 방문 여부만 확인
    boolean[] done; // 방문이 완전히 끝났는지 (다시 방문할 일이 없는지) 확인

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // 테스트 케이스 T개 만큼 반복
        for (int i = 0; i < T; i++) {
            // n, 선택한 학생 번호 입력
            n = Integer.parseInt(br.readLine());
            pick = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                pick[j] = Integer.parseInt(st.nextToken());
            }
            // visited, done 초기화
            visited = new boolean[n + 1];
            Arrays.fill(visited, false);
            done = new boolean[n + 1];
            Arrays.fill(done, false);

            // dfs로 사이클 검사 (사이클에 속하는 학생 수 카운트)
            for (int j = 1; j <= n; j++) {
                if (!visited[j]) {
                    dfs(j);
                }
            }
            // 사이클이 아닌 노드 개수 출력
            System.out.println(n - cnt);
            cnt = 0;
        }
    }

    public void dfs(int curr) {
        // 현재 노드 방문
        visited[curr] = true;

        // 다음 노드를 방문하지 않았다면 방문
        int next = pick[curr];
        if (!visited[next]) {
            dfs(next);
        }
        // 다음 노드를 방문했지만, 방문이 끝난 게 아니라면 사이클이라고 판단
        else if (!done[next]) {
            // 팀원 카운트
            for (int i = next; i != curr; i = pick[i]) {
                cnt++;
            }
            // 자기 자신 카운트
            cnt++;
        }
        // 더이상 해당 노드를 방문할 일 없다
        done[curr] = true;
    }


    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
