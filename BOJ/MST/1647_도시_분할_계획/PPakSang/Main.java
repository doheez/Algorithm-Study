package BOJ.MST.도시분할계획;

import java.util.*;
import java.io.*;

class Path {
    int home;
    int cost;

    public Path(int home, int cost) {
        this.home = home;
        this.cost = cost;
    }
}

public class Main {
    static List<Path>[] conn;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");

        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        conn = new List[N+1];
        visited = new boolean[N+1];

        for (int i = 0; i < N+1; i++) {
            conn[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int h1 = Integer.parseInt(temp[0]);
            int h2 = Integer.parseInt(temp[1]);
            int c = Integer.parseInt(temp[2]);

            conn[h1].add(new Path(h2, c));
            conn[h2].add(new Path(h1, c));
        }

        long answer = mst();

        System.out.println(answer);


    }

    static long mst() {
        long answer = 0L;
        int max = Integer.MIN_VALUE;

        PriorityQueue<Path> pq = new PriorityQueue<>(new Comparator<Path>() {
            @Override
            public int compare(Path o1, Path o2) {
                return o1.cost - o2.cost;
            }
        });

        pq.add(new Path(1, 0));

        while(!pq.isEmpty()) {
            Path cur = pq.poll();

            if (visited[cur.home]) continue;

            answer += cur.cost;
            max = Math.max(max, cur.cost);


            for (Path next : conn[cur.home]) {
                if (!visited[next.home]) {
                    pq.add(next);
                }
            }
            visited[cur.home] = true;
        }

        return answer-max;
    }
}
