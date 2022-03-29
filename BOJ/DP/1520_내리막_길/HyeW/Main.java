import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] road, dp;
	static int row, col;
	static int[] dc = { -1, 1, 0, 0 };
	static int[] dr = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		road = new int[row][col];
		dp = new int[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				road[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		System.out.println(DFS(0,0));
	}

	static int DFS(int x, int y) {
		if (x == (row - 1) && y == (col - 1)) {
			return 1;
		}
		
		if(dp[x][y] != -1) {
			return dp[x][y];
		}
		
		int nrow;
		int ncol;
		
		dp[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			nrow = x + dr[i];
			ncol = y + dc[i];
			if (nrow < 0 || ncol < 0 || nrow >= row || ncol >= col)
				continue;

			if (road[nrow][ncol] < road[x][y]) {
				dp[x][y] += DFS(nrow, ncol);
			}
		}
		
		return dp[x][y];
	}


}
