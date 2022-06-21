import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LackElectricity {
    static int N, M, K;
    static int[] parent;
    static ArrayList<Edge> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int idx = Integer.parseInt(st.nextToken());
            parent[idx] = -1;
        }

        graph = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.add(new Edge(s,e,w));
        }

        Collections.sort(graph);

        int ans = kruskal();
        System.out.println(ans);
    }

    static int kruskal() {
        int ans = 0;
        for(Edge city : graph) {

            if(find(city.s)!=find(city.e)) {
                union(city.s,city.e);
                ans += city.w;
            }

            if(isConnect())
                break;
        }

        return ans;
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == -1)
            parent[y] = x;
        else if (y == -1)
            parent[x] = y;
        else if (y != x)
            parent[y] = x;
    }

    static int find(int x) {
        if (parent[x] == -1)
            return -1;
        if (parent[x] == x)
            return x;
        else
            return parent[x] = find(parent[x]);
    }

    static boolean isConnect() {
        for(int i = 1; i < N+1; i++) {
            if(parent[i]!= -1)
                return false;
        }
        return true;
    }

    static class Edge implements Comparable<Edge> {
        int s;
        int e;
        int w;

        Edge(int s,int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

}
