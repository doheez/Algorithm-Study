import java.io.*;
import java.util.*;

public class Main {
    char[][] map = new char[12][6];
    boolean[][] visited = new boolean[12][6];
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    boolean isBurst = false; // 터뜨린 뿌요가 존재하는가
    int cnt = 0; // 연쇄 횟수

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        for (int i = 0; i < 12; i++) {
            String temp = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = temp.charAt(j);
                visited[i][j] = false;
            }
        }

        while (true) {
            // 모든 뿌요에 대해 BFS하며 뿌요 터뜨리기
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    bfs(new Position(i, j, map[i][j]));
                }
            }

            // 터뜨린 뿌요가 없으면 종료 (더이상 터뜨릴 게 없다는 뜻이니까)
            if (!isBurst) {
                break;
            } else {
                // 바닥으로 뿌요 떨어뜨리기
                moveAfterBurst();

                // 연쇄 횟수 증가
                cnt++;

                // visited 초기화
                for (int i = 0; i < 12; i++) {
                    for (int j = 0; j < 6; j++) {
                        visited[i][j] = false;
                    }
                }
                isBurst = false;
            }
        }

        // 출력
        System.out.println(cnt);
        br.close();
    }

    public void moveAfterBurst() {
        for (int j = 0; j < 6; j++) {
            while (true) {
                boolean isMoved = false;
                // 각 열별로 현위치가 '.'이고 위의 row에 뿌요가 있는 경우 한 칸 내린다.
                for (int i = 11; i > 0; i--) {
                    if (map[i][j] == '.' && map[i - 1][j] != '.') {
                        map[i][j] = map[i - 1][j];
                        map[i - 1][j] = '.';
                        isMoved = true;
                    }
                }
                // 더 내릴 게 없으면 종료
                if (!isMoved) {
                    break;
                }
            }
        }
    }

    public void bfs(Position start) {
        int cnt = 0; // 연쇄 횟수
        Queue<Position> queue = new LinkedList<>();
        LinkedList<Position> path = new LinkedList<>(); // 최소 4개 이상인지 확인하기 위함

        visited[start.row][start.col] = true;

        // 뿌요가 없는 위치면 bfs 종료
        if (start.color == '.') {
            visited[start.row][start.col] = true;
            return;
        }

        path.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            Position curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                Position next = new Position(curr.row + dr[i], curr.col + dc[i], '?');

                // 범위 검사
                if (next.row < 0 || next.row > 11 || next.col < 0 || next.col > 5) {
                    continue;
                }
                // 방문 검사
                if (visited[next.row][next.col]) {
                    continue;
                }
                // 색깔 지정
                next.color = map[next.row][next.col];

                // 같은 색깔의 뿌요면 경로에 추가하고 큐에 넣고 방문
                if (curr.color == next.color) {
                    path.add(next);
                    queue.add(next);
                    visited[next.row][next.col] = true;
                }
            }
        }
        // 찾아낸 같은 색 뿌요들이 4개 이상이면 뿌요를 터뜨린다
        if (path.size() >= 4) {
            while (!path.isEmpty()) {
                Position position = path.poll();
                map[position.row][position.col] = '.';
            }
            isBurst = true;
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

class Position {
    int row;
    int col;
    char color;

    Position(int row, int col, char color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }
}