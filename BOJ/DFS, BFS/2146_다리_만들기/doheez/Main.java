import java.io.*;
import java.util.*;

public class Main {
    int N;
    int[][] map;
    boolean[][] visited;
    int[] dRow = {-1, 1, 0, 0};
    int[] dCol = {0, 0, -1, 1};
    int min = Integer.MAX_VALUE;

    public void solution() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // visited 초기화
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        int cnt = 2; // 번호는 2부터 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // dfs로 서로 다른 섬 표시하기 (섬 구분을 위해 섬마다 서로 다른 번호 붙이기)
                if (map[i][j] == 1 && !visited[i][j]) {
                    map[i][j] = cnt;
                    visited[i][j] = true;
                    dfs(i, j, cnt++);
                }
                // 현위치가 섬이라면 bfs로 다른 섬까지의 최단 거리 구하기 (min 갱신)
                if (map[i][j] != 0) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(min);
        br.close();
    }

    public void dfs(int row, int col, int cnt) {
        for (int i = 0; i < 4; i++) {
            int newRow = row + dRow[i];
            int newCol = col + dCol[i];

            // 범위 검사
            if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) {
                continue;
            }
            // 방문 검사
            if (visited[newRow][newCol]) {
                continue;
            }
            // 이동하며 번호 매기기
            if (map[newRow][newCol] == 1) {
                visited[newRow][newCol] = true;
                map[newRow][newCol] = cnt;
                dfs(newRow, newCol, cnt);
            }
        }
    }

    public void bfs(int row, int col) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(row, col, 0));
        boolean[][] visited = new boolean[N][N];
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            Position position = queue.poll();

            // 가지치기
            if (position.dist >= min) {
                return;
            }

            // 사방탐색
            for (int i = 0; i < 4; i++) {
                int newRow = position.row + dRow[i];
                int newCol = position.col + dCol[i];

                // 범위 검사
                if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) {
                    continue;
                }
                // 방문 검사
                if (visited[newRow][newCol]) {
                    continue;
                }
                // 바다라면 방문하고 큐에 넣기
                if (map[newRow][newCol] == 0) {
                    visited[newRow][newCol] = true;
                    queue.add(new Position(newRow, newCol, position.dist + 1));
                }
                // 다른 섬에 도달했다면 min 값 비교 후 갱신
                else if (map[newRow][newCol] != map[row][col]) {
                    min = Math.min(min, position.dist);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

class Position {
    int row;
    int col;
    int dist;

    Position(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}