import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NotOnlyMeLove {
    static ArrayList<ArrayList<Node>> graph;
    static int N,M,ans;
    static int[] type; //w = 0, m = 1

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        type = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++)
            type[i] = st.nextToken().equals("W")?0:1;

        for(int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<Node>());
        }


        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Node(d,w));
            graph.get(d).add(new Node(s,w));
        }

        prim();
        System.out.println(ans);
    }

    static void prim() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        int[] minR = new int[N+1];
        Arrays.fill(minR, Integer.MAX_VALUE);
        minR[1] = 0;
        int cnt = -1;

        q.add(new Node(1,0));

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(visited[cur.v])
                continue;

            cnt++;
            ans += cur.w;
            visited[cur.v] = true;

            for(Node next : graph.get(cur.v)) {
                if(!visited[next.v] && minR[next.v] > next.w && type[cur.v]!=type[next.v])
                {
                    q.add(next);
                    minR[next.v] = next.w;
                }
            }
        }

        if(cnt != N-1)
            ans = -1;
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
