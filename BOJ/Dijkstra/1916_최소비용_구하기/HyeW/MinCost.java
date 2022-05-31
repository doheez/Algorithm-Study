import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MinCost {
    static int N, M, start, end;
    static ArrayList<ArrayList<Node>> bus = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for(int i = 0; i < N+1; i++)
            bus.add(new ArrayList<>());

        for(int i = 0; i < M; i++ ) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            bus.get(a).add(new Node(b,w));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        long ans = dijkstra();

        System.out.println(ans);
    }

    static long dijkstra () {
        PriorityQueue<Node> q = new PriorityQueue<>((o1,o2)->Long.compare(o1.cost,o2.cost));
        long[] dist = new long[N+1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        q.offer(new Node(start,0));
        dist[start] = 0;

        while(!q.isEmpty()) {
            Node curNode = q.poll();

            if(curNode.city == end)
                break;

            if(dist[curNode.city] < curNode.cost)
                continue;

            for(int i = 0; i < bus.get(curNode.city).size(); i++) {
                Node nxtNode = bus.get(curNode.city).get(i);

                if(dist[nxtNode.city] > curNode.cost + nxtNode.cost) {
                    dist[nxtNode.city] = curNode.cost + nxtNode.cost;
                    q.offer(new Node(nxtNode.city, dist[nxtNode.city]));

                }
            }
        }

        return dist[end];
    }

    static class Node{
        int city;
        long cost;

        public Node(int city, long cost) {
            this.city = city;
            this.cost = cost;
        }
    }
}
