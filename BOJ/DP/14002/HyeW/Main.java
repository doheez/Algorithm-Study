import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static Node[] dp;
	static int[] arr;
	static int n;
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		dp = new Node[n];
		arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < n; i++) {
			dp[i] = new Node(1, 0);
			for(int j = 0; j < i; j++) {
				if(arr[i] > arr[j] && dp[i].val  < dp[j].val + 1 ) {
					dp[i].pre = j;
					dp[i].val = dp[j].val + 1;
				}
			}
		}
		
		int max = 0;
		int max_index = 0;
		
		for(int i =0; i <n; i++) {
			if(max < dp[i].val) {
				max = dp[i].val;
				max_index = i;
			}
		}
		
		System.out.println(max);
		
		print(max_index);
		System.out.println(sb);
	}
	
	static void print(int index) {
		if(dp[index].val == 0)
			return;
		print(dp[index].pre);
		
		sb.append(arr[index]+" ");
	}
	
	static class Node{
		int val;
		int pre;
		
		public Node(int val, int pre) {
			this.val = val;
			this.pre = pre;
		}
	}

}
