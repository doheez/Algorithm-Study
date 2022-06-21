package BOJ.MST.최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Connection {
    int n1;
    int weight;

    Connection(int n1, int weight) {
        this.n1 = n1;
        this.weight = weight;
    }
}

public class Prim {
    static boolean[] visited;
    static List<Connection>[] connList;
    static PriorityQueue<Connection> pq = new PriorityQueue<>(new Comparator<Connection>(){

        @Override
        public int compare(Connection o1, Connection o2) {
            return o1.weight - o2.weight;
        }
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");

        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);

        int answer = 0;
        int cnt = 0;

        visited = new boolean[n+1];
        connList = new List[n+1];

        for (int i = 0; i < n+1; i++) {
            connList[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            temp = br.readLine().split(" ");
            int n1 = Integer.parseInt(temp[0]);
            int n2 = Integer.parseInt(temp[1]);
            int w = Integer.parseInt(temp[2]);

            Connection conn1 = new Connection(n1, w);
            Connection conn2 = new Connection(n2, w);
            connList[n1].add(conn2);
            connList[n2].add(conn1);
        }

        pq.add(new Connection(1, 0));

        while(!pq.isEmpty()) {
            Connection me = pq.poll();
            if (visited[me.n1]) continue;

            visited[me.n1] = true;
            answer += me.weight;
            cnt++;

            if (cnt == n) break;

            for (Connection conn : connList[me.n1]) {
                if (!visited[conn.n1]) {
                    pq.add(conn);
                }
            }
        }

        System.out.println(answer);

    }
}
