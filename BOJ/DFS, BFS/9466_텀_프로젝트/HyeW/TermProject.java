import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TermProject {
    static int T;
    static int n;
    static int cnt;
    static int[] root;
    static boolean[] visited;
    static boolean[] finished;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            root = new int[n + 1];
            visited = new boolean[n + 1];
            finished = new boolean[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++)
                root[j] = Integer.parseInt(st.nextToken());

            for (int j = 1; j <= n; j++)
                dfs(j);

            System.out.println(n - cnt);
            cnt = 0;
        }

    }

    static void dfs(int x) {
        // 맨 처음에만 사용하는 조건
        if (visited[x])
            return;

        visited[x] = true;
        int next = root[x];

        if (!visited[next])
            dfs(next);
        else {
            if (!finished[next]) {
                cnt++;
                for (int i = next; i != x; i = root[i])
                    cnt++;
            }
        }

        finished[x] = true;

    }

}
