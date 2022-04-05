import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m, vcur, vcnt, wall;
	static int min = Integer.MAX_VALUE;
	static int[][] room;
	static boolean[][] visited;
	static Vi[] vi = new Vi[10];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		room = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (room[i][j] == 2) {
					vi[vcnt++] = new Vi(i, j);
				} else if (room[i][j] == 1) {
					wall++;
				}
			}
		}

		comb(0, 3);
		int result = m * n - min - (wall + 3);
		System.out.println(result);
	}

	static void comb(int start, int r) {
		if (r == 0) {
			visited = new boolean[n][m];
			for (int i = 0; i < vcnt; i++) {
				DFS(vi[i].x, vi[i].y);
			}
			min = Math.min(min, vcur);
			vcur = 0;
			return;
		}

		for (int i = start; i < n * m; i++) {
			int x = i / m;
			int y = i % m;

			if (room[x][y] != 0)
				continue;

			room[x][y] = 1;
			comb(i + 1, r - 1);
			room[x][y] = 0;
		}

	}

	static void DFS(int x, int y) {
		vcur++;
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int dx = x + dr[i];
			int dy = y + dc[i];

			if (dx < 0 || dy < 0 || dx >= n || dy >= m)
				continue;

			if (room[dx][dy] == 0 && !visited[dx][dy]) {
				DFS(dx, dy);
			}
		}
	}

	static class Vi {
		int x;
		int y;

		public Vi(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
