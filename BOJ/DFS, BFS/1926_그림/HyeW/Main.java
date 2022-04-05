import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m, max, cur;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		int cnt = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					DFS(i, j);
					max = Math.max(max, cur);
					cnt++;
					cur = 0;
				}
			}
		
		System.out.println(cnt);
		System.out.println(max);
	}

	static void DFS(int x, int y) {
		visited[x][y] = true;
		cur ++;
		for (int i = 0; i < 4; i++) {
			int dx = x + dr[i];
			int dy = y + dc[i];
			
			if(dx < 0 || dy <0 || dx >=n || dy >= m)
				continue;
			
			if(arr[dx][dy] == 1 && !visited[dx][dy]) {
				DFS(dx, dy);
			}
		}
		
	}

}
