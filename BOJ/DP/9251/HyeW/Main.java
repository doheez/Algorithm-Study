import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String str1, str2;
	static int[][] dp = new int[1001][1001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str1 = br.readLine();
		str2 = br.readLine();
		int n1 = str1.length();
		int n2 = str2.length();
		
		for (int i = 0; i < n1; i++) {
			for (int j = 0; j < n2; j++)
				if (str1.charAt(i) == str2.charAt(j)) {
					dp[i + 1][j + 1] = dp[i][j] + 1;
				} else {
					dp[i + 1][j + 1] = Math.max(dp[i+1][j], dp[i][j+1]);
				}
		}
		
		System.out.println(dp[n1][n2]);
	}

}
