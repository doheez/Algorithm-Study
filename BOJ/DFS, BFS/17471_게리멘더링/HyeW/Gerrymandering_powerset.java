import java.io.*;
import java.util.*;

public class Gerrymandering_powerset {
    static int[] population;
    static int n;
    static int total;
    static int min = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Integer>> city;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        population = new int[n + 1];
        city = new ArrayList<>();
        boolean[] divided = new boolean[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            total += population[i];
        }
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

        if (min != -1)
            powerSet(divided, 1);

        if(min == Integer.MAX_VALUE)
            min = -1;

        System.out.println(min);
    }

    static void powerSet(boolean[] divided, int idx) {
        if (idx == n+1) {
            //한 선거구역으로만 이루어졌는지 체크
            boolean first = divided[1];
            boolean same = false;
            for(int i = 1; i < n+1; i++)
                if(first != divided[i]) {
                    same = true;
                    break;
                }
            if(same == false)
                return;

            int t = bfs(divided, true);
            if (t != -1) {
                int f = bfs(divided, false);
                if (f != -1) {
                    min = Math.min(min, Math.abs(t - f));
                }
            }
            return;
        }

        divided[idx] = false;
        powerSet(divided, idx + 1);

        divided[idx] = true;
        powerSet(divided, idx + 1);

    }

    static int bfs(boolean[] divided, boolean value) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int cnt = 0;

        for (int i = 1; i < n + 1; i++)
            if (divided[i] == value) {
                q.add(i);
                visited[i] = true;
                break;
            }

        while (!q.isEmpty()) {
            int cur = q.poll();
            cnt += population[cur];
            for (int nxt : city.get(cur)) {
                if (visited[nxt] || divided[nxt] != value)
                    continue;

                q.add(nxt);
                visited[nxt] = true;
            }
        }

        if (value)
            for (int i = 1; i < n + 1; i++) {
                if (divided[i] != visited[i])
                    return -1;
            }
        else
            for (int i = 1; i < n + 1; i++) {
                if (divided[i] == visited[i])
                    return -1;
            }

        return cnt;
    }

}
