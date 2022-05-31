import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ShortestPath {
    static int V, E, start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();

        for(int i = 0; i <= V; i++)
            graph.add(new ArrayList<>());

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b,w));
        }

        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2)-> Integer.compare(o1.cost,o2.cost));
        q.offer(new Node(start,0));
        dist[start] = 0;

        while(!q.isEmpty()) {
            Node curNode = q.poll();

            if(dist[curNode.idx] <curNode.cost)
                continue;

            for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
                Node nxtNode = graph.get(curNode.idx).get(i);

                if(dist[nxtNode.idx] > curNode.cost + nxtNode.cost) {
                    dist[nxtNode.idx] = curNode.cost + nxtNode.cost;
                    q.offer(new Node(nxtNode.idx,dist[nxtNode.idx]));
                }
            }

        }

        for(int i = 1; i<= V; i++) {
            if(dist[i] == Integer.MAX_VALUE)
                sb.append("INF\n");
            else
                sb.append(dist[i]+"\n");
        }

        System.out.println(sb);
    }

    static class Node{
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}
