import java.io.*;
import java.util.*;

public class RoboticVacuum {
    static int[][] room;
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        room = new int[R][C];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        Position cur = new Position(r, c, d);

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++)
                room[i][j] = Integer.parseInt(st.nextToken());

        }

        int result = clean(cur);

        System.out.println(result);
    }

    static int clean(Position cur) {
        int cnt = 1;

        while (true) {

            cur = search(cur);

            if (cur == null)
                break;

            cnt++;
        }

        return cnt;
    }

    static Position search(Position cur) {
        Position nxt;

        room[cur.x][cur.y] = 2;
        while (true) {
            for (int i = 0; i < 4; i++) {
                if (cur.d == 0)
                    cur.d = 3;
                else
                    cur.d = cur.d - 1;

                int dx = cur.x + dr[cur.d];
                int dy = cur.y + dc[cur.d];

                if (dx >= R || dy >= C || dx < 0 || dy < 0)
                    continue;

                if (room[dx][dy] == 0) {
                    nxt = new Position(dx, dy, cur.d);
                    return nxt;
                }
            }

            // 한 칸 후진
            int back = Math.abs(cur.d - 2);
            if (cur.d == 1)
                back = 3;


            int dx = cur.x + dr[back];
            int dy = cur.y + dc[back];

            if (dx >= R || dy >= C || dx < 0 || dy < 0 || room[dx][dy] == 1)
                return null;

            cur.x = dx;
            cur.y = dy;

        }
    }

    static class Position {
        int x;
        int y;
        int d;

        Position(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
