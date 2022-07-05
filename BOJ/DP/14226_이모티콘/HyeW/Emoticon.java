import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Emoticon {
    static int S;
    static boolean[][] visited; // [클립보드][개수]

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());
        visited = new boolean[S + 1][S + 1];
        bfs();
    }

    static void bfs() {
        Queue<Emoji> q = new LinkedList<Emoji>();
        q.add(new Emoji(1, 0, 0));
        visited[0][1] = true;

        while (!q.isEmpty()) {
            Emoji cur = q.poll();

            if (cur.cnt == S) {
                System.out.println(cur.sec);
                return;
            }

            // 1. 임티 클립보드에 복사
            if (!visited[cur.cnt][cur.cnt]) {
                q.add(new Emoji(cur.cnt, cur.sec + 1, cur.cnt));
                visited[cur.cnt][cur.cnt] = true;
            }
            // 2. 클립보드에 있던거 붙이기
            if (cur.clip != 0 && cur.cnt + cur.clip <= S && !visited[cur.clip][cur.clip + cur.cnt] ) {
                q.add(new Emoji(cur.cnt + cur.clip, cur.sec + 1, cur.clip));
                visited[cur.clip][cur.clip + cur.cnt] = true;
            }

            // 3. 삭제
            if (cur.cnt != 0 && !visited[cur.clip][cur.cnt - 1]) {
                q.add(new Emoji(cur.cnt - 1, cur.sec + 1, cur.clip));
                visited[cur.clip][cur.cnt - 1] = true;
            }
        }
    }

    static class Emoji {
        int cnt;
        int sec;
        int clip;

        Emoji(int cnt, int sec, int clip) {
            this.sec = sec;
            this.cnt = cnt;
            this.clip = clip;
        }
    }
}
