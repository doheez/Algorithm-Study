package BOJ.MST.전기가부족해;

import java.util.*;
import java.io.*;
import java.util.stream.*;

class Edge {
    int next;
    int w;

    public Edge(int next, int w) {
        this.next = next;
        this.w = w;
    }
}

public class Main {

    static List<Edge>[] conn;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");

        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int K = Integer.parseInt(temp[2]);

        conn = new List[N+1];
        visited = new boolean[N+1];

        for (int i = 0; i < N+1; i++) {
            conn[i] = new ArrayList<>();
        }

        String[] plant = br.readLine().split(" ");

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int c1 = Integer.parseInt(temp[0]);
            int c2 = Integer.parseInt(temp[1]);
            int w = Integer.parseInt(temp[2]);

            conn[c1].add(new Edge(c2, w));
            conn[c2].add(new Edge(c1, w));
        }

        Comparator<Edge> cp = (Edge i1, Edge i2) -> {
            return i1.w - i2.w;
        };

        PriorityQueue<Edge> pq = new PriorityQueue<>(cp);

        for (String p : plant) {
            int num = Integer.parseInt(p);
            visited[num] = true;

            pq.addAll(conn[num]);
        }

        int answer = 0;
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (visited[edge.next]) continue;
            visited[edge.next] = true;

            for (Edge e : conn[edge.next]) {
                if (visited[e.next]) continue;
                pq.add(e);
            }

            answer += edge.w;
        }

        System.out.println(answer);
    }
}
