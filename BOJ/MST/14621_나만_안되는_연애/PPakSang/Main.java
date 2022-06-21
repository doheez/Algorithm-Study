package BOJ.MST.나만안되는연애;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.*;

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
    static Map<Integer, String> type = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");

        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        conn = new List[N+1];
        visited = new boolean[N+1];

        temp = br.readLine().split(" ");

        for (int i = 0; i < N+1; i++) {
            conn[i] = new ArrayList<>();
            if (i != 0)
                type.put(i, temp[i-1]);
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

        boolean flag = true;
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                flag = false;
                break;
            }
        }

        if (flag)
            System.out.println(answer);
        else
            System.out.println(-1);
    }

    static long mst() {
        long answer = 0L;

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

            String t1 = type.get(cur.home);
            for (Path next : conn[cur.home]) {
                String t2 = type.get(next.home);

                if (!visited[next.home] && !t1.equals(t2)) {
                    pq.add(next);
                }
            }
            visited[cur.home] = true;
        }

        return answer;
    }
}
