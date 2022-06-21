import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NetworkConnecting {
    static int V, E;
    static ArrayList<ArrayList<Node>> graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for(int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i <E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Node(e,w));
            graph.get(e).add(new Node(s,w));
        }

        int ans = prim();
        System.out.println(ans);
    }

    static int prim() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        int ans = 0;
        boolean[] visited = new boolean[V+1];
        Arrays.fill(visited, false);
        int[] minR = new int[V+1];

        for(int i = 2; i <= V; i++) {
            minR[i] = Integer.MAX_VALUE;
        }

        q.add(new Node(1,0));

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(visited[cur.v])
                continue;

            visited[cur.v] = true;
            ans += cur.w;

            for(Node next: graph.get(cur.v)) {
                if(!visited[next.v] && minR[next.v] > next.w) {
                    q.add(next);
                    minR[next.v] = next.w;
                }
            }
        }
        return ans;
    }

    static class Node implements Comparable<Node>{
        int v;
        int w;

        Node(int v, int w){
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
