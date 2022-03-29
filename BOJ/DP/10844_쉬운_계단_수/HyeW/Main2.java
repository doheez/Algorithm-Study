import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class stair_recur {
	static Long[][] stair;
	static int CONST = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		long sum = 0;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		stair = new Long[n + 1][10];
		
		stair[1][0] = 0;
		for (int i = 1; i < 10; i++)
			stair[1][i] = 1L;
		
		for (int i = 0; i < 10; i++)
			sum = recur(n, i) + sum;

		System.out.println(sum % CONST);
	}

	static long recur(int n, int val) {
		if (n == 1) {
			return stair[n][val];
		}
		
		if (stair[n][val] == null) {
			 if (val == 0) {
				stair[n][val] = recur(n - 1, 1);
			} else if (val == 9) {
				stair[n][val] = recur(n - 1, 8);
			} else {
				stair[n][val] = recur(n - 1, val - 1) + recur(n - 1, val + 1);
			}
		}
		
		return stair[n][val] % CONST;

	}

}
