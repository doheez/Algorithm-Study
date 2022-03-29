import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] arr, dp;
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int ans = 0;
		arr = new int[n];
		dp = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i <n; i++) {
			dp[i]=1;
			for(int j=0; j <i; j++)
				if(arr[i] > arr[j])
					dp[i] = Math.max(dp[i], dp[j]+1);
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(n - ans);
		
	}

}
