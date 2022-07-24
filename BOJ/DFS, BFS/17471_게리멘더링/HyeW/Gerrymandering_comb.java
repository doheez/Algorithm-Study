import java.io.*;
import java.util.*;

public class Gerrymandering_comb {
    static int[] population;
    static boolean[] divided;
    static int n;
    static int min = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Integer>> city;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        population = new int[n + 1];
        city = new ArrayList<>();
        divided = new boolean[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++)
            population[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n + 1; i++)
            city.add(new ArrayList<>());

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int d = Integer.parseInt(st.nextToken());
                city.get(i).add(d);
            }
        }

        for (int i = 1; i < n; i++)
            comb(1, i, i);
        if (min == Integer.MAX_VALUE)
            min = -1;

        System.out.println(min);
    }

    static void comb(int start, int num, int r) {
        if (r == 0) {
            int t = bfs( num, true);
            if (t != -1) {
                int f = bfs( n - num, false);
                if (f != -1) {
                    min = Math.min(min, Math.abs(t - f));
                }
            }
            return;
        }

        for (int i = start; i < n + 1; i++) {
            divided[i] = true;
            comb( i + 1, num, r - 1);
            divided[i] = false;
        }

    }

    static int bfs( int num, boolean value) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int cnt = 0;
        int tnum = 0;

        for (int i = 1; i < n + 1; i++)
            if (divided[i] == value) {
                q.add(i);
                visited[i] = true;
                break;
            }

        while (!q.isEmpty()) {
            int cur = q.poll();
            cnt += population[cur];
            tnum++;
            for (int nxt : city.get(cur)) {
                if (visited[nxt] || divided[nxt] != value)
                    continue;

                q.add(nxt);
                visited[nxt] = true;
            }
        }

        if (tnum != num)
            return -1;

        return cnt;
    }

}
