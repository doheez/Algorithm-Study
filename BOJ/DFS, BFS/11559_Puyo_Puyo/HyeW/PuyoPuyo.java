import java.io.*;
import java.util.*;

public class PuyoPuyo {
    static char[][] puyos;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static boolean[][] visited;
    static ArrayList<Puyo> location;
    static int row = 12;
    static int col = 6;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        visited = new boolean[row][col];
        puyos = new char[row][col];
        location = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            puyos[i] = br.readLine().toCharArray();
        }

        //뿌요 알고리즘 시작
        while (true) {
            ArrayList<Puyo> result = new ArrayList<>();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (visited[i][j] || puyos[i][j] == '.')
                        continue;

                    result = bfs(new Puyo(i, j));
                    if (result != null)
                        for (Puyo cur : result)
                            location.add(cur);

                }
            }

            //터질 뿌요가 없으면 종료
            if (location.size() == 0)
                break;

            cnt++;
            // row로 오름차순 정렬 (위에 것이 먼저 터지는게 pop할 때 for문을 덜 돈다)
            Collections.sort(location, new Comparator<Puyo>() {
                @Override
                public int compare(Puyo o1, Puyo o2) {
                    return o1.x - o2.x;
                }
            });

            //뿌요 옮기기
            for (Puyo cur : location) {
                pop(cur);
            }

            //초기화
            location.clear();
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++)
                    visited[i][j] = false;
        }
        System.out.println(cnt);

    }

    static void pop(Puyo s) {
        int x = s.x;
        int y = s.y;

        int dx = s.x - 1;
        while (true) {
            //x좌표가 0이하거나 옮길 뿌요가 없을 때
            if (dx < 0 || puyos[dx][y] == '.') {
                puyos[x][y] = '.';
                break;
            } else {
                puyos[x][y] = puyos[dx][y];
            }

            x = x - 1;
            dx = dx - 1;
        }

    }

    // 터질 뿌요찾기
    static ArrayList<Puyo> bfs(Puyo s) {
        Queue<Puyo> q = new LinkedList<>();
        ArrayList<Puyo> puyo = new ArrayList<>();
        q.add(s);
        puyo.add(s);
        visited[s.x][s.y] = true;

        while (!q.isEmpty()) {
            Puyo cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int dx = cur.x + dr[i];
                int dy = cur.y + dc[i];

                if (dx >= row || dy >= col || dx < 0 || dy < 0)
                    continue;
                if (visited[dx][dy] || puyos[dx][dy] == '.')
                    continue;
                if (puyos[cur.x][cur.y] != puyos[dx][dy])
                    continue;

                q.add(new Puyo(dx, dy));
                visited[dx][dy] = true;
                puyo.add(new Puyo(dx, dy));
            }
        }
        if (puyo.size() < 4)
            return null;
        return puyo;
    }

    static class Puyo {
        int x;
        int y;

        Puyo(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
