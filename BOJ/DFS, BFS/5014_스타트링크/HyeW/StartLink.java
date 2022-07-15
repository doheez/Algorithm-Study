import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StartLink {
    static int F,S,G,U,D, cnt;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        visited = new boolean[F+1];

        int cnt = bfs();
        if(cnt == -1)
            System.out.println("use the stairs");
        else
            System.out.println(cnt);
    }

    static int bfs() {
        Queue<Stair> q = new LinkedList<>();
        q.add(new Stair(S,0));
        visited[S]=true;

        while(!q.isEmpty()) {
            Stair cur = q.poll();

            if(cur.floor == G)
                return cur.cnt;

            //올라가기
            if(cur.floor+U <= F && !visited[cur.floor+U]) {
                q.add(new Stair(cur.floor + U,cur.cnt+1));
                visited[cur.floor+U] = true;
            }
            //내려가기
            if(cur.floor-D > 0 && !visited[cur.floor-D]) {
                q.add(new Stair(cur.floor-D,cur.cnt+1));
                visited[cur.floor-D] = true;
            }

        }

        return -1;
    }

    static class Stair{
        int floor;
        int cnt;

        Stair(int floor, int cnt){
            this.floor = floor;
            this.cnt = cnt;
        }
    }
}

