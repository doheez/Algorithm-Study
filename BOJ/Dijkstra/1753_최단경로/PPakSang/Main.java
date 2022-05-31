package BOJ.dijkstra.최단경로.복습;

import java.util.*;
import java.io.*;

/*
출발 점에서 부터 각 노드까지 거리의 최솟값

매번 갈 수 있는 노드 중에 가장 최소 값을 뽑는다

현재 갈 수 있는 노드 중에서 가장 최소 거리는 그 노드 까지 거리의 가장 최소값이 맞다

현재 최소 거리 -> 다음에 경유할 노드이기 때문에 다음에 오는 거리들은 무조건 현재 최소 거리보다 멀다

그럼 현재 갈 수 있는 노드 중 최소 뽑히면, 그 노드에서 갈 수 있는 노드들 볼건데, 방문한 데는 볼 필요가 없다

방문 안 한 곳에서 이 때 까지 경유해서 그 노드로 갈 수 있는 거리 중 최소인 경로만 다시 pq 에 넣는다

*/



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

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");

        int V = Integer.parseInt(temp[0]);
        int E = Integer.parseInt(temp[1]);

        conn = new List[V+1];
        dist = new int[V+1];
        visited = new boolean[V+1];

        for (int i = 0; i < V+1; i++) {
            conn[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            temp = br.readLine().split(" ");
            int n1 = Integer.parseInt(temp[0]);
            int n2 = Integer.parseInt(temp[1]);
            int w = Integer.parseInt(temp[2]);

            conn[n1].add(new Edge(n2, w));

        }

        dijkstra(start);

        for (int i = 1; i < dist.length; i++) {
            int dt = dist[i];
            if (dt == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dt);
        }

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
