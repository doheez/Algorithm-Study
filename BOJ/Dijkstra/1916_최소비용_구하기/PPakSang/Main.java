package BOJ.dijkstra.최소비용구하기;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Edge {
    int node;
    int weight;

    public Edge(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}
public class Main {
    static List<Edge>[] conn;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        conn = new List[N+1];
        dist = new int[N+1];
        visited = new boolean[N+1];

        for (int i = 0; i < N+1; i++) {
            conn[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }


        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int n1 = Integer.parseInt(temp[0]);
            int n2 = Integer.parseInt(temp[1]);
            int w = Integer.parseInt(temp[2]);

            conn[n1].add(new Edge(n2, w));

        }

        temp = br.readLine().split(" ");

        int start = Integer.parseInt(temp[0]);
        int end = Integer.parseInt(temp[1]);

        dijkstra(start);

        System.out.println(dist[end]);

    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            public int compare(Edge e1, Edge e2) {
                return e1.weight - e2.weight;
            }
        });

        dist[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.node]) continue;

            for (Edge next : conn[cur.node]) {
                int nNode = next.node;
                int nWeight = cur.weight + next.weight;

                if (!visited[nNode] && dist[nNode] > nWeight) {
                    dist[nNode] = nWeight;
                    pq.add(new Edge(nNode, nWeight));
                }
            }

            visited[cur.node] = true;
        }
    }

}

