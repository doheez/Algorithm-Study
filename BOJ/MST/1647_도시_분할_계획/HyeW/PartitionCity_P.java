import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PartitionCity_P {
    static int N, M, ans;
    static ArrayList<ArrayList<Node>> graph;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i <M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Node(e,w));
            graph.get(e).add(new Node(s,w));
        }

        prim();
        System.out.println(ans);
    }

    static void prim() {
        boolean[] visited = new boolean[N+1];
        int[] minR = new int[N+1];
        //Arrays.fill(visited, false);
        Arrays.fill(minR, Integer.MAX_VALUE);
        minR[1] = 0;
        PriorityQueue<Node> q = new PriorityQueue<>();

        q.add(new Node(1,0));

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(visited[cur.v])
                continue;

            ans += cur.w;
            visited[cur.v] = true;

            for(Node next : graph.get(cur.v)) {
                if(!visited[next.v] && minR[next.v] > next.w )
                {
                    q.add(next);
                    minR[next.v] = next.w;
                }
            }
        }

        Arrays.sort(minR);
        ans -= minR[N-1];
    }

    static class Node implements Comparable<Node> {
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

}
