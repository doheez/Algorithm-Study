import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BipartiteGraph {
    static int K;
    static int[] visited;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            visited = new int[v + 1];
            graph = new ArrayList<>();

            for (int j = 0; j <= v; j++)
                graph.add(new ArrayList<>());

            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                graph.get(d).add(s);
                graph.get(s).add(d);
            }

            boolean result = false;

            for (int j = 1; j <= v; j++) {
                if (visited[j] == 0)
                    result = bfs(j);
                if (!result)
                    break;
            }

            if (result)
                System.out.println("YES");
            else
                System.out.println("NO");

        }

    }

    static boolean bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        visited[n] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int nxt : graph.get(cur)) {
                if (visited[nxt] == 0) {
                    visited[nxt] = visited[cur] * -1;
                    q.add(nxt);
                } else if (visited[nxt] == visited[cur]) {
                    return false;
                }
            }
        }

        return true;
    }
}
