import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1 {
	static int[][] stair;
	static int CONST = 1000000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		stair = new int[n+1][10];
		
		stair[1][0] = 0;
		
		for(int i = 1; i < 10; i++) {
			stair[1][i] = 1;
		}
		
		System.out.println(dp(n));
		
	}
	
	static int dp(int n) {
		int sum = 0;
		
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j <10; j++) {
				if(j == 0) {
					stair[i][j] = stair[i-1][1] % CONST;
				}else if(j == 9) {
					stair[i][j] = stair[i-1][8] % CONST;
				}else {
					stair[i][j] = (stair[i-1][j-1] + stair[i -1][j+1]) % CONST;
				}
			}
		}
		
		for(int i = 0; i <10; i++)
			sum  = stair[n][i] + sum % CONST;
		
		return sum % CONST;
	}

}
