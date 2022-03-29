package BOJ.DP.가장긴증가하는부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

    static int[] arr;
    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++){
            ascNums(i);

        }

        System.out.println(Arrays.stream(dp).max().getAsInt());

    }

    static int ascNums(int start) {
        if (dp[start] == 0) {
            dp[start] = 1;

            int temp = arr[start];

            for (int i = start - 1; i >= 0; i--) {
                if (temp > arr[i]) {
                    dp[start] = Math.max(dp[start], ascNums(i) + 1);
                }
            }
        }
        return dp[start];
    }
}
