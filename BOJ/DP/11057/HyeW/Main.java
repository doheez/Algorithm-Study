import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		map = new int[n + 1][10];

		for (int i = 0; i < 10; i++) {
			map[1][i] = 1;
		}

		for (int i = 2; i <= n; i++) {
			for(int j = 0; j < 10; j++) {
				for(int k = 0; k <= ; k++) {
					map[i][j] += (map[i-1][k] % 10007);
				}
			}
		}
		int sum = 0;
		for(int i = 0; i < 10; i ++) {
			sum = (sum + map[n][i]) % 10007;
		}
		
		System.out.println(sum);

	}

}
