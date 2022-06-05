import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Mst {
    static int[] parent;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Edge> graph = new ArrayList<>();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        parent = new int[v+1];
        for(int i = 0; i <= v; i++){
            parent[i] = i;
        }

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.add(new Edge(start,end,w));
        }

        graph.sort(null);

        int ans = 0;
        int cnt = 0;

        for(int i = 0; i < e; i++) {
            Edge edge = graph.get(i);
            if(!isSame(edge.s,edge.e)) {
                cnt++;
                union(edge.s,edge.e);
                ans += edge.w;
            }
            if(cnt == v -1)
                break;
        }

        System.out.println(ans);
    }
    static int find(int x) {
        if(parent[x] == x)
            return x;
        else
            return parent[x] = find(parent[x]);
    }

    static boolean isSame(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y)
            return true;
        else
            return false;
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y)
            parent[y] = x;
    }
    static class Edge implements Comparable<Edge>{
        int s;
        int e;
        int w;

        Edge(int s, int e, int w){
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
