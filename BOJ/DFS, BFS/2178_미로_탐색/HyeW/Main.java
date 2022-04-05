import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, cnt, s;
	static char[][] map;
	static Queue<Node> q;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		q = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}

		q.add(new Node(0, 0));
		map[0][0] = '2';
		BFS();

		System.out.println(cnt+1);
	}

	static void BFS() {
		while (q.size() > 0) {
			s = q.size();

			while (s-- > 0) {
				Node temp = q.poll();
				
				for (int i = 0; i < 4; i++) {
					int dx = temp.x + dr[i];
					int dy = temp.y + dc[i];

					if (dx == n-1 && dy == m-1) {
						cnt++;
						return;
					}

					if (dx < 0 || dy < 0 || dx >= n || dy >= m)
						continue;

					if (map[dx][dy] == '1') {
						q.add(new Node(dx, dy));
						map[dx][dy]='2';
					}
				}
			}
			cnt++;
		}
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
